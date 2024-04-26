package com.heinnovator.koenawin

import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.heinnovator.koenawin.compose.MainScreen
import com.heinnovator.koenawin.ui.theme.KoeNaWinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val calender: Calendar = Calendar.getInstance()
        val weekday = calender.get(Calendar.DAY_OF_WEEK)

        super.onCreate(savedInstanceState)
        setContent {
            KoeNaWinTheme {
                MainScreen(
                    weekDay = weekday
                )
            }
        }
    }
}