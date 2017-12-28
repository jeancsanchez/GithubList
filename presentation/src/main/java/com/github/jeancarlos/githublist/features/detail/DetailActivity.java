package com.github.jeancarlos.githublist.features.detail;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jeancarlos.githublist.R;
import com.github.jeancarlos.githublist.base.mvp.BaseActivity;
import com.github.jeancarlos.githublist.domain.model.GithubRepo;
import com.github.jeancarlos.githublist.domain.model.User;
import com.github.jeancarlos.githublist.features.detail.adapters.RepositoryAdapter;
import com.github.jeancarlos.githublist.utils.PaginationScrollListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * This class represents the User details screen.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 27/12/17.
 * Jesus loves you.
 */
public class DetailActivity extends BaseActivity implements DetailContract.View {

    public static final String EXTRA_USER_NICKNAME =
            "com.github.jeancarlos.githublist.features.detail.EXTRA_USER_NICKNAME";

    @Inject
    DetailContract.Presenter presenter;


    @Nullable
    @BindView(R.id.imgDetailUser)
    ImageView mImgUserPicture;

    @Nullable
    @BindView(R.id.txtDetailName)
    TextView mTxtUserName;

    @Nullable
    @BindView(R.id.txtDetailLocation)
    TextView mTxtUserLocation;

    @Nullable
    @BindView(R.id.txtDetailNickname)
    TextView mTxtUserNickname;

    @Nullable
    @BindView(R.id.txtDetailBio)
    TextView mTxtUserBio;

    @Nullable
    @BindView(R.id.txtDetailRepos)
    TextView mTxtReposCount;

    @Nullable
    @BindView(R.id.txtDetailGists)
    TextView mTxtGistsCount;

    @Nullable
    @BindView(R.id.txtDetailStars)
    TextView mTxtStarsCount;

    @Nullable
    @BindView(R.id.txtDetailFollowers)
    TextView mTxtFollowersCount;

    @Nullable
    @BindView(R.id.recviewDetailRepos)
    RecyclerView mRecViewUsers;

    @Nullable
    @BindView(R.id.swipeDetailRepos)
    SwipeRefreshLayout mSwipeRefresh;

    private RepositoryAdapter mAdapter;
    private String mUserNickname;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    /**
     * Gets a new intent for this class.
     *
     * @param context      The context.
     * @param userNickname The user nickname
     * @return A new intent for Details Activity.
     */
    public static Intent getCallingIntent(Context context, String userNickname) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_USER_NICKNAME, userNickname);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getAppComponent()
                .activityComponent()
                .inject(this);

        presenter.setView(this);
        handleIntent(getIntent());
        setupRecyclerView();
    }

    /**
     * Initializes the recycler view and adapter.
     */
    private void setupRecyclerView() {
        mAdapter = new RepositoryAdapter(DetailActivity.this);

        if (mRecViewUsers != null) {
            LinearLayoutManager manager = new LinearLayoutManager(
                    DetailActivity.this,
                    LinearLayoutManager.VERTICAL,
                    false
            );
            mRecViewUsers.setLayoutManager(manager);
            mRecViewUsers.setAdapter(mAdapter);
            mRecViewUsers.addOnScrollListener(new PaginationScrollListener(manager) {
                @Override
                protected void loadMoreItems() {
                    showRepositoriesLoading();
//                    presenter.onLoadUserRepositories(mUserNickname);
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

    /**
     * Handles the intent.
     *
     * @param intent The target intent
     */
    private void handleIntent(Intent intent) {
        try {
            mUserNickname = intent.getExtras().getString(EXTRA_USER_NICKNAME, "");
            if (mUserNickname.isEmpty()) {
                throw new Exception();
            }

            presenter.onLoadUserDetails(mUserNickname);
            presenter.onLoadUserRepositories(mUserNickname);
        } catch (Exception exception) {
            Toast.makeText(this, getString(R.string.general_error_message), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;
    }

    @Override
    public void showUserDetails(User user) {
        if (mTxtUserName != null) {
            mTxtUserName.setText(user.getName());
        }

        if (mTxtUserNickname != null) {
            mTxtUserNickname.setText(getString(R.string.item_user_nick, user.getLogin()));
        }

        if (mTxtUserLocation != null) {
            mTxtUserLocation.setText(user.getLocation());
        }

        if (mImgUserPicture != null && user.getAvatarUrl() != null) {
            Picasso.with(this).load(user.getAvatarUrl()).into(mImgUserPicture);
        }

        if (mTxtReposCount != null) {
            mTxtReposCount.setText(String.valueOf(user.getPublicRepos()));
        }

        if (mTxtGistsCount != null) {
            mTxtGistsCount.setText(String.valueOf(user.getPublicGists()));
        }

        if (mTxtStarsCount != null) {
            mTxtStarsCount.setText(String.valueOf(user.getFollowing()));
        }

        if (mTxtFollowersCount != null) {
            mTxtFollowersCount.setText(String.valueOf(user.getFollowers()));
        }

        if (mTxtUserBio != null) {
            if (user.getBio() == null || (user.getBio() != null && user.getBio().isEmpty())) {
                mTxtUserBio.setVisibility(View.GONE);
            } else {
                mTxtUserBio.setVisibility(View.VISIBLE);
                mTxtUserBio.setText(user.getBio());
            }
        }
    }

    @Override
    public void showUserRepositories(List<GithubRepo> repositories) {
        if (mAdapter != null) {
            if (mAdapter.getItemCount() > 0) {
                mAdapter.addItems(repositories);
            } else {
                mAdapter.setUpItems(repositories);
            }
        }
    }

    @Override
    public void showRepositoriesLoading() {
        isLoading = true;

        if (mSwipeRefresh != null && !mSwipeRefresh.isRefreshing()) {
            mSwipeRefresh.setRefreshing(true);
        }
    }

    @Override
    public void hideRepositoriesLoading() {
        isLoading = false;
        if (mSwipeRefresh != null) {
            mSwipeRefresh.setRefreshing(false);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
