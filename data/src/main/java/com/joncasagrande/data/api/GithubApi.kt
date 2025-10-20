package com.joncasagrande.data.api

import com.joncasagrande.data.model.GithubRepos
import com.joncasagrande.data.utils.NetworkResult

interface GithubApi {
    suspend fun fetchRepos(): NetworkResult<GithubRepos>
}