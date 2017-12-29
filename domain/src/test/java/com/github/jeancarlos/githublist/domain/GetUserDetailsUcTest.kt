package com.github.jeancarlos.githublist.domain

import com.github.jeancarlos.githublist.domain.interactors.GetUserDetailsUc
import com.github.jeancarlos.githublist.domain.model.User
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
 * This class stores some test cases for [GetUserDeetailsUc].
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 29/12/17.
 * Jesus loves you.
 */
@RunWith(MockitoJUnitRunner::class)
class GetUserDetailsUcTest {

    @Mock private lateinit var githubRepository: GithubRepository
    @InjectMocks private lateinit var useCase: GetUserDetailsUc

    companion object {

        private const val FAKE_NICKNAME = "jeancsanchez"

        @JvmField
        val FAKE_USER = User(
                id = 1,
                name = "Fake DUser",
                login = "@fakelogin",
                publicRepos = 10,
                publicGists = 10,
                followers = 10,
                following = 10
        )
    }

    @Before
    fun setUp() {

        whenever(githubRepository.userDetails(FAKE_NICKNAME))
                .thenReturn(Observable.just(FAKE_USER))
    }

    @Test
    fun onlyExecuteUseCaseAndNeededMethods() {

        // Given Test params, when execute:
        useCase.buildObservable(FAKE_NICKNAME)

        // Then, build observable must be the only interaction.
        verify(githubRepository).userDetails(kEq(FAKE_NICKNAME))
        verifyNoMoreInteractions(githubRepository)
    }
}