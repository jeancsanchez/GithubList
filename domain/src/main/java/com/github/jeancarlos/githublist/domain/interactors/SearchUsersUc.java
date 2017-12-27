package com.github.jeancarlos.githublist.domain.interactors;

import com.github.jeancarlos.githublist.domain.base.UseCase;
import com.github.jeancarlos.githublist.domain.model.User;
import com.github.jeancarlos.githublist.domain.respositories.GithubRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Search for users use case.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
@SuppressWarnings("SpellCheckingInspection")
public class SearchUsersUc extends UseCase<String, List<User>> {

    private GithubRepository githubRepository;

    @Inject
    public SearchUsersUc(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    @NotNull
    @Override
    public Observable<List<User>> buildObservable(String query) {
        return githubRepository.searchForUser(query);
    }
}
