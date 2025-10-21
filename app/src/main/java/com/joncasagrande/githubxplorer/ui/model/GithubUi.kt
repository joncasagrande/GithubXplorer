package com.joncasagrande.githubxplorer.ui.model

data class GithubUi(
    val name: String,
    val avatar: String,
    val description: String,
    val stars: Int,
    val forks: Int,
    val lastUpdated: String,
    val language: String? = null,
    val license: String? = null,
    val ownerName: String
)
