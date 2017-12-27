package com.github.jeancarlos.githublist.data.models

import com.google.gson.annotations.SerializedName

/**
 * * This class represents a Data Github Repository model.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
class DGithubRepo(
        val id: Int = -1,
        val name: String = "",
        @SerializedName("full_name") val fullName: String? = "",
        val private: Boolean = false,
        val description: String? = "",
        val fork: Boolean = false,
        val url: String? = "",
        @SerializedName("stargazers_count") val stargazersCount: Int = 0,
        @SerializedName("watchers_count") val watchersCount: Int = 0,
        val language: String = "",
        @SerializedName("forks_count") val forksCount: Int = 0,
        @SerializedName("open_issues_count") val openIssuesCount: Int = 0
)