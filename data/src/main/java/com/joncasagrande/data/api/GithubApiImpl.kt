package com.joncasagrande.data.api

import com.joncasagrande.data.api.HttpRoutes.GITHUB_API
import com.joncasagrande.data.model.Repos
import com.joncasagrande.data.utils.NetworkResult
import com.joncasagrande.data.utils.toResult
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import javax.inject.Inject

class GithubApiImpl@Inject constructor(
    private val client: HttpClient
) : GithubApi {

    override suspend fun fetchRepos(): NetworkResult<List<Repos>> {
        return client.get {
            url("$GITHUB_API/repositories")
        }.toResult()
    }

    override suspend fun fetchLanguage(project:String,repo:String): NetworkResult<Map<String, Int>> {
        return client.get {
            url("$GITHUB_API/repos/$project/$repo/languages")
        }.toResult()
    }
}