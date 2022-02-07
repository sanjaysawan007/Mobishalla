package com.optcrm.optreporting

import android.app.Application
import com.example.mobishalaassignment.architecture.application.ArchitectureApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Sanjay Kumar on 06/02/2022.
 */
@Module
class AppModule(private val app: ArchitectureApp) {
    @Provides
    @Singleton
    fun provideApplication(): Application {
        return app
    }

    @Provides
    @Singleton
    fun provideArchitecture(): ArchitectureApp {
        return app
    }


}