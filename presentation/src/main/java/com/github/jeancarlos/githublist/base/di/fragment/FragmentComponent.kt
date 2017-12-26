package com.github.jeancarlos.githublist.base.di.fragment

import com.github.jeancarlos.githublist.base.di.scopes.PerFragment
import dagger.Subcomponent

/**
 * This class represents the Dagger fragment component.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
@PerFragment
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
}