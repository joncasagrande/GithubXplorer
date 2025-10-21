package com.joncasagrande.domain.model

data class GithubReposDto(
    val image: String?,
    val name: String,
    val forks: Int,
    val watchers: Int,
    val lang: List<String>,
    val description: String,
    val ownerName: String,
    val stars: Int,
    val license: String,
    val lastUpdated: String
)