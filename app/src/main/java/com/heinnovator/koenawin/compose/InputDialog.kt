package com.heinnovator.koenawin.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.heinnovator.koenawin.R
import com.heinnovator.koenawin.data.Repo
import com.heinnovator.koenawin.ui.theme.CornerShape
import com.heinnovator.koenawin.ui.theme.KoeNaWinTheme
import com.heinnovator.koenawin.ui.theme.pyidaungsu
import com.heinnovator.koenawin.ui.theme.spacing
import com.heinnovator.koenawin.utils.mySharedPrefs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDialog(
    weekDay: Int,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirm: (Int) -> Unit
){
    val list = Repo.getList()
    var saveLevel by LocalContext.current.mySharedPrefs("slider_level", 1)

    var sliderLevel by remember { mutableFloatStateOf(saveLevel.toFloat()) }
    var isMenuExpanded by remember { mutableStateOf(false) }

    val validItemList by remember(sliderLevel) {
        derivedStateOf {
            list.filter {
                it.level == sliderLevel.toInt() && it.day == weekDay
            }
        }
    }
    var selectedTitle by remember(validItemList) {
        mutableStateOf(
            validItemList.first().title
        )
    }
    val scrollIndex = validItemList.find {
        it.title == selectedTitle
    }

    Dialog (onDismissRequest = { onDismissRequest() }) {
        OutlinedCard(
            shape = CornerShape.medium
        ) {
            Column(
                modifier = modifier
                    .padding(all = spacing.marginPadding)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.level, sliderLevel.toInt()),
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = pyidaungsu,
                    fontWeight = FontWeight.Bold,
                )
                HorizontalDivider(modifier = modifier.padding(0.dp, spacing.linePadding))
                Slider(
                    value = sliderLevel,
                    onValueChange = {
                        sliderLevel = it
//                        validItemList = viewModel.filter(sliderLevel)
//                        selectedTitle = validItemList[0].title
                    },
                    onValueChangeFinished = { },
                    steps = 7,
                    valueRange = 1f..9f
                )
                ExposedDropdownMenuBox(
                    expanded = isMenuExpanded,
                    onExpandedChange = { isMenuExpanded = !isMenuExpanded }) {

                    OutlinedTextField(
                        value = selectedTitle,
                        onValueChange = {},
                        readOnly = true,
                        modifier = modifier
                            .menuAnchor(),
                        label = { Text(stringResource(R.string.day_selection_label)) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isMenuExpanded) },
                    )

                    ExposedDropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissRequest = { isMenuExpanded = false }) {

                        validItemList.forEach { options ->
                            DropdownMenuItem(
                                text = { Text(text = options.title) },
                                onClick = {
                                    selectedTitle = options.title
                                    isMenuExpanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
                Spacer(modifier = modifier.height(spacing.linePadding))
                Button(
                    modifier = modifier
                        .fillMaxWidth(),
                    onClick = {
                        saveLevel = sliderLevel.toInt()
                        onConfirm((scrollIndex?.id ?: 1) - 1)
                    }
                ) {
                    Text(text = stringResource(R.string.set_pager_button_text))
                }
            }
        }
    }
}

@Preview
@Composable
fun InputDialogPreview() {
    KoeNaWinTheme {
        InputDialog(
            weekDay = 0,
            onDismissRequest = { },
            onConfirm = { }
        )
    }
}