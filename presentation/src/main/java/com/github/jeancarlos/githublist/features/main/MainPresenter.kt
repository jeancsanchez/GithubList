package com.github.jeancarlos.githublist.features.main

import com.github.jeancarlos.githublist.base.di.scopes.PerActivity
import com.github.jeancarlos.githublist.domain.interactors.GetUsersUc
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
        private val getUsersUc: GetUsersUc
) : MainContract.Presenter {

    override var view: MainContract.View? = null

    override fun onLoadUsers() {
        view?.showLoading()

        getUsersUc.execute(
                params = Unit,
                onNext = {
                    view?.hideLoading()

                    if (it.isEmpty()) {
                        view?.showNoContent()
                    } else {
                        view?.showUsers(it)
                    }
                },
                onError = {
                    view?.hideLoading()
                    it?.let { view?.showError(it) } ?: view?.showNoContent()
                }
        )
    }
}
