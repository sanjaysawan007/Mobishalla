package com.example.mobishalaassignment.architecture.developement.modules

import android.app.Application
import android.content.Intent
import com.example.mobishalaassignment.architecture.constants.Constants
import com.example.mobishalaassignment.architecture.constants.Constants.Injection.ENDPOINT_V1
import com.example.mobishalaassignment.architecture.constants.Constants.Injection.GSON_V1
import com.example.mobishalaassignment.architecture.constants.Constants.Injection.INTERCEPTOR_HEADER_V1
import com.example.mobishalaassignment.architecture.constants.Constants.Injection.INTERCEPTOR_LOGGING_V1
import com.example.mobishalaassignment.architecture.constants.Constants.Injection.INTERCEPTOR_RESPONSE_V1
import com.example.mobishalaassignment.architecture.constants.Constants.Injection.OKHHTP_CACHE_V1
import com.example.mobishalaassignment.architecture.constants.Constants.Injection.OKHHTP_CLIENT_V1
import com.example.mobishalaassignment.architecture.constants.Constants.Injection.RETROFIT_V1
import com.example.mobishalaassignment.architecture.customAppComponents.interfaces.ApiProject
import com.example.mobishalaassignment.architecture.customAppComponents.interfaces.EndPoint
import com.example.mobishalaassignment.architecture.developement.implementations.ApiEndPointImpl
import com.example.mobishalaassignment.architecture.developement.implementations.ApiProjectImpl


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor

import dagger.Module
import dagger.Provides

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {



   //private val presenter=Presenter()

    @Provides
    @Singleton
    @Named(GSON_V1)
    internal fun provideGsonV1(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    @Named(OKHHTP_CACHE_V1)
    internal fun provideOkHttpCacheV1(application: Application): Cache {
        // Install an HTTP cache in the application cache directory.
        val cacheDir = File(application.cacheDir, "http")
        return Cache(cacheDir, DISK_CACHE_SIZE.toLong())
    }

    @Provides
    @Singleton
    @Named(OKHHTP_CLIENT_V1)
    internal fun provideOkHttpClientV1(
        @Named(OKHHTP_CACHE_V1) cache: Cache,
        @Named(INTERCEPTOR_LOGGING_V1) logging: HttpLoggingInterceptor,
        @Named(INTERCEPTOR_HEADER_V1) headerInterceptor: Interceptor,
        @Named(INTERCEPTOR_RESPONSE_V1) globalResponseInterceptor: Interceptor,
        application: Application

    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        //builder.networkInterceptors().add(cachingControlInterceptor);

        return builder.apply {
            addInterceptor(headerInterceptor)
            readTimeout(120, TimeUnit.SECONDS)
            connectTimeout(120, TimeUnit.SECONDS)
            addInterceptor(logging)
            //if (BuildConfig.DEBUG)
             addInterceptor(OkHttpProfilerInterceptor())
            addInterceptor {
                chain ->
                val response=chain.proceed(chain.request())
                response.code()
                if (response.code()==440)
                {
                   //logout(application, sharedPreferencesUtil,dataBaseUtil)


                }

                response
            }

          //  addInterceptor(globalResponseInterceptor)
            cache(null)
        }.build()
    }

    @Provides
    @Singleton
    @Named(INTERCEPTOR_RESPONSE_V1)
    internal fun provideGlobalApiResponseInterceptorV1(
        application: Application

    ): Interceptor {
        return Interceptor { chain ->

            val request = chain.request()
            val response = chain.proceed(request)
            //val responseTemp = chain.proceed(request)
            var responseOriginal: Response? = null

            try {
                val responseBody = response.body()?.string()
                val jsonObject = JSONObject(responseBody)
                jsonObject?.let {
                    val isStatusCodeNotValid = 440 /*jsonObject.optInt("responseCode", 0)*/
                    if (isStatusCodeNotValid != 0) {
                        if (isStatusCodeNotValid == 440) {
                            return@Interceptor response
                        }
                    }
                }

                return@Interceptor response.newBuilder()
                    .body(ResponseBody.create(response.body()?.contentType(), responseBody))
                    .build()

            } catch (e: Exception) {
                e.printStackTrace()
                return@Interceptor null
            }
        }
    }

    @Provides
    @Singleton
    @Named(INTERCEPTOR_HEADER_V1)
    internal fun provideRetrofitHeaderV1(
        application: Application
    ): Interceptor {
        return Interceptor { chain ->
            /*val original = chain.request()
            val builder = original.newBuilder()
            builder.header("Content-Type", "application/json").method(
                original.method(),
                original.body()
            )
            builder.url()

            val request = builder.build()*/
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url()
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key",Constants.API_KEY)
                .build()
            val request = originalRequest.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    @Named(INTERCEPTOR_LOGGING_V1)
    internal fun provideLoggingInterceptorV1(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Named(ENDPOINT_V1)
    internal fun provideEndPointV1(
        @Named(Constants.Injection.API_CURRENT_URL)
        URL: String
    ): EndPoint {
        return ApiEndPointImpl().setEndPoint(URL)
    }

    @Provides
    @Singleton
    @Named(RETROFIT_V1)
    internal fun provideRetrofitV1(
        @Named(GSON_V1) gson: Gson,
        @Named(OKHHTP_CLIENT_V1) okHttpClient: OkHttpClient,
        @Named(ENDPOINT_V1) endPoint: EndPoint
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(endPoint.url!!)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofitApiV1(@Named(RETROFIT_V1) retrofit: Retrofit): ApiProject {
        return ApiProjectImpl(retrofit)
    }

    companion object {
        internal val DISK_CACHE_SIZE = 1024 * 1024
    }

    private fun clearAll(){

    }

}
