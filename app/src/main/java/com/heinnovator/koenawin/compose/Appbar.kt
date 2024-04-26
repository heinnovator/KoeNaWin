package com.heinnovator.koenawin.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.heinnovator.koenawin.R
import com.heinnovator.koenawin.ui.theme.KoeNaWinTheme
import com.heinnovator.koenawin.ui.theme.phetsot

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appbar(
    onDialogButton: () -> Unit,
    onNotificationButton: () -> Unit
){
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                onDialogButton()
            }) {
                Icon(
                    imageVector = Icons.Rounded.CalendarMonth,
                    contentDescription = "Date Selection"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                onNotificationButton()
            }) {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Notification Time"
                )
            }
        },
        title = {
            Text(
                text = stringResource(R.string.app_name_burmese),
                style = MaterialTheme.typography.headlineSmall,
                fontFamily = phetsot,)
        }
    )
}

@Preview
@Composable
fun AppbarPreview() {
    KoeNaWinTheme {
        Appbar(
            onDialogButton = { },
            onNotificationButton = {}
        )
    }
}