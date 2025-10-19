package com.joncasagrande.data.repository

import com.joncasagrande.data.model.Repos
import com.joncasagrande.data.utils.Resource

interface GithubRepository {
    suspend fun getUserRepos(user: String): Resource<List<Repos>>
}