package com.github.jeancarlos.githublist.features.detail;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jeancarlos.githublist.R;
import com.github.jeancarlos.githublist.base.mvp.BaseActivity;
import com.github.jeancarlos.githublist.domain.model.GithubRepo;
import com.github.jeancarlos.githublist.domain.model.User;
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
    }

    /**
     * Handles the intent.
     *
     * @param intent The target intent
     */
    private void handleIntent(Intent intent) {
        try {
            String userNickname = intent.getExtras().getString(EXTRA_USER_NICKNAME, "");
            if (userNickname.isEmpty()) {
                throw new Exception();
            }

            presenter.onLoadUserDetails(userNickname);
            presenter.onLoadUserRepositories(userNickname);
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
            mTxtUserBio.setText(user.getBio());
        }
    }

    @Override
    public void showUserRepositories(List<GithubRepo> repositories) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
