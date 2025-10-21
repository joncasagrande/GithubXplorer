package com.joncasagrande.data.repository

import com.joncasagrande.data.model.Repos
import com.joncasagrande.data.utils.Resource

interface GithubRepository {
    suspend fun getRepos(): Resource<List<Repos>>
    suspend fun fetchLanguage(project:String,repo:String): Resource<Map<String, Int>>
}