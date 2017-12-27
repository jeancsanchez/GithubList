package com.github.jeancarlos.githublist.data.remote

import com.github.jeancarlos.githublist.data.models.DGithubRepo
import com.github.jeancarlos.githublist.data.models.DUser
import io.reactivex.Observable

/**
 * This class represents a remote data provider.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 27/12/17.
 * Jesus loves you.
 */
interface RemoteProvider {


    /**
     * Loads all remote Github users.
     *
     * @param page The page number.
     */
    fun listUsers(page: Int): Observable<List<DUser>>


    /**
     * Search for an remote user by query .
     *
     * @param query The query for search.
     * @param page  The page number.
     */
    fun searchForUser(query: String, page: Int): Observable<List<DUser>>

    /**
     * Load the remote user details.
     *
     * @param nickname The user nickname.
     */
    fun userDetails(nickname: String): Observable<DUser>


    /**
     * Load the remote user repositories.
     *
     * @param nickname The user nickname.
     * @param page  The page number.
     */
    fun userRepositories(nickname: String, page: Int): Observable<List<DGithubRepo>>
}