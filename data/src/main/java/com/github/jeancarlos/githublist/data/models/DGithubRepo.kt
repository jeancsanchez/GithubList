package com.github.jeancarlos.githublist.data.models

import com.github.jeancarlos.githublist.domain.model.GithubRepo
import com.google.gson.annotations.SerializedName

/**
 * * This class represents a Data Github Repository model.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
class DGithubRepo : GithubRepo() {

    @SerializedName("full_name")
    override var fullName: String? = ""

    @SerializedName("stargazers_count")
    override var stargazersCount: Int = 0

    @SerializedName("watchers_count")
    override var watchersCount: Int = 0

    @SerializedName("forks_count")
    override var forksCount: Int = 0

    @SerializedName("open_issues_count")
    override var openIssuesCount: Int = 0
}