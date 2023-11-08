package com.example.jokeesingleserving.core.database.di

import android.content.Context
import androidx.room.Room
import com.example.jokeesingleserving.core.database.JokeDatabase
import com.example.jokeesingleserving.core.database.dao.JokeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun providesJokeDao(
        database: JokeDatabase
    ): JokeDao = database.jokeDao()

    @Provides
    @Singleton
    fun providesJokeDatabase(
        @ApplicationContext context: Context
    ): JokeDatabase = Room.databaseBuilder(
        context = context,
        klass = JokeDatabase::class.java,
        name = "joke-database"
    ).build()
}