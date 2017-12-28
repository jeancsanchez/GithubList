package com.github.jeancarlos.githublist.domain.respositories;

import com.github.jeancarlos.githublist.domain.model.GithubRepo;
import com.github.jeancarlos.githublist.domain.model.User;

import java.util.List;

import io.reactivex.Observable;
import kotlin.Unit;

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


    /**
     * Search for an unser by query .
     *
     * @param query The query for search.
     */
    Observable<List<User>> searchForUser(String query);

    /**
     * Load the user details.
     *
     * @param nickname The user nickname.
     */
    Observable<User> userDetails(String nickname);


    /**
     * Load the user repositories.
     *
     * @param nickname The user nickname.
     */
    Observable<List<GithubRepo>> userRepositories(String nickname);

    /**
     * Clear the application data.
     */
    Observable<Unit> clearData();
}
