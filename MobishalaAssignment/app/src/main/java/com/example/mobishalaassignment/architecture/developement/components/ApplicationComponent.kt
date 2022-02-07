package com.example.mobishalaassignment.architecture.developement.components

import android.app.Application
import com.example.mobishalaassignment.architecture.customAppComponents.activity.BaseAppCompatActivity
import com.example.mobishalaassignment.architecture.developement.modules.NetworkModule
import com.example.mobishalaassignment.architecture.developement.modules.PrimitivesModule
import com.example.mobishalaassignment.databinding.ActivityMovieDetailsBinding
import com.example.mobishalaassignment.presenter.presenter.Presenter

import com.example.mobishalaassignment.ui.activity.MovieListingActivity
import com.optcrm.optreporting.AppModule

import dagger.Component

import javax.inject.Singleton


/**
 * Created by Sanjay Kumar on 06/02/2022.
 */
@Singleton
@Component(
        modules = arrayOf(
                AppModule::class , NetworkModule::class, PrimitivesModule::class
        )
)
interface ApplicationComponent {

    fun inject(app: Application)

    /**
     * Activities
     */


    fun inject(activity: BaseAppCompatActivity)
    fun inject(activiMovieListingActivity: MovieListingActivity)
    fun inject(activityMovieDetailsBinding: ActivityMovieDetailsBinding)
    /**
     * Fragment
     */


    /**
     * Presenters
     */
     fun inject(presenter: Presenter)

    /**
     * View Model
     */


    /**
     * Adapters
     */


    /**
     * Holders
     */


    /**
     * Others
     */

}