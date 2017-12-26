package com.github.jeancarlos.githublist.base.di.activity

import com.github.jeancarlos.githublist.base.di.fragment.FragmentComponent
import com.github.jeancarlos.githublist.base.di.scopes.ActivityScope
import dagger.Subcomponent

/**
 * This class represents the Dagger activity component.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun fragmentComponent(): FragmentComponent
}