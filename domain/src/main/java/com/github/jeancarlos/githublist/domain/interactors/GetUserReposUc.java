package com.github.jeancarlos.githublist.domain.interactors;

import com.github.jeancarlos.githublist.domain.base.UseCase;
import com.github.jeancarlos.githublist.domain.model.GithubRepo;
import com.github.jeancarlos.githublist.domain.respositories.GithubRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * List all user repositories use case.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
@SuppressWarnings("SpellCheckingInspection")
public class GetUserReposUc extends UseCase<String, List<GithubRepo>> {

    private GithubRepository githubRepository;

    @Inject
    public GetUserReposUc(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    @NotNull
    @Override
    public Observable<List<GithubRepo>> buildObservable(String nickname) {
        return githubRepository.userRepositories(nickname);
    }
}
