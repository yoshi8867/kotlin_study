package com.example.composestudydemo20252016

import android.util.Log.e
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class DemoViewModel : ViewModel() {

    var isFarenheit by mutableStateOf(true)
    var result by mutableStateOf("")

    fun convertTemp(temp: String) {
        result = try {
            val tempInt = temp.toInt()
            if (isFarenheit) {
                ((tempInt-32)*0.5556).roundToInt().toString()
            } else {
                ((tempInt*1.8)+32).roundToInt().toString()
            }
        } catch (e: Exception) {
            result = "invalid"
        } as String
    }

    fun switchChange() {
        isFarenheit = !isFarenheit
    }
}