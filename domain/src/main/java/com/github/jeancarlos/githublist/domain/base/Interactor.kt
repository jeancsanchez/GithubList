package com.github.jeancarlos.githublist.domain.base

/**
 * This class represents the base Interactor.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
internal interface Interactor<in PARAMS, out RESULT> {

    fun execute(
            params: PARAMS,
            onSubscribe: (() -> Unit)? = null,
            onNext: ((result: RESULT) -> Unit)? = null,
            onError: ((exception: Throwable?) -> Unit)? = null,
            onComplete: (() -> Unit)? = null
    )

    fun dispose()
}