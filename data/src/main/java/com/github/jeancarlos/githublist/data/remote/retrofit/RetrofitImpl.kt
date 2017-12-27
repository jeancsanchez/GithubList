package com.github.jeancarlos.githublist.data.remote.retrofit

import com.github.jeancarlos.githublist.data.models.DGithubRepo
import com.github.jeancarlos.githublist.data.models.DUser
import com.github.jeancarlos.githublist.data.models.mappers.DUserMapper
import com.github.jeancarlos.githublist.data.remote.RemoteProvider
import com.topracha.jeancsanchez.data.repository.rest.retrofit.GithubAPI
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This class represents an implementation of [RemoteProvider].
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 27/12/17.
 * Jesus loves you.
 */
@Singleton
class RetrofitImpl
@Inject constructor() : RemoteProvider {

    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }

    private var service: GithubAPI
    private val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

    init {
        service = retrofit.build().create(GithubAPI::class.java)
    }

    override fun listUsers(page: Int): Observable<List<DUser>> {
        return service.listUsers(page = page)
    }

    override fun userDetails(nickname: String): Observable<DUser> {
        return service.userDetailsByNickname(nickname)
    }

    override fun searchForUser(query: String, page: Int): Observable<List<DUser>> {
        return service.listUsers(query = query, page = page)
    }

    override fun userRepositories(nickname: String): Observable<List<DGithubRepo>> {
        return service.userRepositories(nickname)
    }
}