package com.example.androidtasks.FirstModule

fun List<Any>.getInt() : Int? = firstOrNull() { it is Int } as? Int
