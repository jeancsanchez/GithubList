package com.github.jeancarlos.githublist.base.di.repositories

import com.github.jeancarlos.githublist.data.GithubRepositoryImpl
import com.github.jeancarlos.githublist.data.local.LocalProvider
import com.github.jeancarlos.githublist.data.local.database.LocalProviderImpl
import com.github.jeancarlos.githublist.data.remote.RemoteProvider
import com.github.jeancarlos.githublist.data.remote.retrofit.RetrofitImpl
import com.github.jeancarlos.githublist.domain.respositories.GithubRepository
import dagger.Binds
import dagger.Module

/**
 * This class represents a Dagger module with the repositories dependencies.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsGithubRepository(githubRepositoryImpl: GithubRepositoryImpl): GithubRepository

    @Binds
    abstract fun bindsRemoteProvider(retrofitImpl: RetrofitImpl): RemoteProvider

    @Binds
    abstract fun bindsLocalProvider(localProviderImpl: LocalProviderImpl): LocalProvider
}