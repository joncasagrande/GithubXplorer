package com.joncasagrande.data.di

import com.google.gson.FieldNamingPolicy
import com.joncasagrande.data.api.GithubApi
import com.joncasagrande.data.api.GithubApiImpl
import com.joncasagrande.data.repository.GithubRepository
import com.joncasagrande.data.repository.GithubRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                    disableHtmlEscaping()
                    setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

                }
            }
            install(Logging) {
                level = LogLevel.ALL
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 10000
            }
        }
    }

    @Provides
    @Singleton
    fun provideApiService(client: HttpClient): GithubApi {
        return GithubApiImpl(client)
    }

    @Provides
    @Singleton
    fun provideRepository(dogApi: GithubApi): GithubRepository {
        return GithubRepositoryImpl(dogApi)
    }
}