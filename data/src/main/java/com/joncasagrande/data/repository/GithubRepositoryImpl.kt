package com.joncasagrande.data.repository

import com.joncasagrande.data.api.GithubApi
import com.joncasagrande.data.model.Repos
import com.joncasagrande.data.utils.NetworkResult
import com.joncasagrande.data.utils.Resource
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : GithubRepository {

    override suspend fun getRepos(): Resource<List<Repos>> {
        return when (val data = api.fetchRepos()) {
            is NetworkResult.Success -> Resource.Success(data.body)
            is NetworkResult.Error -> Resource.Error(
                "api error",
                data.error
            )
        } as Resource<List<Repos>>
    }
}