package com.joncasagrande.data.repository

import com.joncasagrande.data.api.GithubApi
import com.joncasagrande.data.model.GithubRepos
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
        val gitRepo = GithubRepos(
            totalCount = 0,
            false,
            emptyList<Repos>()
        )

                coEvery { githubApi.fetchRepos() } returns NetworkResult.Success(gitRepo)

            //when
            val result = githubRepository.getRepos() as Resource.Success<GithubRepos>

        //then
       assertEquals(
             result.value.items.isEmpty(),
             true
        )
    }

    @Test
    fun getDogListResourceSuccessWithItem() = runTest {
        //given
        val gitRepo =GithubRepos(
            totalCount = 0,
            false,
            listOf(Repos())
        )

        coEvery { githubApi.fetchRepos() } returns NetworkResult.Success(gitRepo)

        //when
        val result = githubRepository.getRepos() as Resource.Success<GithubRepos>

        //then
        assertEquals(
            result.value.items.isNotEmpty(),
            true
        )
    }

    @Test
    fun getDogListResourceError() = runTest {
        //given
        coEvery { githubApi.fetchRepos() } returns NetworkResult.Error(Exception("error"))

        //when
        val result = githubRepository.getRepos() as Resource.Error<*>

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