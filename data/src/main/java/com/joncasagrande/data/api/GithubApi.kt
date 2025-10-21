package com.joncasagrande.data.api

import com.joncasagrande.data.model.Repos
import com.joncasagrande.data.utils.NetworkResult

interface GithubApi {
    suspend fun fetchRepos(): NetworkResult<List<Repos>>
    suspend fun fetchLanguage(project:String,repo:String): NetworkResult<Map<String,Int>>
}