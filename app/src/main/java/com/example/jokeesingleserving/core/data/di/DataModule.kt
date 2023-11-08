package com.example.jokeesingleserving.core.data.di

import com.example.jokeesingleserving.core.data.repository.JokeRepository
import com.example.jokeesingleserving.core.data.repository.implementation.DefaultJokeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindsJokeRepository(
        jokeRepository: DefaultJokeRepository
    ): JokeRepository
}