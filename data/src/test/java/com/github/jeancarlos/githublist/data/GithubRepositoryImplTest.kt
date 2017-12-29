package com.github.jeancarlos.githublist.data

import com.github.jeancarlos.githublist.data.local.LocalProvider
import com.github.jeancarlos.githublist.data.models.DGithubRepo
import com.github.jeancarlos.githublist.data.models.DUser
import com.github.jeancarlos.githublist.data.models.mappers.DGithubRepoMapper
import com.github.jeancarlos.githublist.data.models.mappers.DUserMapper
import com.github.jeancarlos.githublist.data.remote.RemoteProvider
import com.github.jeancarlos.githublist.data.utils.kEq
import com.github.jeancarlos.githublist.domain.model.GithubRepo
import com.github.jeancarlos.githublist.domain.model.User
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations
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
        val FAKE_DUSER_LIST = listOf(FAKE_DUSER)

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

        @JvmField
        val FAKE_DREPO = DGithubRepo(
                id = 1,
                name = "Fake repository",
                private = false
        )

        @JvmField
        val FAKE_DREPO_LIST = listOf(FAKE_DREPO)

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
        MockitoAnnotations.initMocks(this)

        githubRepository = PowerMockito.spy(
                GithubRepositoryImpl(remoteProvider, localProvider, dUserMapper, dGithubRepoMapper)
        )


        whenever(dUserMapper.transform(FAKE_DUSER))
                .thenReturn(FAKE_USER)

        whenever(remoteProvider.listUsers(anyInt()))
                .thenReturn(Observable.just(FAKE_DUSER_LIST))

        whenever(localProvider.saveNextUsersPage(anyInt()))
                .thenReturn(Observable.just(Unit))

        whenever(localProvider.getNextUsersPage())
                .thenReturn(Observable.just(1))

        whenever(localProvider.saveNextReposPage(anyInt()))
                .thenReturn(Observable.just(Unit))

        whenever(localProvider.getNextReposPage())
                .thenReturn(Observable.just(1))
    }

    /**
     * Test case: When loading users, then all Github users  should be retrieved.
     */
    @Test
    fun whenLoadUsersThenListAllUsers() {

        // When
        githubRepository.loadUsers()
                .test()


        // Then
        verify(remoteProvider).listUsers(anyInt())
        verify(dUserMapper).transform(kEq(FAKE_DUSER_LIST))

        verifyNoMoreInteractions(dUserMapper)
        verifyNoMoreInteractions(remoteProvider)
    }

    /**
     * Test case: When searching an users, then the users result should be retrieved.
     */
    @Test
    fun whenSearchForUserThenListUsers() {

        // Given
        val fakeQuery = "jeancsanchez"
        val fakePage = 1

        whenever(remoteProvider.searchForUser(fakeQuery, fakePage))
                .thenReturn(Observable.just(FAKE_DUSER_LIST))

        // When
        githubRepository.searchForUser(fakeQuery)
                .test()

        // Then
        verify(remoteProvider).searchForUser(kEq(fakeQuery), kEq(fakePage))
        verify(dUserMapper).transform(kEq(FAKE_DUSER_LIST))

        verifyNoMoreInteractions(dUserMapper)
        verifyNoMoreInteractions(remoteProvider)
    }

    /**
     * Test case: When getting user details, then the users details should be retrieved.
     */
    @Test
    fun whenUserDetailsThenUsersDetails() {

        // Given
        val fakeNickname = "jeancsanchez"
        val fakePage = 1

        whenever(remoteProvider.userDetails(fakeNickname))
                .thenReturn(Observable.just(FAKE_DUSER))

        // When
        githubRepository.userDetails(fakeNickname)
                .test()

        // Then
        verify(remoteProvider).userDetails(kEq(fakeNickname))
        verify(dUserMapper).transform(kEq(FAKE_DUSER))

        verifyNoMoreInteractions(dUserMapper)
        verifyNoMoreInteractions(remoteProvider)
    }

    /**
     * Test case: When getting user repositories, then the users repositories should be retrieved.
     */
    @Test
    fun whenUserRepositoriesThenListAllRepositories() {

        // Given
        val fakeNickname = "jeancsanchez"
        val fakePage = 1

        whenever(remoteProvider.userRepositories(fakeNickname, fakePage))
                .thenReturn(Observable.just(FAKE_DREPO_LIST))


        // When
        githubRepository.userRepositories(fakeNickname)
                .test()

        // Then
        verify(remoteProvider).userRepositories(kEq(fakeNickname), kEq(fakePage))
        verify(dGithubRepoMapper).transform(kEq(FAKE_DREPO_LIST))

        verifyNoMoreInteractions(dGithubRepoMapper)
        verifyNoMoreInteractions(remoteProvider)
    }

    /**
     * Test case: When clear data, then local data should be cleared.
     */
    @Test
    fun whenClearThenClearAllData() {

        // Given
        whenever(localProvider.clear())
                .thenReturn(Observable.just(Unit))

        // When
        githubRepository.clearData()
                .test()

        // Then
        verify(localProvider).clear()

        verifyNoMoreInteractions(localProvider)
    }
}