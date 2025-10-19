package com.joncasagrande.data.repository

import com.joncasagrande.data.model.GitRepos
import com.joncasagrande.data.utils.Resource

interface GithubRepository {
    suspend fun getUserRepos(user: String): Resource<GitRepos>
}