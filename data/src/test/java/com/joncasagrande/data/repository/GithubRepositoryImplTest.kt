package com.joncasagrande.data.repository

import com.joncasagrande.data.api.GithubApi
import com.joncasagrande.data.model.GitRepos
import com.joncasagrande.data.model.Items
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
        val gitRepo = emptyList<Items>()

        coEvery { githubApi.fetchRepos("test") } returns NetworkResult.Success(gitRepo)

        //when
        val result = githubRepository.getUserRepos("test") as Resource.Success<GitRepos>

        //then
       assertEquals(
             result.value.items?.isEmpty(),
             true
        )
    }

    @Test
    fun getDogListResourceSuccessWithItem() = runTest {
        //given
        val gitRepo = listOf(Items())

        coEvery { githubApi.fetchRepos("test") } returns NetworkResult.Success(gitRepo)

        //when
        val result = githubRepository.getUserRepos("test") as Resource.Success<GitRepos>

        //then
        assertEquals(
            result.value.items?.isNotEmpty(),
            true
        )
    }

    @Test
    fun getDogListResourceError() = runTest {
        //given
        coEvery { githubApi.fetchRepos("") } returns NetworkResult.Error(Exception("error"))

        //when
        val result = githubRepository.getUserRepos("") as Resource.Error<GitRepos>


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