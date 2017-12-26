package com.github.jeancarlos.githublist.base.di.application

import com.github.jeancarlos.githublist.base.BaseApp
import com.github.jeancarlos.githublist.base.di.activity.ActivityComponent
import dagger.Component
import javax.inject.Singleton

/**
 * This class represents the Dagger application component.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun activityComponent(): ActivityComponent

    fun inject(app: BaseApp)
}