package com.github.jeancarlos.githublist.features.main;

import android.os.Bundle;

import com.github.jeancarlos.githublist.R;
import com.github.jeancarlos.githublist.base.mvp.BaseActivity;
import com.github.jeancarlos.githublist.domain.model.User;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAppComponent()
                .activityComponent()
                .inject(this);

        presenter.setView(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void showUsers(List<User> users) {
    }
}
