package com.joncasagrande.data.repository

import com.joncasagrande.data.api.GithubApi
import com.joncasagrande.data.model.Repos
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
    lateinit var githubRepository: GithubRepository

    @MockK
    lateinit var githubApi: GithubApi

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        githubRepository = GithubRepositoryImpl(githubApi)
    }

    @Test
    fun getDogListResourceSuccess() = runTest {
        //given
        val gitRepo = emptyList<Repos>()

        coEvery { githubApi.fetchRepos("test") } returns NetworkResult.Success(gitRepo)

        //when
        val result = githubRepository.getUserRepos("test") as Resource.Success<List<Repos>>

        //then
       assertEquals(
             result.value.isEmpty(),
             true
        )
    }

    @Test
    fun getDogListResourceSuccessWithItem() = runTest {
        //given
        val gitRepo =listOf(Repos())

        coEvery { githubApi.fetchRepos("test") } returns NetworkResult.Success(gitRepo)

        //when
        val result = githubRepository.getUserRepos("test") as Resource.Success<List<Repos>>

        //then
        assertEquals(
            result.value.isNotEmpty(),
            true
        )
    }

    @Test
    fun getDogListResourceError() = runTest {
        //given
        coEvery { githubApi.fetchRepos("") } returns NetworkResult.Error(Exception("error"))

        //when
        val result = githubRepository.getUserRepos("") as Resource.Error<List<Repos>>


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