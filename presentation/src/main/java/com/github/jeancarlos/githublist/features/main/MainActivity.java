package com.github.jeancarlos.githublist.features.main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.github.jeancarlos.githublist.R;
import com.github.jeancarlos.githublist.base.mvp.BaseActivity;
import com.github.jeancarlos.githublist.domain.model.User;
import com.github.jeancarlos.githublist.features.main.adapters.UserAdapter;
import com.github.jeancarlos.githublist.utils.PaginationScrollListener;
import com.github.jeancarlos.githublist.utils.SuggestionProvider;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View, SearchView.OnQueryTextListener {

    @Inject
    MainContract.Presenter presenter;

    @Nullable
    @BindView(R.id.swipeMainUsers)
    SwipeRefreshLayout mSwipeRefresh;

    @Nullable
    @BindView(R.id.recviewMainUsers)
    RecyclerView mRecViewUsers;

    private SearchView mSearchView;

    private UserAdapter mAdapter;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleIntent(getIntent());

        getAppComponent()
                .activityComponent()
                .inject(this);


        presenter.setView(this);
        setupRecyclerView();

        if (mSwipeRefresh != null) {
            mSwipeRefresh.setOnRefreshListener(() -> presenter.onLoadUsers());
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    /**
     * Handle specifics essential for Githubs search
     *
     * @param intent The intent.
     */
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            saveRecentQuery(query);

        } else if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            mSearchView.setQuery(query, false);
            onQueryTextSubmit(query);
        }
    }

    /**
     * Initializes the recycler view and adapter.
     */
    private void setupRecyclerView() {
        mAdapter = new UserAdapter(MainActivity.this);

        if (mRecViewUsers != null) {
            LinearLayoutManager manager = new LinearLayoutManager(
                    MainActivity.this,
                    LinearLayoutManager.VERTICAL,
                    false
            );

            mRecViewUsers.setLayoutManager(manager);
            mRecViewUsers.setAdapter(mAdapter);
            mRecViewUsers.addOnScrollListener(new PaginationScrollListener(manager) {
                @Override
                protected void loadMoreItems() {
                    showLoading();
                    presenter.onLoadUsers();
                }

                @Override
                public boolean isLastPage() {
                    return isLastPage;
                }

                @Override
                public boolean isLoading() {
                    return isLoading;
                }
            });
        }
    }


    @Override
    public void showLoading() {
        isLoading = true;
        if (mSwipeRefresh != null) {
            mSwipeRefresh.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        isLoading = false;
        if (mSwipeRefresh != null) {
            mSwipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void showNoContent() {
        if (mAdapter != null && mAdapter.getItemCount() > 0) {
            isLastPage = true;
        } else {
            // TODO: Show no content message.
        }
    }

    @Override
    public void showUsers(List<User> users) {
        if (mAdapter != null) {
            if (mAdapter.getItemCount() > 0) {
                mAdapter.addItems(users);
            } else {
                mAdapter.setUpItems(users);
            }
        }
    }

    @Override
    public void showSearchResult(List<User> users) {
        if (mAdapter != null) {
            mAdapter.setUpItems(users);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search_view_githubs);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView = (SearchView) searchItem.getActionView();
        if (searchManager != null) {
            mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            mSearchView.setQueryHint(getResources().getString(R.string.title_search_view_github));
            mSearchView.setOnQueryTextListener(this);
        }

        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                if (mAdapter != null) {
                    mAdapter.cacheCurrentItems();
                }

                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                if (mAdapter != null) {
                    mAdapter.loadCachedItems();
                }
                return true;
            }
        });

        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        if (mAdapter != null && !query.isEmpty()) {
            presenter.onSearch(query);
            saveRecentQuery(query);
            showNoContent();
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        if (mAdapter != null && !query.isEmpty()) {
            presenter.onSearch(query);
            saveRecentQuery(query);
            showNoContent();
        }
        return true;
    }


    /**
     * Method that saves the queries typed by user on list of the recent searches
     *
     * @param query The text typed by user
     */
    private void saveRecentQuery(String query) {
        SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                SuggestionProvider.AUTHORITY, SuggestionProvider.MODE);

        // Clear the suggestions list if the list is greater then 5
        if (mSearchView.getSuggestionsAdapter().getCount() > 5) {
            suggestions.clearHistory();
        }

        suggestions.saveRecentQuery(query, null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onLoadUsers();
    }
}
