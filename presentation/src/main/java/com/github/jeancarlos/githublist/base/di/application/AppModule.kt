package com.github.jeancarlos.githublist.base.di.application


import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.github.jeancarlos.githublist.base.BaseApp
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
    internal fun providesApplication(): BaseApp {
        return application
    }

    @Provides
    @Singleton
    internal fun providesContext(): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun providesSharedPrefs(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}
