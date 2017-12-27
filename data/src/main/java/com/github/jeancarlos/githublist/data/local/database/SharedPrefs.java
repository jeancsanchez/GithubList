package com.github.jeancarlos.githublist.data.local.database;

import android.content.Context;

import com.orhanobut.hawk.Hawk;

import javax.inject.Inject;

import io.reactivex.Observable;
import kotlin.Unit;


/**
 * This class represents a [SharedPreferences] implementation.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 27/12/17.
 * Jesus loves you.
 */
public class SharedPrefs {

    private final String NEXT_USERS_PAGE_KEY = "com.github.jeancarlos.githublist.data.local.NEXT_USERS_PAGE_KEY";
    private final String NEXT_REPOS_PAGE_KEY = "com.github.jeancarlos.githublist.data.local.NEXT_REPOS_PAGE_KEY";

    @Inject
    public SharedPrefs(Context context) {
        Hawk.init(context).build();
    }

    /**
     * Puts the next users page index.
     *
     * @param nextPage The next page index.
     */
    public void putNextUsersPage(int nextPage) {
        Hawk.put(NEXT_USERS_PAGE_KEY, nextPage);
    }


    /**
     * Gets the next users page index.
     *
     * @return An observable with the next page index.
     */
    public Observable<Integer> getNextUsersPage() {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(Hawk.get(NEXT_USERS_PAGE_KEY, 0));
                emitter.onComplete();
            } catch (Exception exception) {
                emitter.onError(exception);
            }
        });
    }

    /**
     * Puts the next repositories page index.
     *
     * @param nextPage The next page index.
     */
    public void putNextReposPage(int nextPage) {
        Hawk.put(NEXT_REPOS_PAGE_KEY, nextPage);
    }


    /**
     * Gets the next repositories page index.
     *
     * @return An observable with the next page index.
     */
    public Observable<Integer> getNextReposPage() {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(Hawk.get(NEXT_REPOS_PAGE_KEY, 0));
                emitter.onComplete();
            } catch (Exception exception) {
                emitter.onError(exception);
            }
        });
    }

    /**
     * Clear all the data.
     */
    public Observable<Unit> clearAll() {
        return Observable.fromCallable(() -> {
            Hawk.delete(NEXT_REPOS_PAGE_KEY);
            Hawk.delete(NEXT_USERS_PAGE_KEY);
            return null;
        });
    }
}

