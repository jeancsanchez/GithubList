package com.github.jeancarlos.githublist.base.di.activity

import com.github.jeancarlos.githublist.features.main.MainContract
import com.github.jeancarlos.githublist.features.main.MainPresenter
import dagger.Binds
import dagger.Module


/**
 * This class represents a Dagger module with the activities dependencies.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
@Module
abstract class ActivityModule {

    @Binds
    abstract fun bindsMainPresenter(mainPresenter: MainPresenter): MainContract.Presenter
}