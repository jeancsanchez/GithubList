package com.github.jeancarlos.githublist.features.splash

import com.github.jeancarlos.githublist.base.di.scopes.PerActivity
import com.github.jeancarlos.githublist.domain.interactors.GetUserDetailsUc
import com.github.jeancarlos.githublist.domain.interactors.GetUserReposUc
import com.github.jeancarlos.githublist.features.detail.DetailContract
import javax.inject.Inject

/**
 * This class represents the Details Presenter
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
@PerActivity
class DetailPresenter
@Inject constructor(
        private val getUserReposUc: GetUserReposUc,
        private val getUserDetailsUc: GetUserDetailsUc
) : DetailContract.Presenter {

    override var view: DetailContract.View? = null

    override fun onLoadUserDetails(login: String?) {
        view?.showLoading()

        getUserDetailsUc.execute(
                params = login,
                onNext = {
                    view?.hideLoading()
                    view?.showUserDetails(it)
                },
                onError = {
                    view?.hideLoading()
                    it?.let { view?.showError(it) }
                }
        )
    }

    override fun onLoadUserRepositories(login: String) {
        view?.showRepositoriesLoading()

        getUserReposUc.execute(
                params = login,
                onNext = {
                    view?.hideRepositoriesLoading()
                    view?.showUserRepositories(it)
                },
                onError = {
                    view?.hideRepositoriesLoading()
                    it?.let { view?.showError(it) }
                    it?.printStackTrace()
                }
        )
    }

    override fun onDestroy() {
        getUserDetailsUc.dispose()
        getUserReposUc.dispose()
    }
}
