package com.joncasagrande.domain.mapper

import com.joncasagrande.data.model.Repos
import com.joncasagrande.domain.model.GithubReposDto

class GithubRepoMapper {
    fun mapper(githubRepos: List<Repos>?): List<GithubReposDto> {
        val githubReposDtos: MutableList<GithubReposDto> = mutableListOf()
        githubRepos?.forEach { repos ->
            val githubReposDto = GithubReposDto(
                image = repos.owner?.avatarUrl,
                name = repos.name.orEmpty(),
                forks = repos.forksCount ?: 0,
                watchers = repos.watchers ?: 0,
                lang = repos.language?: "N/A",
                description = repos.description.orEmpty(),
                ownerName = repos.owner?.login.orEmpty(),
                stars = repos.stargazersCount ?: 0,
                license = repos.license?.name.orEmpty(),
                lastUpdated = repos.updatedAt.orEmpty()
            )
            githubReposDtos.add(githubReposDto)
        }
        return githubReposDtos
    }
}