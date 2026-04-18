package com.example.androidtasks.FourthModule

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.androidtasks.R

class FirstFragment : Fragment(R.layout.fragment_first)
class SecondFragment : Fragment(R.layout.fragment_second)
class ThirdFragment : Fragment(R.layout.fragment_third)

enum class Screen {
    FIRST,
    SECOND,
    THIRD
}

class AppRouter(
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) {
    private var currentScreen: Screen = Screen.FIRST

    init {
        openFirst()
    }

    fun next() {
        when (currentScreen) {
            Screen.FIRST -> openSecond()
            Screen.SECOND -> openThird()
            Screen.THIRD -> openFirst()
        }
    }

    fun back() {
        when (currentScreen) {
            Screen.THIRD -> openSecond()
            Screen.SECOND -> openFirst()
            Screen.FIRST -> openThird()
        }
    }

    private fun openFirst() {
        fragmentManager.beginTransaction()
            .replace(containerId, FirstFragment())
            .commit()

        currentScreen = Screen.FIRST
    }

    private fun openSecond() {
        fragmentManager.beginTransaction()
            .replace(containerId, SecondFragment())
            .commit()

        currentScreen = Screen.SECOND
    }

    private fun openThird() {
        fragmentManager.beginTransaction()
            .replace(containerId, ThirdFragment())
            .commit()

        currentScreen = Screen.THIRD
    }
}

// инициализация роутера в Activity

val router = AppRouter(
    supportFragmentManager,
    R.id.fragmentContainer
)

// использование во фрагментах

nextButton.setOnClickListener {
    router.next()
}

backButton.setOnClickListener {
    router.back()
}
