package com.example.jokeesingleserving.core.datastore.model

import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(val isFirstRun: Boolean = true)