package com.github.jeancarlos.githublist.base

import android.app.Application
import com.github.jeancarlos.githublist.base.di.application.AppComponent

import com.github.jeancarlos.githublist.base.di.application.AppModule
import com.github.jeancarlos.githublist.base.di.application.DaggerAppComponent

/**
 * This class represents the base application.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
class BaseApp : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}
