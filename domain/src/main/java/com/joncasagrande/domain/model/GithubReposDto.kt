package com.joncasagrande.domain.model

data class GithubReposDto(
    val image: String?,
    val name: String,
    val forks: Int,
    val watchers: Int,
    val lang: String
)