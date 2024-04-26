package com.heinnovator.koenawin.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.heinnovator.koenawin.data.Repo
import com.heinnovator.koenawin.notification.NotificationScheduler
import com.heinnovator.koenawin.utils.VibrationHelper
import com.heinnovator.koenawin.utils.mySharedPrefs
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    weekDay: Int
){
    val context = LocalContext.current.applicationContext
    val scheduler by lazy { NotificationScheduler(context) }
    val scope = rememberCoroutineScope()
    val list = Repo.getList()

    var savePageNumber by context.mySharedPrefs("page_no",0)
    var saveCounter by context.mySharedPrefs("count_no",0)
    var saveSliderLevel by context.mySharedPrefs("slider_level", 1)

    var pageNumber by remember { mutableIntStateOf(savePageNumber) }
    var clickCounter by remember { mutableIntStateOf(saveCounter) }
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var showTimePicker by rememberSaveable { mutableStateOf(false) }

    val pagerState = rememberPagerState(pageCount = { list.size }, initialPage = savePageNumber)
    val snackHostState = remember { SnackbarHostState() }
    val formatter = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackHostState)
        },
        topBar = {
            Appbar(
                onDialogButton = { showDialog = true },
                onNotificationButton = { showTimePicker = true }
            )
        },
        content = { paddingValue ->
            if (showDialog){
                InputDialog(
                    weekDay = weekDay,
                    onDismissRequest = { showDialog = false },
                    onConfirm = { index ->
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                        clickCounter = 0; savePageNumber = index; showDialog = false
                    }
                )
            }
            if (showTimePicker){
                ReminderDialog(
                    onDismissRequest = {
                        showTimePicker = false
                        scheduler.cancelSchedule()
                    },
                    onTimeSelected = { cal ->
                        scheduler.scheduleReminderNotification(cal)
                        showTimePicker = false
                        scope.launch {
                            snackHostState.showSnackbar(
                                message = "Reminder: ${formatter.format(cal.time)}",
                                withDismissAction = true
                            )
                        }
                    }
                )
            }
            Column(
                modifier = modifier
                    .padding(top = paddingValue.calculateTopPadding())
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                HorizontalPager(
                    state = pagerState,
                    userScrollEnabled = false,
                    beyondBoundsPageCount = 0,
                    key = { list[it].id },
                    modifier = modifier
                        .fillMaxWidth()
                ) { page ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CounterCard(
                            counter = clickCounter,
                            item = list[page],
                            onCardClick = {
                                if (list[page].day != weekDay) {
                                    return@CounterCard
                                } else {
                                    clickCounter++
                                    saveCounter = clickCounter
                                    if (clickCounter % 108 == 0) {
                                        VibrationHelper(context).vibrate()
                                    }
                                    if (clickCounter > list[page].count) {
                                        clickCounter = 0; saveCounter = 0

                                        pageNumber = if (page == list.size - 1 ) 0 else pagerState.currentPage + 1
                                        savePageNumber = pageNumber
                                        saveSliderLevel = list[pageNumber].level
                                        scope.launch {
                                            pagerState.animateScrollToPage(pageNumber)
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
                Row(
                    modifier = modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    val dots = if (pagerState.currentPage < 9) pagerState.currentPage
                    else (pagerState.currentPage % 9)
                    repeat(9) { iteration ->
                        val color = if (dots == iteration) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant
                        Box(
                            modifier = modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(8.dp)
                        )
                    }
                }
            }
        }
    )
}