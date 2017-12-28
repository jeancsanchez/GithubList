package com.github.jeancarlos.githublist.features.detail;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.github.jeancarlos.githublist.R;
import com.github.jeancarlos.githublist.base.mvp.BaseActivity;
import com.github.jeancarlos.githublist.domain.model.GithubRepo;
import com.github.jeancarlos.githublist.domain.model.User;

import java.util.List;

import javax.inject.Inject;

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
    public void showUserDetails(User user) {

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
