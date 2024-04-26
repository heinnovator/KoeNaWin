package com.heinnovator.koenawin.compose

import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.heinnovator.koenawin.R
import com.heinnovator.koenawin.ui.theme.CornerShape
import com.heinnovator.koenawin.ui.theme.KoeNaWinTheme
import com.heinnovator.koenawin.ui.theme.pyidaungsu
import com.heinnovator.koenawin.ui.theme.spacing
import com.heinnovator.koenawin.utils.mySharedPrefs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onTimeSelected: (Calendar) -> Unit
) {
    var saveHour by LocalContext.current.mySharedPrefs("hour",0)
    var saveMinute by LocalContext.current.mySharedPrefs("minute",0)

    val timePickerState = rememberTimePickerState(
        initialHour = saveHour, initialMinute = saveMinute
    )

    Dialog(onDismissRequest = { onDismissRequest() }) {
        OutlinedCard(
            shape = CornerShape.medium
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(all = spacing.marginPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.notification_dialog_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = pyidaungsu,
                    fontWeight = FontWeight.Bold
                )
                HorizontalDivider(modifier = modifier.padding(0.dp, spacing.linePadding))
                TimeInput(state = timePickerState)
                Button(
                    modifier = modifier
                        .fillMaxWidth(),
                    onClick = {
                        val schedule = Calendar.getInstance().apply {
                            timeInMillis = System.currentTimeMillis()
                            set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                            set(Calendar.MINUTE, timePickerState.minute)
                        }
                        onTimeSelected(schedule)
                        saveHour = timePickerState.hour
                        saveMinute = timePickerState.minute
                    }
                ) {
                    Text(text = stringResource(R.string.notify_confirm_button_text))
                }
            }
        }
    }
}

@Preview
@Composable
fun ReminderDialogPreview() {
    KoeNaWinTheme {
        ReminderDialog(
            onDismissRequest = { },
            onTimeSelected = { }
        )
    }
}