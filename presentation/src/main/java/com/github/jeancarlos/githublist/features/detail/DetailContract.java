package com.github.jeancarlos.githublist.features.detail;

import com.github.jeancarlos.githublist.base.mvp.BasePresenter;
import com.github.jeancarlos.githublist.base.mvp.BaseView;
import com.github.jeancarlos.githublist.domain.model.GithubRepo;
import com.github.jeancarlos.githublist.domain.model.User;

import java.util.List;

/**
 * This class represents the contract between Details View and Details Presenter.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 27/12/17.
 * Jesus loves you.
 */
public interface DetailContract {

    interface View extends BaseView {

        /**
         * Shows the user details.
         *
         * @param user The user.
         */
        void showUserDetails(User user);

        /**
         * Show user repositories
         *
         * @param repositories The repositories list.
         */
        void showUserRepositories(List<GithubRepo> repositories);
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * Loads the user details.
         */
        void onLoadUserDetails(String login);

        /**
         * Loads the user repositories
         */
        void onLoadUserRepositories(String login);
    }
}
