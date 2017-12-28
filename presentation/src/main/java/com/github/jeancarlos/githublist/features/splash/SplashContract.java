package com.github.jeancarlos.githublist.features.splash;

import com.github.jeancarlos.githublist.base.mvp.BasePresenter;
import com.github.jeancarlos.githublist.base.mvp.BaseView;

/**
 * This class represents the contract between Splash View and Splash Presenter.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 27/12/17.
 * Jesus loves you.
 */
public interface SplashContract {

    interface View extends BaseView {

        /**
         * Shows the home.
         */
        void showHome();
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * Notifies the resume state to presenter.
         */
        void onResume();
    }
}
