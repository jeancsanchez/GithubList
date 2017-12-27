package com.github.jeancarlos.githublist.domain.model

/**
 * This class represents an Github Repository model.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
class GithubRepo(
        val id: Int = -1,
        val name: String = "",
//        val full_name: "torvalds/libdc-for-dirk",
        val fullName: String = "",
        val private: Boolean = false,
        val description: String? = "",
        val fork: Boolean = false,
        val url: String? = "",
//        val stargazers_count: Int = 0,
        val stargazersCount: Int = 0,
//        val watchers_count: Int = 0,
        val watchersCount: Int = 0,
        val language: String = "",
//        val forks_count: Int = 0,
        val forksCount: Int = 0,
//        val open_issues_count: Int = 0
        val openIssuesCount: Int = 0
)