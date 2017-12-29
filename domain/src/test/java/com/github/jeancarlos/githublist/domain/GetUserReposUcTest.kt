package com.github.jeancarlos.githublist.domain

import com.github.jeancarlos.githublist.domain.interactors.GetUserReposUc
import com.github.jeancarlos.githublist.domain.model.GithubRepo
import com.github.jeancarlos.githublist.domain.respositories.GithubRepository
import com.github.jeancarlos.githublist.domain.utils.kEq
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner

/**
 * This class stores some test cases for [GetUserReposUcTest].
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 29/12/17.
 * Jesus loves you.
 */
@RunWith(MockitoJUnitRunner::class)
class GetUserReposUcTest {

    @Mock private lateinit var githubRepository: GithubRepository
    @InjectMocks private lateinit var useCase: GetUserReposUc

    companion object {

        private const val FAKE_NICKNAME = "jeancsanchez"

        @JvmField
        val FAKE_REPO = GithubRepo(
                id = 1,
                name = "Fake repository",
                private = false
        )

        @JvmField
        val FAKE_REPO_LIST = listOf(FAKE_REPO)
    }

    @Before
    fun setUp() {

        whenever(githubRepository.userRepositories(FAKE_NICKNAME))
                .thenReturn(Observable.just(FAKE_REPO_LIST))
    }

    @Test
    fun onlyExecuteUseCaseAndNeededMethods() {

        // Given Test params, when execute:
        useCase.buildObservable(FAKE_NICKNAME)

        // Then, build observable must be the only interaction.
        verify(githubRepository).userRepositories(kEq(FAKE_NICKNAME))
        verifyNoMoreInteractions(githubRepository)
    }
}