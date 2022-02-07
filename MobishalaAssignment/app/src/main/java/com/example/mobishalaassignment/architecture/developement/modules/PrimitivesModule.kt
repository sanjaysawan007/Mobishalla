package com.example.mobishalaassignment.architecture.developement.modules

import com.example.mobishalaassignment.architecture.constants.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by Sanjay Kumar on 06/02/2022.
 */

@Module
class PrimitivesModule {

    @Provides
    @Named(Constants.Injection.API_CURRENT_URL)
    fun provideCurrentURL(@Named(Constants.Injection.API_DEVELOPMENT_URL) URL: String): String {
        return URL
    }

    @Provides
    @Named(Constants.Injection.API_DEVELOPMENT_URL)
    fun provideDevelopmentURL(): String {
        return Constants.API.URL.URL_DEVELOPMENT
    }

    @Provides
    @Named(Constants.Injection.API_TESTING_URL)
    fun provideTestingURL(): String {
        return Constants.API.URL.URL_TESTING
    }

    @Provides
    @Named(Constants.Injection.API_LIVE_URL)
    fun provideLiveURL(): String {
        return Constants.API.URL.URL_LIVE
    }

    @Provides
    @Named(Constants.Injection.API_PRODUCTION_URL)
    fun provideProductionURL(): String {
        return Constants.API.URL.URL_PRODUCTION
    }
}
