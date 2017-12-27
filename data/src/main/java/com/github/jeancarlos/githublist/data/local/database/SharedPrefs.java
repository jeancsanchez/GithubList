package com.github.jeancarlos.githublist.data.local.database;

import android.content.Context;

import com.orhanobut.hawk.Hawk;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


/**
 * This class represents a [SharedPreferences] implementation.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 27/12/17.
 * Jesus loves you.
 */
public class SharedPrefs {

    private final String CURR_PAGE_KEY = "com.github.jeancarlos.githublist.data.local.CURR_PAGE_KEY";

    @Inject
    public SharedPrefs(Context context) {
        Hawk.init(context).build();
    }

    /**
     * Puts the current page index.
     *
     * @param currentPage The current page index.
     */
    public void putCurrentPage(int currentPage) {
        Hawk.put(CURR_PAGE_KEY, currentPage);
    }


    /**
     * Gets the current page index.
     *
     * @return An observable with the current page index.
     */
    public Observable<Integer> getCurrentPage() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                try {
                    emitter.onNext(Hawk.get(CURR_PAGE_KEY, 0));
                    emitter.onComplete();
                } catch (Exception exception) {
                    emitter.onError(exception);
                }
            }
        });
    }
}

