package com.joncasagrande.domain.usecase

import com.joncasagrande.data.repository.GithubRepository
import com.joncasagrande.data.utils.Resource
import com.joncasagrande.domain.mapper.GithubRepoMapper
import com.joncasagrande.domain.model.GithubReposDto
import javax.inject.Inject

class GithubRepoUseCase @Inject constructor(
    val repository: GithubRepository,
    val mapper: GithubRepoMapper
) {
    sealed class Event {
        data class Success(val listDogs: List<GithubReposDto>) : Event()
        data class Error(val error: String) : Event()
    }

    suspend fun getRepos(): Event {
        return when (val data = repository.getRepos()) {
            is Resource.Success -> Event.Success(mapper.mapper(data.value))
            is Resource.Error -> Event.Error(data.error)
        }
    }
}