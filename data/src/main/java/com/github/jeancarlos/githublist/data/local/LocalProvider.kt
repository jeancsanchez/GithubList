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
     * Saves the current page
     * @param page The current page.
     */
    fun saveCurrentPage(page: Int): Observable<Unit>


    /**
     * Gets the current page.
     */
    fun getCurrentPage(): Observable<Int>
}