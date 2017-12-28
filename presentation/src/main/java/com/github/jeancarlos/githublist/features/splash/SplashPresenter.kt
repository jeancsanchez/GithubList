package com.github.jeancarlos.githublist.features.splash

import com.github.jeancarlos.githublist.base.di.scopes.PerActivity
import com.github.jeancarlos.githublist.domain.interactors.ClearAppUc
import javax.inject.Inject

/**
 * This class represents the Splash Presenter
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
@PerActivity
class SplashPresenter
@Inject constructor(
        private val clearAppUc: ClearAppUc
) : SplashContract.Presenter {

    override var view: SplashContract.View? = null

    override fun onResume() {
        clearAppUc.execute(
                params = Unit,
                onNext = { view?.showHome() },
                onError = { view?.showHome() }
        )
    }

    override fun onDestroy() {
        clearAppUc.dispose()
    }
}
