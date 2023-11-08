package com.example.jokeesingleserving.core.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Joke(
    val content: String,
    val isLiked: Boolean? = null,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)