package com.example.mobishalaassignment.architecture.retrofit.api.response

import com.google.gson.annotations.SerializedName


object Response {
    data class ResponseSample(var isSuccess: Boolean, var status: Int, var message: String)


}
