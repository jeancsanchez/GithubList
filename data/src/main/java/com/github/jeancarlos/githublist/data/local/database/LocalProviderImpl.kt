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

    override fun saveNextUsersPage(page: Int): Observable<Unit> {
        return Observable.fromCallable { sharedPrefs.putNextUsersPage(page) }
    }

    override fun getNextUsersPage(): Observable<Int> = sharedPrefs.nextUsersPage

    override fun saveNextReposPage(page: Int): Observable<Unit> {
        return Observable.fromCallable { sharedPrefs.putNextReposPage(page) }
    }

    override fun getNextReposPage(): Observable<Int> = sharedPrefs.nextReposPage

    override fun clear(): Observable<Unit> = sharedPrefs.clearAll()
}