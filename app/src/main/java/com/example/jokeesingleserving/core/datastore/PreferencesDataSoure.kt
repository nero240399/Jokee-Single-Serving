package com.example.jokeesingleserving.core.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import com.example.jokeesingleserving.core.datastore.model.UserPreferences
import kotlinx.coroutines.flow.first
import java.io.IOException
import javax.inject.Inject

private const val TAG = "PreferencesDataSource"

class PreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    suspend fun getIsFirstRun(): Boolean = userPreferences.data.first().isFirstRun
    suspend fun setIsFirstRunIntoFalse() = try {
        userPreferences.updateData { UserPreferences(false) }
    } catch (exception: IOException) {
        Log.e(TAG, "Failed to update user preferences.", exception)
    }
}
