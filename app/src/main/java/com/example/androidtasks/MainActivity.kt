package com.example.androidtasks

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.androidtasks.FirstModule.SecondTaskDelegate
import com.example.androidtasks.FirstModule.getInt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = listOf("a", 'b', 5_000_000_000L, 4389, 254.46, true, 45, "sdvf")
        Log.d("FirstTask", list.getInt().toString())


        val startTime by SecondTaskDelegate()
        lifecycleScope.launch(Dispatchers.IO) {
            while (true) {
                Log.d("SecondTask", startTime.toString())
                delay(3000)
            }
        }
    }
}
