package com.example.mobishalaassignment.architecture.customAppComponents.activity

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobishalaassignment.architecture.customAppComponents.interfaces.CustomAppActivity


/**
 * Created by Sanjay Kumar on 06/02/2022.
 */
open class BaseAppActivityImpl :  AppCompatActivity(),CustomAppActivity {

    private var activity: Activity = this
    private var context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity = this
        context = this

    }

    override fun getActivity(): Activity {
        return activity
    }

    override fun getContext(): Context {
        return context
    }
}