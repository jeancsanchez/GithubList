package com.github.jeancarlos.githublist.data.models

import com.google.gson.annotations.SerializedName

/**
 * This class represents a Data User model.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */
class DUser(
        val id: Int = -1,
        val name: String = "",
        val login: String? = "",
        @SerializedName("avatar_url") val avatarUrl: String? = "",
        @SerializedName("repos_url") val reposUrl: String? = "",
        val company: String? = "",
        val location: String? = "",
        val email: String? = "",
        val bio: String? = "",
        @SerializedName("public_repos") val publicRepos: Int = 0,
        @SerializedName("public_gists") val publicGists: Int = 0,
        val followers: Int = 0,
        val following: Int = 0
)