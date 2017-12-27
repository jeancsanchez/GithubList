package com.github.jeancarlos.githublist.data;

import com.github.jeancarlos.githublist.domain.model.User;
import com.github.jeancarlos.githublist.domain.respositories.GithubRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import static java.util.Collections.singletonList;

/**
 * This class represents an implementation of {@link GithubRepository}
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */

@SuppressWarnings("SpellCheckingInspection")
public class GithubRepositoryImpl implements GithubRepository {

    @Inject
    public GithubRepositoryImpl() {
    }

    @Override
    public Observable<List<User>> loadUsers() {
        return Observable.just(singletonList(new User("Jean")));
    }
}
