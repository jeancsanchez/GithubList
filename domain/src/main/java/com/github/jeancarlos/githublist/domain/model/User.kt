package com.github.jeancarlos.githublist.domain.model

/**
 * This class represents an User model.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */

open class User(
        val id: Int = -1,
        val name: String = "",
        val login: String? = "",
        open var avatarUrl: String? = "",
        open var reposUrl: String? = "",
        val company: String? = "",
        val location: String? = "",
        val email: String? = "",
        val bio: String? = "",
        open var publicRepos: Int = 0,
        open var publicGists: Int = 0,
        val followers: Int = 0,
        val following: Int = 0
)
