package com.github.jeancarlos.githublist.features.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.jeancarlos.githublist.R;
import com.github.jeancarlos.githublist.domain.model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void showUsers(List<User> users) {

    }
}
