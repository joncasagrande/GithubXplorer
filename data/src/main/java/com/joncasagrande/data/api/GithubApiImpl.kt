package com.joncasagrande.data.api

import com.joncasagrande.data.api.HttpRoutes.GITHUB_API
import com.joncasagrande.data.model.GithubRepos
import com.joncasagrande.data.utils.NetworkResult
import com.joncasagrande.data.utils.toResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import javax.inject.Inject

class GithubApiImpl@Inject constructor(
    private val client: HttpClient
) : GithubApi {

    override suspend fun fetchRepos(): NetworkResult<GithubRepos> {
        return client.get {
            url("$GITHUB_API/search/repositories?q=Q")
        }.toResult()
    }
}