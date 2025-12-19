package com.example.androidtasks.FirstModule

import kotlin.reflect.KProperty

class SecondTaskDelegate {
    private var cashedTime: Long? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>) : Long {
        if (cashedTime == null) cashedTime = System.currentTimeMillis()
        return cashedTime!!
    }

}
