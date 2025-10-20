package com.joncasagrande.domain.di

import com.joncasagrande.domain.mapper.GithubRepoMapper
import com.joncasagrande.domain.usecase.GithubRepoUseCase
import com.joncasagrande.data.repository.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideMapper(): GithubRepoMapper {
        return GithubRepoMapper()
    }

    @Provides
    @Singleton
    fun provideGetBreedUseCase(
        repository: GithubRepository,
        mapper: GithubRepoMapper
    ): GithubRepoUseCase {
        return GithubRepoUseCase(repository, mapper)
    }

}