package com.example.androidtasks.FourthModule

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.androidtasks.R

class FirstFragment : Fragment(R.layout.fragment_first)
class SecondFragment : Fragment(R.layout.fragment_second)
class ThirdFragment : Fragment(R.layout.fragment_third)


class AppRouter(
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) {
    fun open(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(null)
            .commit()
    }
    fun back() {
        fragmentManager.popBackStack()
    }
}

// инициализация роутера в Activity

val router = AppRouter(
    supportFragmentManager,
    R.id.fragmentContainer
)

// использование во фрагментах

// FirstFragment
nextButton.setOnClickListener {
    router.open(SecondFragment())
}

backButton.setOnClickListener {
    router.back()
}

// SecondFragment
nextButton.setOnClickListener {
    router.open(ThirdFragment())
}

backButton.setOnClickListener {
    router.back()
}

// ThirdFragment
nextButton.setOnClickListener {
    router.open(FirstFragment())
}

backButton.setOnClickListener {
    router.back()
}
