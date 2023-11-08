package com.example.jokeesingleserving.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jokeesingleserving.core.database.dao.JokeDao
import com.example.jokeesingleserving.core.model.Joke

@Database(entities = [Joke::class], version = 1, exportSchema = false)
abstract class JokeDatabase : RoomDatabase() {
    abstract fun jokeDao(): JokeDao
}