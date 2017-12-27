package com.github.jeancarlos.githublist.base.mvp;

import android.annotation.SuppressLint;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.github.jeancarlos.githublist.base.BaseApp;
import com.github.jeancarlos.githublist.base.di.application.AppComponent;

import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;

/**
 * This class represents a base {@link AppCompatActivity}
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements BaseView {

    /**
     * Gets the {@link AppComponent} instance.
     *
     * @return The {@link AppComponent} instance.
     */
    protected AppComponent getAppComponent() {
        return ((BaseApp) getApplication()).getComponent();
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this, getWindow().getDecorView());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(@NotNull Throwable exception) {

    }
}
