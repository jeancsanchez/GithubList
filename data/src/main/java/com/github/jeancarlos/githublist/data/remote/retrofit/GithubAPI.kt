package com.topracha.jeancsanchez.data.repository.rest.retrofit

import com.github.jeancarlos.githublist.data.models.DGithubRepo
import com.github.jeancarlos.githublist.data.models.DUser
import com.github.jeancarlos.githublist.data.remote.retrofit.PER_PAGE
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * This class represents the Github API.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 27/12/17.
 * Jesus loves you.
 */
interface GithubAPI {

    /**
     * Searches for users by query.
     * @param query The search query.
     * @param page The initial page.
     * @param perPage The total per page.
     */
    @GET("search/users")
    fun listUsers(
            @Query("q") query: String? = "",
            @Query("page") page: Int? = 1,
            @Query("per_page") perPage: Int? = PER_PAGE
    ): Observable<List<DUser>>

    /**
     * Gets the user details by the nickname.
     * @param nickname The user nickname.
     */
    @GET("users/{nickname}")
    fun userDetailsByNickname(
            @Path("nickname") nickname: String
    ): Observable<DUser>

    /**
     * Gets the user repositories by the user nickname.
     * @param nickname The user nickname.
     */
    @GET("users/{nickname}/repos")
    fun userRepositories(
            @Path("nickname") nickname: String
    ): Observable<List<DGithubRepo>>
}