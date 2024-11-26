package com.mtc.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mtc.designsystem.R
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray04
import com.mtc.designsystem.theme.Gray05
import com.mtc.designsystem.theme.Gray06
import com.mtc.designsystem.theme.MainCoral
import com.mtc.designsystem.theme.SubCoral
import com.mtc.model.MatchingInfo

@Composable
fun MatchingContainer(
    modifier: Modifier = Modifier,
    matchingInfo: MatchingInfo,
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_success_matching_bg),
            contentDescription = "matching",
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(0.2f))
            MatchingMeetingInfo(icon = R.drawable.ic_location, title = "만남장소", content = matchingInfo.location)
            Spacer(modifier = Modifier.weight(0.1f))
            MatchingMeetingInfo(icon = R.drawable.ic_time_appointment_14, title = "약속시간", content = matchingInfo.time)
            Spacer(modifier = Modifier.weight(0.3f))
            Row(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = matchingInfo.nickname,
                    style = FestimateTheme.typography.titleExtra24,
                    color = MainCoral,
                    textAlign = TextAlign.Center,
                )
                Text(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = SubCoral,
                            shape = CircleShape,
                        )
                        .clip(CircleShape)
                        .background(color = SubCoral)
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    text = "매칭 완료",
                    style = FestimateTheme.typography.capRegular11.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    color = MainCoral,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.weight(0.05f))
            MatchingMeetingInfo(icon = R.drawable.ic_university_hat, title = matchingInfo.university)
            Spacer(modifier = Modifier.weight(0.07f))
            MatchingMeetingSubInfo(firstText = matchingInfo.age, secondText = matchingInfo.mbti)
            Spacer(modifier = Modifier.weight(0.05f))
            MatchingMeetingSubInfo(firstText = matchingInfo.faceFirst, secondText = matchingInfo.faceSecond)
            Spacer(modifier = Modifier.weight(0.05f))
            MatchingMeetingClothInfo(todayCloth = matchingInfo.todayCloth)
            Spacer(modifier = Modifier.weight(0.18f))
        }
    }
}

@Composable
fun MatchingMeetingInfo(
    modifier: Modifier = Modifier,
    icon: Int,
    title: String,
    content: String = "",
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .padding(end = 10.dp),
            painter = painterResource(id = icon),
            contentDescription = "meetingInfo",
        )
        Text(
            modifier = Modifier
                .padding(end = 12.dp),
            text = title,
            style = FestimateTheme.typography.bodySemibold15,
            color = Gray04,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = Modifier,
            text = content,
            style = FestimateTheme.typography.bodySemibold15,
            color = MainCoral,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun MatchingMeetingSubInfo(
    modifier: Modifier = Modifier,
    firstText: String,
    secondText: String? = null,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            modifier = Modifier.padding(end = 12.dp),
            text = "#$firstText",
            textAlign = TextAlign.Start,
            style = FestimateTheme.typography.bodySemibold15,
            color = Gray06,
            maxLines = 1,
        )
        if (!secondText.isNullOrBlank()) {
            Text(
                text = "#$secondText",
                textAlign = TextAlign.Start,
                style = FestimateTheme.typography.bodySemibold15,
                color = Gray06,
                maxLines = 1,
            )
        }
        Spacer(modifier = Modifier.weight(0.8f))
    }
}

@Composable
fun MatchingMeetingClothInfo(
    modifier: Modifier = Modifier,
    todayCloth: String,
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
    ) {
        Image(painter = painterResource(id = R.drawable.img_success_matching_content), contentDescription = "cloth")
        Text(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(vertical = 28.dp, horizontal = 14.dp)
                .verticalScroll(rememberScrollState()),
            text = todayCloth,
            style = FestimateTheme.typography.bodyMedium13,
            color = Gray05,
        )
    }
}
