package com.joncasagrande.data.api

import com.joncasagrande.data.model.Items
import com.joncasagrande.data.utils.NetworkResult

interface GithubApi {
    suspend fun fetchRepos(user: String): NetworkResult<List<Items>>
}