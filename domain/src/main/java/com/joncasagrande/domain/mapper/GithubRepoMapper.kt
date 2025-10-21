package com.joncasagrande.domain.mapper

import com.joncasagrande.domain.model.GithubReposDto
import com.joncasagrande.data.model.GithubRepos

class GithubRepoMapper {
    fun mapper(githubRepos: GithubRepos?): List<GithubReposDto> {
        val githubReposDtos: MutableList<GithubReposDto> = mutableListOf()
        githubRepos?.items?.forEach { repos ->
            val githubReposDto = GithubReposDto(
                image = repos.owner?.avatarUrl,
                name = repos.name.orEmpty(),
                forks = repos.forksCount ?: 0,
                watchers = repos.watchers ?: 0,
                lang = repos.language.orEmpty(),
                description = repos.description.orEmpty()
            )
            githubReposDtos.add(githubReposDto)
        }
        return githubReposDtos
    }
}