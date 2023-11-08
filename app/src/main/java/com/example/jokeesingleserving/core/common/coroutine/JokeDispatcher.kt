package com.example.jokeesingleserving.core.common.coroutine

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val dispatcher: JokeDispatcher)

enum class JokeDispatcher { Default, IO, }
