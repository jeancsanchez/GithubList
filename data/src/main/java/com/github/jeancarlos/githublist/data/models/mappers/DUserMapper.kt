package com.github.jeancarlos.githublist.data.models.mappers

import com.github.jeancarlos.githublist.data.models.DUser
import com.github.jeancarlos.githublist.domain.base.Mapper
import com.github.jeancarlos.githublist.domain.model.User

/**
 * This class is an implementation of [Mapper] for the User on Data layer.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 27/12/17.
 * Jesus loves you.
 */
class DUserMapper : Mapper<DUser, User>() {

    override fun transform(value: DUser): User =
            User(
                    id = value.id,
                    name = value.name,
                    login = value.login,
                    avatarUrl = value.avatarUrl,
                    reposUrl = value.reposUrl,
                    company = value.company,
                    location = value.location,
                    email = value.email,
                    bio = value.bio,
                    publicRepos = value.publicRepos,
                    publicGists = value.publicGists,
                    followers = value.followers,
                    following = value.following
            )
}