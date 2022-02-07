package com.example.mobishalaassignment.presenter.presenter

import android.app.ProgressDialog
import android.content.Context
import com.example.mobishalaassignment.architecture.application.ArchitectureApp
import com.example.mobishalaassignment.architecture.constants.ConstantsApi
import com.example.mobishalaassignment.architecture.customAppComponents.activity.BaseAppCompatActivity
import com.example.mobishalaassignment.architecture.customAppComponents.interfaces.ApiProject
import com.example.mobishalaassignment.architecture.retrofit.api.requests.Requests
import com.example.mobishalaassignment.architecture.util.DialogFactory
import com.example.mobishalaassignment.architecture.util.exShowToast
import com.example.mobishalaassignment.presenter.connector.Connector
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Created by Sanjay Kumar on 06/02/2022.
 */
@Suppress("UNCHECKED_CAST")
class Presenter {

    @Inject
    lateinit var apiProject: ApiProject


    init {
        ArchitectureApp.instance.component.inject(this)
    }
    fun <RequestApi, ResponseApi> callNetwork(type: ConstantsApi, movieConnector: Connector.ViewOpt<RequestApi, ResponseApi>) {
        val requestApi = when (type) {
            ConstantsApi.CALL_POPULAR_MOVIE -> apiProject.api.getPopularMovies()
            ConstantsApi.CALL_NOW_PLAYING_MOVIE ->apiProject.api.getNowPlayingMovies()
            ConstantsApi.CALL_MOVIEDETAIL_API ->apiProject.api.getMovieDetails((movieConnector.apiRequest as Requests.RequestMovieID).movieId)
            ConstantsApi.CALL_CAST_API ->apiProject.api.getCastDetails((movieConnector.apiRequest as Requests.RequestMovieID).movieId)


            else -> return
        }

        callApi(movieConnector, requestApi = requestApi)
    }

    private fun <RequestApi, ResponseApi> callApi(viewOpt: Connector.ViewOpt<RequestApi, ResponseApi>, requestApi: Observable<out Any>) {
        val dispose = requestApi
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewOpt.showProgressDialog() }
                .doFinally { viewOpt.hideProgressDialog() }
                .subscribe({ response ->
                    response?.let { apiSuccess(viewOpt, response as ResponseApi) }
                }, { e -> if(e != null){
                    val errorMessage  = ApiError(e).message
                    apiFailure(viewOpt , errorMessage)

                }else {
                    apiFailure(viewOpt , e)
                }
                }
                )


    }

    private fun <RequestApi, ResponseApi> apiSuccess(viewOpt: Connector.ViewOpt<RequestApi, ResponseApi>, response: ResponseApi) {
        viewOpt.getApiSuccess(value = response)
    }

    private fun <RequestApi, ResponseApi> apiFailure(viewOpt: Connector.ViewOpt<RequestApi, ResponseApi>, e: String?) {
        viewOpt.getApiFailure(e.toString())
    }


}

abstract class ViewGeneric<RequestApi, ResponseApi>(val context: Context) : Connector.ViewOpt<RequestApi, ResponseApi> {

    internal var progressDialog: ProgressDialog? = null

    override fun showToast(msg: String) {
        msg.exShowToast(context)
    }

    override fun showProgressDialog() {
        when (BaseAppCompatActivity.progressDialog == null) {
            true -> BaseAppCompatActivity.progressDialog = DialogFactory.getInstance(context = context)
        }
        //BaseAppCompatActivity.progressDialog?.show()
        //BaseAppCompatActivity.progressDialog?.setCancelable(false)
    }

    override fun hideProgressDialog() {
        BaseAppCompatActivity.progressDialog?.hide()
    }

    override fun getApiFailure(msg: String) {
        if (msg == "440") {

        } else {
            showToast(msg)
        }
        //showToast(context.getString(R.string.error_api_failure))
    }
}
class ApiError constructor(error : Throwable){
    var message = "An error occured"
    var responseCode = "0"
    init {
        if(error is HttpException){
            val errorJsonString = error.response().errorBody()?.string()

            val responseMessage:JSONObject = JSONObject(errorJsonString)
            message = responseMessage.getString("responseMsg")
            responseCode = responseMessage.getString("responseCode")
        }
        else{
            this.message = error.message ?: this.message
        }
    }
}

