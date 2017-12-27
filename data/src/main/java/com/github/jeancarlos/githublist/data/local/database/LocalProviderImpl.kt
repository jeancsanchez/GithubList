package com.github.jeancarlos.githublist.data.local.database

import com.github.jeancarlos.githublist.data.local.LocalProvider
import io.reactivex.Observable
import javax.inject.Inject

/**
 * This class implements the [LocalProvider].
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 27/12/17.
 * Jesus loves you.
 */

class LocalProviderImpl
@Inject constructor(
        private val sharedPrefs: SharedPrefs
) : LocalProvider {

    override fun saveCurrentPage(page: Int): Observable<Unit> {
        return Observable.fromCallable { sharedPrefs.putCurrentPage(page) }
    }

    override fun getCurrentPage(): Observable<Int> = sharedPrefs.currentPage
}