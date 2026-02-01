package com.example.androidtasks.SecondModule

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


fun <T> Flow<T>.throttleFirst(window: Long) : Flow<T> = flow {
    var lastTime = 0L
    collect { value ->
        val currTime = System.currentTimeMillis()
        if (currTime - lastTime >= window) {
            lastTime = currTime
            emit(value)
        }
    }
}

fun <T> Flow<T>.throttleLatest(window: Long) : Flow<T> = channelFlow {
    var hasValue = false
    var latestValue: T? = null

    launch {
        collect { value ->
            hasValue = true
            latestValue = value
        }
    }

    while (true) {
        delay(window)
        if (hasValue) {
            send(latestValue!!)
            hasValue = false
        }
    }
}
