package com.joncasagrande.githubxplorer.ui.model

data class EventState(
    val idle: Boolean = false,
    val showLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val githubUis: List<GithubUi>? = null,
    val showError: String? = null
)
