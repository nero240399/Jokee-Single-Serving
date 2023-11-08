package com.example.jokeesingleserving.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.jokeesingleserving.core.model.Joke
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDao {

    @Query("SELECT * FROM Joke WHERE isLiked IS NULL ORDER BY id LIMIT 1")
    fun observeCurrent(): Flow<Joke?>

    @Upsert
    suspend fun upsert(joke: Joke)
}