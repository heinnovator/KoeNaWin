package com.heinnovator.koenawin.compose

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.heinnovator.koenawin.R
import com.heinnovator.koenawin.data.Item
import com.heinnovator.koenawin.data.Repo
import com.heinnovator.koenawin.ui.theme.CornerShape
import com.heinnovator.koenawin.ui.theme.KoeNaWinTheme
import com.heinnovator.koenawin.ui.theme.kanote7
import com.heinnovator.koenawin.ui.theme.phetsot
import com.heinnovator.koenawin.ui.theme.pyidaungsu
import com.heinnovator.koenawin.ui.theme.spacing

@Composable
fun CounterCard(
    counter: Int,
    item: Item,
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit
) {
    var showInfoDialog by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = counter.toFloat()/(item.count),
        label = "progress_animation"
    )
    if (showInfoDialog){
        InfoDialog(
            level = { item.level },
            onDismissRequest = {showInfoDialog = false }
        )
    }
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.85f)
            .padding(spacing.marginPadding)
            .clickable { onCardClick() },
        shape = CornerShape.medium
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(all = spacing.marginPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.headlineSmall,
                fontFamily = phetsot,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = modifier.height(spacing.linePadding))
            ElevatedAssistChip(
                onClick = { showInfoDialog = true },
                label = {
                    Text(
                        text = stringResource(R.string.level, item.level)
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    labelColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
            HorizontalDivider(modifier = modifier.padding(0.dp, spacing.linePadding))
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1.0f),
                text = item.desc,
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = pyidaungsu
            )
            Text(
                text = "wer",
                fontFamily = kanote7,
                style = MaterialTheme.typography.displayMedium
            )
            LinearProgressIndicator(
                progress = { progress },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(0.dp, spacing.linePadding),
            )
            Row {
                AnimatedContent(
                    targetState = counter,
                    transitionSpec = {
                        if (targetState > initialState) {
                            (slideInVertically { height -> height } + fadeIn()).togetherWith(
                                slideOutVertically { height -> -height } + fadeOut())
                        } else {
                            (slideInVertically { height -> -height } + fadeIn()).togetherWith(
                                slideOutVertically { height -> height } + fadeOut())
                        }.using(
                            SizeTransform(clip = false)
                        )
                    }, label = "text_animation"
                ) { targetCount ->
                    Text(
                        text = "$targetCount",
                        style = MaterialTheme.typography.titleLarge)
                }
                Text(
                    text = " / ${item.count}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun InfoDialog(
    modifier: Modifier = Modifier,
    level: () -> Int,
    onDismissRequest: () -> Unit
){
    Dialog(
        onDismissRequest = { onDismissRequest() }) {
        OutlinedCard(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = CornerShape.medium
        ) {
            Column(
                modifier = modifier
                    .padding(spacing.marginPadding)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.benefits_dialog_title),
                    fontFamily = phetsot,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                HorizontalDivider(modifier = modifier.padding(0.dp, spacing.linePadding))
                Text(
                    text = Repo.getBenefits(level.invoke()),
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = pyidaungsu,
                    modifier = modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = modifier.height(spacing.linePadding))
                Text(
                    text = "uio",
                    fontFamily = kanote7,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun CardPreview(){
    KoeNaWinTheme {
        CounterCard(
            counter =  1 ,
            item =  Item(1, "Title", "Desc", 0, 1, 1),
            onCardClick = { }
        )
    }
}

@Preview
@Composable
fun InfoDialogPreview(){
    KoeNaWinTheme {
        InfoDialog(
            level = { 1 },
            onDismissRequest = { }
        )
    }
}
