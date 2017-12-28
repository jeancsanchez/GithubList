package com.github.jeancarlos.githublist.features.splash;


import android.content.Intent;
import android.os.Bundle;

import com.github.jeancarlos.githublist.base.mvp.BaseActivity;
import com.github.jeancarlos.githublist.features.main.MainActivity;

import javax.inject.Inject;

/**
 * This class represents the Splash screen. We just need to call for {@link MainActivity} when this
 * get ready. This approach prevents the blank screen at application launch.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 27/12/17.
 * Jesus loves you.
 */
public class SplashActivity extends BaseActivity implements SplashContract.View {

    @Inject
    SplashContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getAppComponent()
                .activityComponent()
                .inject(this);

        presenter.setView(this);
    }


    @Override
    public void showHome() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
