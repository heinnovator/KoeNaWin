package com.heinnovator.koenawin

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import com.heinnovator.koenawin.data.Item
import com.heinnovator.koenawin.data.Repo

class MainViewModel: ViewModel() {

    private val list = Repo.getList()

    private val calender: Calendar = Calendar.getInstance()
    private val weekday = calender.get(Calendar.DAY_OF_WEEK)

    fun filter(sliderLevel: Float): List<Item> =
        list.filter { item ->
            item.level == sliderLevel.toInt() && item.day == weekday
        }

    fun scrollIndex(sliderLevel: Float, title: String): Int =
        list.indexOfFirst {
            it.level == sliderLevel.toInt() && title == it.title
        }
}

//    var validItemList = viewModel.filter(sliderLevel)
//    var selectedTitle by remember { mutableStateOf(validItemList[0].title) }
//    val scrollIndex = viewModel.scrollIndex(sliderLevel, selectedTitle)