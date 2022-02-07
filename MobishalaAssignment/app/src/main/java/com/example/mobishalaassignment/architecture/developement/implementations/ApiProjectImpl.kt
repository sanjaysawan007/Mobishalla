package com.example.mobishalaassignment.architecture.developement.implementations

import com.example.mobishalaassignment.architecture.customAppComponents.interfaces.ApiProject
import com.example.mobishalaassignment.architecture.retrofit.api.Api
import retrofit2.Retrofit

/**
 * Created by Sanjay Kumar on 06/02/2022.
 */

class ApiProjectImpl(retrofit: Retrofit) : ApiProject {

    var apiObject: Api? = null

    init {
        apiObject = retrofit.create(Api::class.java)
    }

    override val api: Api
        get() = apiObject!!
}