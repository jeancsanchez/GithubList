package com.github.jeancarlos.githublist.features.main;

import com.github.jeancarlos.githublist.base.mvp.BasePresenter;
import com.github.jeancarlos.githublist.base.mvp.BaseView;
import com.github.jeancarlos.githublist.domain.model.User;

import java.util.List;

/**
 * This class represents the contract between Main View and Main Presenter.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
public interface MainContract {

    interface View extends BaseView {

        /**
         * Shows the user list.
         *
         * @param users the user list.
         */
        void showUsers(List<User> users);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * Notify the resume state to presenter.
         */
        void onResume();
    }
}
