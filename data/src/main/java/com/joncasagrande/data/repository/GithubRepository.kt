package com.joncasagrande.data.repository

import com.joncasagrande.data.model.GithubRepos
import com.joncasagrande.data.utils.Resource

interface GithubRepository {
    suspend fun getRepos(): Resource<GithubRepos>
}