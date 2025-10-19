package com.joncasagrande.data.api

import com.joncasagrande.data.api.HttpRoutes.GITHUB_API
import com.joncasagrande.data.model.Items
import com.joncasagrande.data.utils.NetworkResult
import com.joncasagrande.data.utils.toResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import javax.inject.Inject

class GithubApiImpl@Inject constructor(
    private val client: HttpClient
) : GithubApi {

    override suspend fun fetchRepos(user: String): NetworkResult<List<Items>> {
        return client.get {
            url("$GITHUB_API/users/$user/repos")
        }.toResult()
    }
}