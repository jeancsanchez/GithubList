package com.github.jeancarlos.githublist.base.di.fragment

import com.github.jeancarlos.githublist.base.di.scopes.FragmentScope
import dagger.Subcomponent

/**
 * This class represents the Dagger fragment component.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
@FragmentScope
@Subcomponent(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
}