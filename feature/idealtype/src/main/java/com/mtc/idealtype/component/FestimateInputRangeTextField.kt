package com.mtc.idealtype.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mtc.designsystem.component.FestimateBasicTextField
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray04
import com.mtc.designsystem.theme.Gray06
import com.mtc.idealtype.IdealTypeState

@Composable
fun FestimateInputRangeTextField(
    modifier: Modifier = Modifier,
    uiState: IdealTypeState,
    minPlaceholder: String,
    maxPlaceholder: String,
    subtitle: String,
    minValue: String,
    maxValue: String,
    updateMinValueChange: (String) -> Unit,
    updateMaxValueChange: (String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        FestimateBasicTextField(
            value = minValue,
            onValueChange = { updateMinValueChange(it) },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp),
            placeholder = minPlaceholder,
            textStyle = FestimateTheme.typography.bodyMedium15,
            textColor = Gray04,
            maxLength = 3,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword,
            ),
        )
        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .width(10.dp),
            thickness = 2.dp,
            color = Gray06,
        )
        FestimateBasicTextField(
            value = maxValue,
            onValueChange = { updateMaxValueChange(it) },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp),
            placeholder = maxPlaceholder,
            textStyle = FestimateTheme.typography.bodyMedium15,
            textColor = Gray04,
            maxLength = 3,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword,
            ),
        )
        Text(
            modifier = Modifier.padding(start = 10.dp, end = 56.dp),
            text = subtitle,
            style = FestimateTheme.typography.bodySemibold17,
            color = Gray06,
        )
    }
}
