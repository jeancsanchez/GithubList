package com.github.jeancarlos.githublist.data;


import com.github.jeancarlos.githublist.data.local.LocalProvider;
import com.github.jeancarlos.githublist.data.models.DGithubRepo;
import com.github.jeancarlos.githublist.data.models.DUser;
import com.github.jeancarlos.githublist.data.models.mappers.DGithubRepoMapper;
import com.github.jeancarlos.githublist.data.models.mappers.DUserMapper;
import com.github.jeancarlos.githublist.data.remote.RemoteProvider;
import com.github.jeancarlos.githublist.domain.model.GithubRepo;
import com.github.jeancarlos.githublist.domain.model.User;
import com.github.jeancarlos.githublist.domain.respositories.GithubRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * This class represents an implementation of {@link GithubRepository}
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */

@SuppressWarnings("SpellCheckingInspection")
public class GithubRepositoryImpl implements GithubRepository {

    private DGithubRepoMapper dGithubRepoMapper;
    private DUserMapper dUserMapper;
    private LocalProvider localProvider;
    private RemoteProvider remoteProvider;

    @Inject
    public GithubRepositoryImpl(
            RemoteProvider remoteProvider,
            LocalProvider localProvider,
            DUserMapper dUserMapper,
            DGithubRepoMapper dGithubRepoMapper
    ) {
        this.remoteProvider = remoteProvider;
        this.localProvider = localProvider;
        this.dUserMapper = dUserMapper;
        this.dGithubRepoMapper = dGithubRepoMapper;
    }

    @Override
    public Observable<List<User>> loadUsers() {
        return remoteProvider.listUsers(nextUsersPage())
                .map(dUsers -> {
                    saveNextUsersPage(dUsers);
                    return dUserMapper.transform(dUsers);
                });
    }


    @Override
    public Observable<List<User>> searchForUser(String query) {
        return remoteProvider.searchForUser(query, nextUsersPage())
                .map(dUsers -> {
                    saveNextUsersPage(dUsers);
                    return dUserMapper.transform(dUsers);
                });
    }

    @Override
    public Observable<User> userDetails(String nickname) {
        return remoteProvider.userDetails(nickname)
                .map(dUser -> dUserMapper.transform(dUser));
    }

    @Override
    public Observable<List<GithubRepo>> userRepositories(String nickname) {
        return remoteProvider.userRepositories(nickname, nextReposPage())
                .map(dGithubRepos -> {
                    saveNextReposPage(dGithubRepos);
                    return dGithubRepoMapper.transform(dGithubRepos);
                });
    }

    /**
     * Gets the next users page.
     *
     * @return The next page.
     */
    private int nextUsersPage() {
        int nextPage;

        try {
            nextPage = localProvider.getNextUsersPage().blockingFirst();
        } catch (Exception exception) {
            nextPage = 0;
        }

        return nextPage;
    }

    /**
     * Gets the next repositories page.
     *
     * @return The next page.
     */
    private int nextReposPage() {
        int nextPage;

        try {
            nextPage = localProvider.getNextReposPage().blockingFirst();
        } catch (Exception exception) {
            nextPage = 0;
        }

        return nextPage;
    }

    /**
     * Saves the next users page, based the last id.
     *
     * @param dUsers The users list.
     */
    private void saveNextUsersPage(List<DUser> dUsers) {
        localProvider
                .saveNextUsersPage(dUsers.get(dUsers.size() - 1).getId())
                .blockingFirst();
    }


    /**
     * Saves the next repos page, based the last id.
     *
     * @param dRepositories The repositories list.
     */
    private void saveNextReposPage(List<DGithubRepo> dRepositories) {
        localProvider
                .saveNextReposPage(dRepositories.get(dRepositories.size() - 1).getId())
                .blockingFirst();
    }
}
