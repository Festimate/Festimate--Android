package com.mtc.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mtc.designsystem.theme.White
import com.mtc.ui.extension.customClickable

@Composable
fun FestimateBasicButton(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    text: String = "",
    textStyle: TextStyle = TextStyle.Default,
    textColor: Color = White,
    clickable: Boolean = true,
    backgroundColor: Color = Color.Unspecified,
    rippleColor: Color = Color.Unspecified,
    borderColor: Color = Color.Unspecified,
    borderWidth: Dp = 0.dp,
    padding: PaddingValues = PaddingValues(0.dp),
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(shape = shape)
            .background(
                color = backgroundColor,
                shape = shape,
            )
            .border(
                width = borderWidth,
                color = borderColor,
                shape = shape,
            )
            .customClickable(
                runIf = clickable,
                rippleColor = rippleColor,
                rippleEnabled = false,
                onClick = onClick,
            )
            .padding(padding),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = textStyle,
            color = textColor,
            maxLines = 1,
        )
    }
}
