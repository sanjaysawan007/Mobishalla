package com.example.mobishalaassignment.presenter.connector

/**
 * Created by Sanjay Kumar on 06/02/2022.
 */
class Connector {

    interface ViewOpt<Request, Response> : ReusableView {
        val apiRequest: Request?
        fun getApiSuccess(value: Response)
        //fun getApiValidationFail(value : Response)
    }
    interface PresenterOpt
}