package com.github.jeancarlos.githublist.domain

import com.github.jeancarlos.githublist.domain.interactors.ListUsersUc
import com.github.jeancarlos.githublist.domain.model.User
import com.github.jeancarlos.githublist.domain.respositories.GithubRepository
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
 * This class stores some test cases for [ListUsersUc].
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 29/12/17.
 * Jesus loves you.
 */
@RunWith(MockitoJUnitRunner::class)
class ListUserDetailsUcTest {

    @Mock private lateinit var githubRepository: GithubRepository
    @InjectMocks private lateinit var useCase: ListUsersUc

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

        @JvmField
        val FAKE_USER_LIST = listOf(FAKE_USER)
    }

    @Before
    fun setUp() {

        whenever(githubRepository.loadUsers())
                .thenReturn(Observable.just(FAKE_USER_LIST))
    }

    @Test
    fun onlyExecuteUseCaseAndNeededMethods() {

        // Given Test params, when execute:
        useCase.buildObservable(Unit)

        // Then, build observable must be the only interaction.
        verify(githubRepository).loadUsers()
        verifyNoMoreInteractions(githubRepository)
    }
}