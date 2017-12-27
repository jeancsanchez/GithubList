package com.github.jeancarlos.githublist.base.di.application


import android.content.Context
import com.github.jeancarlos.githublist.base.BaseApp
import com.github.jeancarlos.githublist.data.local.database.SharedPrefs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * This class represents a Dagger module with the application dependencies.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
@Module
class AppModule(private val application: BaseApp) {

    @Provides
    @Singleton
    internal fun providesApplication(): BaseApp = application

    @Provides
    @Singleton
    internal fun providesContext(): Context = application.applicationContext

    @Provides
    @Singleton
    internal fun providesSharedPrefs(context: Context): SharedPrefs = SharedPrefs(context)
}
