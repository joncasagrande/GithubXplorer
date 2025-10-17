package com.joncasagrande.data.api

import com.joncasagrande.data.utils.NetworkResult

interface GithubApi {
    suspend fun fetchDogs(amount: Int): NetworkResult<DogList>
    suspend fun fetchBreed(breed: String, amount: Int): NetworkResult<DogList>
    suspend fun fetchSubBreed(breed: String, subBreed: String, amount: Int): NetworkResult<DogList>
}