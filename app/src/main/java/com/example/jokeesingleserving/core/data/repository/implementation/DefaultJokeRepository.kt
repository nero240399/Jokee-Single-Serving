package com.example.jokeesingleserving.core.data.repository.implementation

import com.example.jokeesingleserving.core.common.coroutine.di.ApplicationScope
import com.example.jokeesingleserving.core.data.repository.JokeRepository
import com.example.jokeesingleserving.core.database.dao.JokeDao
import com.example.jokeesingleserving.core.datastore.PreferencesDataSource
import com.example.jokeesingleserving.core.model.Joke
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class DefaultJokeRepository @Inject constructor(
    private val jokeDao: JokeDao,
    private val preferencesDataSource: PreferencesDataSource,

    @ApplicationScope
    private val scope: CoroutineScope
) : JokeRepository {

    private val _currentJoke = MutableStateFlow<Joke?>(null)
    override val currentJoke: StateFlow<Joke?> = _currentJoke.asStateFlow()

    override suspend fun feedbackJoke(isLiked: Boolean) {
        currentJoke.value?.let {
            jokeDao.upsert(it.copy(isLiked = isLiked))
        }
    }

    // Simulate loading joke is long-running task requiring preloading
    override suspend fun preloadJoke() {
        // For simulating
        delay(3.seconds)
        if (preferencesDataSource.getIsFirstRun()) {
            insertJokes()
            preferencesDataSource.setIsFirstRunIntoFalse()
        }
        jokeDao.observeCurrent()
            .onEach { _currentJoke.value = it }
            .launchIn(scope)
    }

    private suspend fun insertJokes() {
        jokeDao.upsert(
            Joke(
                """
            A child asked his father, "How were people born?" So his father said, "Adam and Eve made babies, then their babies became adults and made babies, and so on."

            The child then went to his mother, asked her the same question and she told him, "We were monkeys then we evolved to become like we are now."

            The child ran back to his father and said, "You lied to me!" His father replied, "No, your mom was talking about her side of the family."
        """.trimIndent()
            )
        )
        jokeDao.upsert(
            Joke(
                """
            Teacher: "Kids,what does the chicken give you?" Student: "Meat!" Teacher: "Very good! Now what does the pig give you?" Student: "Bacon!" Teacher: "Great! And what does the fat cow give you?" Student: "Homework!"
        """.trimIndent()
            )
        )
        jokeDao.upsert(
            Joke(
                """
            The teacher asked Jimmy, "Why is your cat at school today Jimmy?" Jimmy replied crying, "Because I heard my daddy tell my mommy, 'I am going to eat that pussy once Jimmy leaves for school today!'"
        """.trimIndent()
            )
        )
        jokeDao.upsert(
            Joke(
                """
            A housewife, an accountant and a lawyer were asked "How much is 2+2?" The housewife replies: "Four!". The accountant says: "I think it's either 3 or 4. Let me run those figures through my spreadsheet one more time." The lawyer pulls the drapes, dims the lights and asks in a hushed voice, "How much do you want it to be?"
        """.trimIndent()
            )
        )
    }
}