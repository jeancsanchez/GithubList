package com.github.jeancarlos.githublist

import com.github.jeancarlos.githublist.data.models.DGithubRepo
import com.github.jeancarlos.githublist.data.models.DUser
import com.github.jeancarlos.githublist.domain.interactors.GetUserDetailsUc
import com.github.jeancarlos.githublist.domain.interactors.GetUserReposUc
import com.github.jeancarlos.githublist.domain.model.GithubRepo
import com.github.jeancarlos.githublist.domain.model.User
import com.github.jeancarlos.githublist.features.detail.DetailContract
import com.github.jeancarlos.githublist.features.splash.DetailPresenter
import com.github.jeancarlos.githublist.utils.BasePresenterTest
import com.github.jeancarlos.githublist.utils.kAny
import com.github.jeancarlos.githublist.utils.kEq
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
 * This class stores some test cases for [DetailPresenter].
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 29/12/17.
 * Jesus loves you.
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(
        DetailPresenter::class,
        fullyQualifiedNames = ["com.baeldung.powermockito.introduction.*"]
)
class DetailPresenterTest : BasePresenterTest() {

    @Mock lateinit var view: DetailContract.View

    @Mock lateinit var getUserReposUc: GetUserReposUc

    @Mock lateinit var getUserDetailsUc: GetUserDetailsUc

    private lateinit var detailPresenter: DetailPresenter


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
        detailPresenter = PowerMockito.spy(
                DetailPresenter(
                        getUserReposUc = getUserReposUc,
                        getUserDetailsUc = getUserDetailsUc
                ).also { it.view = view }
        )
    }

    /**
     * Test case: When getting user details.
     */
    @Test
    fun onLoadUsersTest() {

        // Given
        val fakeNickname = "jeancsanchez"


        // When
        detailPresenter.onLoadUserDetails(fakeNickname)


        // Then
        verify(view).showLoading()
        verify(getUserDetailsUc)
                .execute(
                        kAny(),
                        kAny(),
                        onNextCaptor.capture(),
                        kAny(),
                        kAny()
                )

        onNextCaptor.firstValue.invoke(FAKE_USER)
        verify(view).hideLoading()
        verify(view).showUserDetails(kEq(FAKE_USER))

        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(getUserDetailsUc)
    }

    /**
     * Test case: When getting user repositories.
     */
    @Test
    fun onLoadUserRepositories() {

        // Given
        val fakeQuery = "jeancsanchez"

        // When
        detailPresenter.onLoadUserRepositories(fakeQuery)


        // Then
        verify(view).showRepositoriesLoading()
        verify(getUserReposUc)
                .execute(
                        kAny(),
                        kAny(),
                        onNextCaptor.capture(),
                        kAny(),
                        kAny()
                )

        onNextCaptor.firstValue.invoke(FAKE_REPO_LIST)
        verify(view).hideRepositoriesLoading()
        verify(view).showUserRepositories(kEq(FAKE_REPO_LIST))

        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(getUserReposUc)
    }

    /**
     * Test case: When on destroy state.
     * Dispose all use cases.
     */
    @Test
    fun onDestroyTest() {

        // When
        detailPresenter.onDestroy()


        // Then
        verify(getUserReposUc).dispose()
        verify(getUserDetailsUc).dispose()

        verifyZeroInteractions(view)
        verifyNoMoreInteractions(getUserReposUc)
    }
}