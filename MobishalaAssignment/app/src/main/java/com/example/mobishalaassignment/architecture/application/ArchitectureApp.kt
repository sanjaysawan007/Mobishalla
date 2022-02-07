package com.example.mobishalaassignment.architecture.application

import android.app.Application
import android.content.Context
import com.example.mobishalaassignment.architecture.developement.components.ApplicationComponent
import com.example.mobishalaassignment.architecture.developement.components.DaggerApplicationComponent
import com.example.mobishalaassignment.architecture.developement.modules.NetworkModule
import com.example.mobishalaassignment.architecture.developement.modules.PrimitivesModule

import com.optcrm.optreporting.AppModule




/**
 * Created by Sanjay Kumar on 06/02/2022.
 */
class ArchitectureApp : Application() {

    companion object {
        lateinit var instance: ArchitectureApp
            private set
    }

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()

                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .primitivesModule(PrimitivesModule())
                .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        component.inject(this)

    }



    override fun onTerminate() {
        super.onTerminate()
    }
}