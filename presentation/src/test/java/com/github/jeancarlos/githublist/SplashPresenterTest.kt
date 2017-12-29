package com.github.jeancarlos.githublist

import com.github.jeancarlos.githublist.domain.interactors.ClearAppUc
import com.github.jeancarlos.githublist.features.splash.SplashContract
import com.github.jeancarlos.githublist.features.splash.SplashPresenter
import com.github.jeancarlos.githublist.utils.BasePresenterTest
import com.github.jeancarlos.githublist.utils.kAny
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.powermock.api.mockito.PowerMockito
import org.powermock.api.mockito.PowerMockito.verifyNoMoreInteractions
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

/**
 * This class stores some test cases for [SplashPresenter].
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 29/12/17.
 * Jesus loves you.
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(
        SplashPresenter::class,
        fullyQualifiedNames = ["com.baeldung.powermockito.introduction.*"]
)
class SplashPresenterTest : BasePresenterTest() {

    @Mock lateinit var view: SplashContract.View

    @Mock lateinit var clearAppUc: ClearAppUc

    private lateinit var splashPresenter: SplashPresenter

    @Before
    fun setUp() {
        splashPresenter = PowerMockito.spy(
                SplashPresenter(
                        clearAppUc = clearAppUc
                ).also { it.view = view }
        )
    }

    /**
     * Test case: When on resume state.
     * Clear all cached data on app and go home.
     */
    @Test
    fun onLoadUsersTest() {

        // Given
        val fakeNickname = "jeancsanchez"


        // When
        splashPresenter.onResume()


        // Then
        verify(clearAppUc)
                .execute(
                        kAny(),
                        kAny(),
                        onNextCaptor.capture(),
                        kAny(),
                        kAny()
                )

        onNextCaptor.firstValue.invoke(Unit)
        verify(view).showHome()

        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(clearAppUc)
    }


    /**
     * Test case: When on destroy state.
     * Dispose all use cases.
     */
    @Test
    fun onDestroyTest() {

        // When
        splashPresenter.onDestroy()


        // Then
        verify(clearAppUc).dispose()

        verifyZeroInteractions(view)
        verifyNoMoreInteractions(clearAppUc)
    }
}