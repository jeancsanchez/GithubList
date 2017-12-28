package com.github.jeancarlos.githublist.domain.interactors;

import com.github.jeancarlos.githublist.domain.base.UseCase;
import com.github.jeancarlos.githublist.domain.model.User;
import com.github.jeancarlos.githublist.domain.respositories.GithubRepository;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Gets the user details use case.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
@SuppressWarnings("SpellCheckingInspection")
public class GetUserDetailsUc extends UseCase<String, User> {

    private GithubRepository githubRepository;

    @Inject
    public GetUserDetailsUc(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    @NotNull
    @Override
    public Observable<User> buildObservable(String nickname) {
        return githubRepository.userDetails(nickname);
    }
}
