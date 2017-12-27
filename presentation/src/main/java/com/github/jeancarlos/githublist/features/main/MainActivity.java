package com.github.jeancarlos.githublist.features.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.jeancarlos.githublist.R;
import com.github.jeancarlos.githublist.adapters.UserAdapter;
import com.github.jeancarlos.githublist.base.mvp.BaseActivity;
import com.github.jeancarlos.githublist.domain.model.User;

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
            mRecViewUsers.setLayoutManager(new LinearLayoutManager(
                            MainActivity.this,
                            LinearLayoutManager.VERTICAL,
                            false
                    )
            );
        }

        if (mRecViewUsers != null) {
            mRecViewUsers.setAdapter(mAdapter);
        }
    }


    @Override
    public void showNoContent() {
    }

    @Override
    public void showUsers(List<User> users) {
        if (mAdapter != null) {
            mAdapter.setUpItems(users);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onLoadUsers();
    }
}
