package com.example.mobishalaassignment.presenter.connector

/**
 * Created by Sanjay Kumar on 06/02/2022.
 */
interface ReusableView {
    fun showToast(msg: String)
    fun showProgressDialog()
    fun hideProgressDialog()
    fun getApiFailure(msg: String)
}