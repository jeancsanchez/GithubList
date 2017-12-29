package com.github.jeancarlos.githublist.domain

import com.github.jeancarlos.githublist.domain.interactors.ClearAppUc
import com.github.jeancarlos.githublist.domain.respositories.GithubRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * This class stores some test cases for [ClearAppUcTest].
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 29/12/17.
 * Jesus loves you.
 */
@RunWith(MockitoJUnitRunner::class)
class ClearAppUcTest {

    @Mock private lateinit var githubRepository: GithubRepository
    @InjectMocks private lateinit var useCase: ClearAppUc

    @Before
    fun setUp() {

        whenever(githubRepository.clearData())
                .thenReturn(Observable.just(Unit))
    }

    @Test
    fun onlyExecuteUseCaseAndNeededMethods() {

        // Given Test params, when execute:
        useCase.buildObservable(Unit)

        // Then, build observable must be the only interaction.
        Mockito.verify(githubRepository).clearData()
        Mockito.verifyNoMoreInteractions(githubRepository)
    }
}