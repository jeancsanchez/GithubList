package com.github.jeancarlos.githublist.data

import com.github.jeancarlos.githublist.data.local.LocalProvider
import com.github.jeancarlos.githublist.data.models.DUser
import com.github.jeancarlos.githublist.data.models.mappers.DGithubRepoMapper
import com.github.jeancarlos.githublist.data.models.mappers.DUserMapper
import com.github.jeancarlos.githublist.data.remote.RemoteProvider
import com.github.jeancarlos.githublist.data.utils.kEq
import com.github.jeancarlos.githublist.domain.model.User
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

/**
 * This class stores some test cases for [GithubRepositoryImpl].
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 28/12/17.
 * Jesus loves you.
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(
        GithubRepositoryImpl::class,
        fullyQualifiedNames = ["com.baeldung.powermockito.introduction.*"]
)
class GithubRepositoryImplTest {

    @Mock private lateinit var dGithubRepoMapper: DGithubRepoMapper
    @Mock private lateinit var dUserMapper: DUserMapper
    @Mock private lateinit var localProvider: LocalProvider
    @Mock private lateinit var remoteProvider: RemoteProvider
    private lateinit var githubRepository: GithubRepositoryImpl

    companion object {

        @JvmField
        val FAKE_DUSER = DUser(
                id = 1,
                name = "Fake DUser",
                login = "@fakelogin",
                publicRepos = 10,
                publicGists = 10,
                followers = 10,
                following = 10
        )

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
        githubRepository = PowerMockito.spy(
                GithubRepositoryImpl(remoteProvider, localProvider, dUserMapper, dGithubRepoMapper)
        )
    }

    /**
     * Test case: When loading users, then all Github users  should be retrieved.
     */
    @Test
    fun whenLoadUsersThenListAllUsers() {

        // Given
        Mockito.`when`(dUserMapper.transform(FAKE_DUSER))
                .thenReturn(FAKE_USER)

        Mockito.`when`(remoteProvider.listUsers(anyInt()))
                .thenReturn(Observable.just(listOf(FAKE_DUSER)))


        // When
        githubRepository.loadUsers()


        // Then
        verify(remoteProvider).listUsers(anyInt())
        verify(dUserMapper).transform(kEq(FAKE_DUSER))

        verifyNoMoreInteractions(dUserMapper)
        verifyNoMoreInteractions(remoteProvider)
    }
}