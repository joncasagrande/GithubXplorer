package com.joncasagrande.domain.mapper

import com.joncasagrande.data.model.Repos
import com.joncasagrande.domain.model.GithubReposDto

class GithubRepoMapper {
    fun mapper(githubRepos: List<Repos>?): List<GithubReposDto> {
        val githubReposDtos: MutableList<GithubReposDto> = mutableListOf()
        githubRepos?.forEach { repos ->
            githubReposDtos.add(mapper(repos))
        }
        return githubReposDtos
    }

    fun mapper(repo: Repos): GithubReposDto {
        return GithubReposDto(
            image = repo.owner?.avatarUrl,
            name = repo.name.orEmpty(),
            forks = repo.forksCount ?: 0,
            watchers = repo.watchers ?: 0,
            lang = listOf("Python", "Kotlin"),//fixme
            description = repo.description.orEmpty(),
            ownerName = repo.owner?.login.orEmpty(),
            stars = repo.stargazersCount ?: 0,
            license = repo.license?.name.orEmpty(),
            lastUpdated = repo.updatedAt.orEmpty()
        )
    }
}