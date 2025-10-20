package com.joncasagrande.data.model

data class GithubRepos(
    val totalCount: Int,
    val incompleteResults : Boolean,
    val items: List<Repos>
)
