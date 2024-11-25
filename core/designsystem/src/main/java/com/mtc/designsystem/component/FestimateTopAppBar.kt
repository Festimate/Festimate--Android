package com.mtc.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mtc.designsystem.R
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray06
import com.mtc.designsystem.theme.MainCoral
import com.mtc.designsystem.theme.White
import com.mtc.ui.extension.customClickable

@Composable
fun FestimateTopAppBar(
    modifier: Modifier = Modifier,
    signupPageNumber: String,
    signupPageContent: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 18.dp),
    ) {
        Box(
            modifier = modifier
                .padding(bottom = 9.dp)
                .background(
                    color = MainCoral,
                    shape = CircleShape,
                )
                .size(30.dp)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = signupPageNumber,
                style = FestimateTheme.typography.bodyBold15,
                color = White,
                textAlign = TextAlign.Center,
            )
        }
        Text(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            text = signupPageContent,
            style = FestimateTheme.typography.titleBold20,
            color = Gray06,
            textAlign = TextAlign.Center,
        )
    }
}
