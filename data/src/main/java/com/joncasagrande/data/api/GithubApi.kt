package com.joncasagrande.data.api

import com.joncasagrande.data.model.GitRepos
import com.joncasagrande.data.utils.NetworkResult

interface GithubApi {
    suspend fun fetchRepos(user: String): NetworkResult<GitRepos>
}