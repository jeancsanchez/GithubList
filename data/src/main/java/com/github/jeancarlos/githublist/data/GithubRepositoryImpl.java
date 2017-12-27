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
import io.reactivex.functions.Function;

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
    public Observable<List<User>> loadUsers(int page) {
        return remoteProvider.listUsers(page)
                .map(new Function<List<DUser>, List<User>>() {
                    @Override
                    public List<User> apply(List<DUser> dUsers) throws Exception {
                        return dUserMapper.transform(dUsers);
                    }
                });
    }

    @Override
    public Observable<List<User>> searchForUser(String query, int page) {
        return remoteProvider.searchForUser(query, page)
                .map(new Function<List<DUser>, List<User>>() {
                    @Override
                    public List<User> apply(List<DUser> dUsers) throws Exception {
                        return dUserMapper.transform(dUsers);
                    }
                });
    }

    @Override
    public Observable<User> userDetails(String nickname) {
        return remoteProvider.userDetails(nickname)
                .map(new Function<DUser, User>() {
                    @Override
                    public User apply(DUser dUser) throws Exception {
                        return dUserMapper.transform(dUser);
                    }
                });
    }

    @Override
    public Observable<List<GithubRepo>> userRepositories(String nickname) {
        return remoteProvider.userRepositories(nickname)
                .map(new Function<List<DGithubRepo>, List<GithubRepo>>() {
                    @Override
                    public List<GithubRepo> apply(List<DGithubRepo> dGithubRepos) throws Exception {
                        return dGithubRepoMapper.transform(dGithubRepos);
                    }
                });
    }
}
