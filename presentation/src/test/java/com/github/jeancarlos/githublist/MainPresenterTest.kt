package com.github.jeancarlos.githublist

import com.github.jeancarlos.githublist.data.models.DGithubRepo
import com.github.jeancarlos.githublist.data.models.DUser
import com.github.jeancarlos.githublist.domain.interactors.ListUsersUc
import com.github.jeancarlos.githublist.domain.interactors.SearchUsersUc
import com.github.jeancarlos.githublist.domain.model.GithubRepo
import com.github.jeancarlos.githublist.domain.model.User
import com.github.jeancarlos.githublist.features.main.MainContract
import com.github.jeancarlos.githublist.features.main.MainPresenter
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
 * This class stores some test cases for [MainPresenter].
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 29/12/17.
 * Jesus loves you.
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(
        MainPresenter::class,
        fullyQualifiedNames = ["com.baeldung.powermockito.introduction.*"]
)
class MainPresenterTest : BasePresenterTest() {

    @Mock lateinit var view: MainContract.View

    @Mock lateinit var listUsersUc: ListUsersUc

    @Mock lateinit var searchUsersUc: SearchUsersUc

    private lateinit var mainPresenter: MainPresenter


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
        mainPresenter = PowerMockito.spy(
                MainPresenter(
                        listUsersUc = listUsersUc,
                        searchUsersUc = searchUsersUc
                ).also { it.view = view }
        )
    }

    /**
     * Test case: When loads the users.
     * Gets all users.
     */
    @Test
    fun onLoadUsersTest() {

        // When
        mainPresenter.onLoadUsers()


        // Then
        verify(view).showLoading()
        verify(listUsersUc)
                .execute(
                        kAny(),
                        kAny(),
                        onNextCaptor.capture(),
                        kAny(),
                        kAny()
                )

        onNextCaptor.firstValue.invoke(FAKE_USER_LIST)
        verify(view).hideLoading()
        verify(view).showUsers(FAKE_USER_LIST)

        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(listUsersUc)
    }

    /**
     * Test case: When searching an user by a query.
     * Gets a list of users by query.
     */
    @Test
    fun onSearchTest() {

        // Given
        val fakeQuery = "jeancsanchez"

        // When
        mainPresenter.onSearch(fakeQuery)


        // Then
        verify(view).showLoading()
        verify(searchUsersUc)
                .execute(
                        kAny(),
                        kAny(),
                        onNextCaptor.capture(),
                        kAny(),
                        kAny()
                )

        onNextCaptor.firstValue.invoke(FAKE_USER_LIST)
        verify(view).hideLoading()
        verify(view).showSearchResult(FAKE_USER_LIST)

        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(listUsersUc)
    }

    /**
     * Test case: When on destroy state.
     * Dispose all use cases.
     */
    @Test
    fun onDestroyTest() {

        // When
        mainPresenter.onDestroy()


        // Then
        verify(listUsersUc).dispose()
        verify(searchUsersUc).dispose()

        verifyZeroInteractions(view)
        verifyNoMoreInteractions(listUsersUc)
    }
}