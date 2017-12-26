package com.github.jeancarlos.githublist.base.di.activity

import com.github.jeancarlos.githublist.base.di.fragment.FragmentComponent
import com.github.jeancarlos.githublist.base.di.scopes.PerActivity
import com.github.jeancarlos.githublist.features.main.MainActivity
import dagger.Subcomponent

/**
 * This class represents the Dagger activity component.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun fragmentComponent(): FragmentComponent

    fun inject(mainActivity: MainActivity)
}