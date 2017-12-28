package com.github.jeancarlos.githublist.base.mvp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.jeancarlos.githublist.R;
import com.github.jeancarlos.githublist.base.BaseApp;
import com.github.jeancarlos.githublist.base.di.application.AppComponent;

import org.jetbrains.annotations.NotNull;

import java.net.UnknownHostException;

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

    private ProgressDialog mProgressDialog;

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
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setTitle(null);
            mProgressDialog.setMessage(getString(R.string.general_loading));
        }

        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showError(@NotNull Throwable exception) {
        String messageTitle = exception.getMessage();
        MaterialDialog.Builder dialog =
                new MaterialDialog.Builder(this)
                        .title(messageTitle);

        if (messageTitle.contains("403")) {
            dialog.content(getString(R.string.general_limit_requests))
                    .show();
            return;
        }

        if (exception instanceof UnknownHostException || messageTitle.contains("404")) {
            dialog.content(getString(R.string.general_error_connection))
                    .show();
            return;
        }

        dialog.content(getString(R.string.general_error_message))
                .show();
    }
}
