package com.github.jeancarlos.githublist.domain.respositories;

import com.github.jeancarlos.githublist.domain.model.GithubRepo;
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
     *
     * @param page The page number.
     */
    Observable<List<User>> loadUsers(int page);


    /**
     * Search for an unser by query .
     *
     * @param query The query for search.
     * @param page  The page number.
     */
    Observable<List<User>> searchForUser(String query, int page);

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
}
