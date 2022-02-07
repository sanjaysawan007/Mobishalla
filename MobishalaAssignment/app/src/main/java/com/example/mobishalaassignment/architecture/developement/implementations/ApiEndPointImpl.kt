package com.example.mobishalaassignment.architecture.developement.implementations

import com.example.mobishalaassignment.architecture.customAppComponents.interfaces.EndPoint


/**
 * Created by Sanjay Kumar on 06/02/2022.
 */

class ApiEndPointImpl : EndPoint {

    var sUrl: String? = null

    override val url: String?
        get() = sUrl

    override val name: String?
        get() = "Project" //To change initializer of created properties use File | Settings | File Templates.

    fun setEndPoint(url: String): ApiEndPointImpl {
        this.sUrl = url
        return this
    }
}