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

        /**
         * Shows the search result.
         *
         * @param users the user list.
         */
        void showSearchResult(List<User> users);

        /**
         * Shows no content message.
         */
        void showNoContent();
    }

    interface Presenter extends BasePresenter<View> {

        /**
         * Notifies the resume state to presenter.
         */
        void onLoadUsers();


        /**
         * Notifies when a search was fired.
         *
         * @param query The query search.
         */
        void onSearch(String query);
    }
}
