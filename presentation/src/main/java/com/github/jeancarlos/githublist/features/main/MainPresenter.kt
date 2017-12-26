package com.github.jeancarlos.githublist.features.main

import com.github.jeancarlos.githublist.base.di.scopes.PerActivity
import javax.inject.Inject

/**
 * This class represents the Main Presenter
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
@PerActivity
class MainPresenter
@Inject constructor(

) : MainContract.Presenter {

    override var view: MainContract.View? = null

    override fun onResume() {
        view?.showUsers(emptyList())
    }
}
