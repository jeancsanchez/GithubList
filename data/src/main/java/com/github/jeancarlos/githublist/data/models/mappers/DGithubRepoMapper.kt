package com.github.jeancarlos.githublist.data.models.mappers

import com.github.jeancarlos.githublist.data.models.DGithubRepo
import com.github.jeancarlos.githublist.domain.base.Mapper
import com.github.jeancarlos.githublist.domain.model.GithubRepo

/**
 * This class is an implementation of [Mapper] for the Github repo on Data layer.
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 27/12/17.
 * Jesus loves you.
 */
class DGithubRepoMapper : Mapper<DGithubRepo, GithubRepo>() {

    override fun transform(value: DGithubRepo): GithubRepo =
            GithubRepo(
                    id = value.id,
                    name = value.name,
                    fullName = value.fullName,
                    private = value.private,
                    description = value.description,
                    fork = value.fork,
                    url = value.url,
                    stargazersCount = value.stargazersCount,
                    watchersCount = value.watchersCount,
                    language = value.language,
                    forksCount = value.forksCount,
                    openIssuesCount = value.openIssuesCount
            )
}