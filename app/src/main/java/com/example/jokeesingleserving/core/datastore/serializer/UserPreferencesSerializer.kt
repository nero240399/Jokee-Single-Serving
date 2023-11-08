package com.example.jokeesingleserving.core.datastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.jokeesingleserving.core.common.coroutine.Dispatcher
import com.example.jokeesingleserving.core.common.coroutine.JokeDispatcher.IO
import com.example.jokeesingleserving.core.datastore.model.UserPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class UserPreferencesSerializer @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : Serializer<UserPreferences> {

    override val defaultValue: UserPreferences = UserPreferences()

    override suspend fun readFrom(input: InputStream): UserPreferences =
        try {
            Json.decodeFromString(input.readBytes().decodeToString())
        } catch (exception: SerializationException) {
            throw CorruptionException("Cannot read UserData.", exception)
        }

    override suspend fun writeTo(t: UserPreferences, output: OutputStream) =
        withContext(ioDispatcher) {
            @Suppress("BlockingMethodInNonBlockingContext")
            output.write(Json.encodeToString(t).encodeToByteArray())
        }
}
