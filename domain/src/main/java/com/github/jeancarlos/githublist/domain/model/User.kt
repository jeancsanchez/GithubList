package com.github.jeancarlos.githublist.domain.model

/**
 * This class represents an User model.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 26/12/17.
 * Jesus loves you.
 */

class User(
        val id: Int = -1,
        val name: String = "",
        val login: String? = "",
//        avatar_url: "https://avatars0.githubusercontent.com/u/1024025?v=4"
        avatarUrl: String? = "",
//repos_url: "https://api.github.com/users/torvalds/repos",
        reposUrl: String = "",
        company: String? = "",
        location: String? = "",
        email: String? = "",
        bio: String? = "",
        public_repos: Int = 0,
        public_gists: Int = 0,
        followers: Int = 0,
        following: Int = 0
)
