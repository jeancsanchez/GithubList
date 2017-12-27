package com.github.jeancarlos.githublist.data.models

import com.github.jeancarlos.githublist.domain.model.User
import com.google.gson.annotations.SerializedName

/**
 * This class represents a Data User model.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
class DUser : User() {

    @SerializedName("avatar_url")
    override var avatarUrl: String? = ""

    @SerializedName("repos_url")
    override var reposUrl: String? = ""

    @SerializedName("public_repos")
    override var publicRepos: Int = 0

    @SerializedName("public_gists")
    override var publicGists: Int = 0
}