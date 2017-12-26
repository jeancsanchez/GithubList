package com.github.jeancarlos.githublist.domain.base

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * This class represents the base Use case.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
abstract class UseCase<in PARAMS, RESULT> : Interactor<PARAMS, RESULT> {

    private val compDisposables = CompositeDisposable()

    /**
     * Builds an use case observer.
     * @param params    Use case parameters.
     * @return  Built observable for this use case.
     */
    abstract fun buildObservable(params: PARAMS): Observable<RESULT>

    override fun execute(
            params: PARAMS,
            onSubscribe: (() -> Unit)?,
            onNext: ((result: RESULT) -> Unit)?,
            onError: ((exception: Throwable?) -> Unit)?,
            onComplete: (() -> Unit)?
    ) {
        val disposable = buildObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onSubscribe?.invoke() }
                .subscribeWith(object : DisposableObserver<RESULT>() {
                    override fun onNext(result: RESULT) {
                        onNext?.invoke(result)
                    }

                    override fun onError(exception: Throwable) {
                        onError?.invoke(exception)
                    }

                    override fun onComplete() {
                        onComplete?.invoke()
                    }
                })

        compDisposables.add(disposable)
    }

    override fun dispose() {
        if (compDisposables.isDisposed.not())
            compDisposables.dispose()
    }
}