package com.github.jeancarlos.githublist.data.local

import io.reactivex.Observable

/**
 * This class represents a local data provider.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 27/12/17.
 * Jesus loves you.
 */
interface LocalProvider {

    /**
     * Saves the next users page
     * @param page The current page.
     */
    fun saveNextUsersPage(page: Int): Observable<Unit>

    /**
     * Gets the next users page.
     */
    fun getNextUsersPage(): Observable<Int>

    /**
     * Saves the next repositories page
     * @param page The current page.
     */
    fun saveNextReposPage(page: Int): Observable<Unit>

    /**
     * Gets the next repositories page.
     */
    fun getNextReposPage(): Observable<Int>

    /**
     * Clear all data.
     */
    fun clear(): Observable<Unit>
}