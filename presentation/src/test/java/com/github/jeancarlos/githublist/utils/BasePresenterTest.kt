package com.github.jeancarlos.githublist.utils

import com.nhaarman.mockito_kotlin.argumentCaptor

/**
 * This class represents a base presentation test.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 29/12/17.
 * Jesus loves you.
 */

open class BasePresenterTest {

    // Captors
    protected val onSubscribeCaptor = argumentCaptor<() -> Unit>()
    protected val onNextCaptor = argumentCaptor<(Any) -> Unit>()
    protected val onErrorCaptor = argumentCaptor<(Throwable?) -> Unit>()
    protected val onCompleteCaptor = argumentCaptor<() -> Unit>()
}
