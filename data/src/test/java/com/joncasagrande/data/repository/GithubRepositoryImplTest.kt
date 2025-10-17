package com.joncasagrande.data.repository

import com.joncasagrande.data.api.GithubApi
import com.joncasagrande.data.model.GitRepos
import com.joncasagrande.data.utils.NetworkResult
import com.joncasagrande.data.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GithubRepositoryImplTest {
    lateinit var dogRepository: GithubRepository

    @MockK
    lateinit var dogApi: GithubApi

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dogRepository = GithubRepositoryImpl(dogApi)
    }

    @Test
    fun getDogListResourceSuccess() = runTest {
        //given
        val gitRepo = GitRepos()

        coEvery { dogApi.fetchRepos("test") } returns NetworkResult.Success(gitRepo)

        //when
        val result = dogRepository.getUserRepos("test") as Resource.Success<GitRepos>

        //then
       /* assertEquals(
            // result.value.items.type.isNotBlank(),
            // true
        )
        assertEquals(
        //     result.value.message?.first(),
          //   "https://images.dog.ceo/breeds/terrier-wheaten/n02098105_1228.jpg"
        )

        assertEquals(
            // result.value.status,
            // "success"
        )*/
    }

    @Test
    fun getDogListResourceError() = runTest {
        //given
        coEvery { dogApi.fetchRepos("") } returns NetworkResult.Error(Exception("error"))

        //when
        val result = dogRepository.getUserRepos("") as Resource.Error<GitRepos>


        //then
        assertEquals(
            result.error.isNotEmpty(),
            true
        )
        assertEquals(
            result.error,
            "api error"
        )
    }

}