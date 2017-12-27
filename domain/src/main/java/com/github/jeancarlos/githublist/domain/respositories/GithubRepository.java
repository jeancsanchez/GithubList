package com.github.jeancarlos.githublist.domain.respositories;

import com.github.jeancarlos.githublist.domain.model.User;

import java.util.List;

import io.reactivex.Observable;

/**
 * This class represents the Github repository.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
@SuppressWarnings("SpellCheckingInspection")
public interface GithubRepository {

    /**
     * Loads all Github users.
     */
    Observable<List<User>> loadUsers();
}
