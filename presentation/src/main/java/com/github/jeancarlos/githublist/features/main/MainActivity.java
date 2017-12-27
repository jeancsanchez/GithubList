package com.github.jeancarlos.githublist.features.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.jeancarlos.githublist.R;
import com.github.jeancarlos.githublist.base.mvp.BaseActivity;
import com.github.jeancarlos.githublist.domain.model.User;
import com.github.jeancarlos.githublist.features.main.adapters.UserAdapter;
import com.github.jeancarlos.githublist.utils.PaginationScrollListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainContract.Presenter presenter;

    @Nullable
    @BindView(R.id.swipeMainUsers)
    SwipeRefreshLayout mSwipeRefresh;

    @Nullable
    @BindView(R.id.recviewMainUsers)
    RecyclerView mRecViewUsers;

    private UserAdapter mAdapter;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAppComponent()
                .activityComponent()
                .inject(this);

        presenter.setView(this);
        setupRecyclerView();

        if (mSwipeRefresh != null) {
            mSwipeRefresh.setOnRefreshListener(() -> presenter.onLoadUsers());
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
    protected void onResume() {
        super.onResume();
        presenter.onLoadUsers();
    }
}
