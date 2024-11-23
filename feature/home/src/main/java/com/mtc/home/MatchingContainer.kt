package com.mtc.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
        modifier = modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_success_matching_bg),
            contentDescription = "matching",
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(start = 18.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = modifier.weight(0.08f))
            MatchingMeetingInfo(modifier, R.drawable.ic_location, "만남장소", matchingInfo.location)
            Spacer(modifier = modifier.weight(0.04f))
            MatchingMeetingInfo(modifier, R.drawable.ic_time_appointment_14, "약속시간", matchingInfo.time)
            Spacer(modifier = modifier.weight(0.12f))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    modifier = modifier,
                    text = matchingInfo.nickname,
                    style = FestimateTheme.typography.titleExtra24,
                    color = MainCoral,
                    textAlign = TextAlign.Center,
                )
                Text(
                    modifier = modifier
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
            Spacer(modifier = modifier.weight(0.02f))
            MatchingMeetingInfo(modifier, R.drawable.ic_university_hat, matchingInfo.university)
            Spacer(modifier = modifier.weight(0.03f))
            MatchingMeetingSubInfo(modifier, matchingInfo.age, matchingInfo.mbti)
            Spacer(modifier = modifier.weight(0.03f))
            MatchingMeetingSubInfo(modifier, matchingInfo.faceFirst, matchingInfo.faceSecond)
            Spacer(modifier = modifier.weight(0.03f))
            MatchingMeetingClothInfo(modifier, matchingInfo.todayCloth)
            Spacer(modifier = modifier.weight(0.65f))
        }
    }
}

@Composable
fun MatchingMeetingInfo(
    modifier: Modifier,
    icon: Int,
    title: String,
    content: String = "",
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = modifier
                .padding(end = 10.dp),
            painter = painterResource(id = icon),
            contentDescription = "meetingInfo",
        )
        Text(
            modifier = modifier
                .padding(end = 12.dp),
            text = title,
            style = FestimateTheme.typography.bodySemibold15,
            color = Gray04,
            textAlign = TextAlign.Center,
        )
        Text(
            modifier = modifier,
            text = content,
            style = FestimateTheme.typography.bodySemibold15,
            color = MainCoral,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun MatchingMeetingSubInfo(
    modifier: Modifier,
    firstText: String,
    secondText: String? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
    ) {
        Text(
            text = "#$firstText",
            textAlign = TextAlign.Start,
            style = FestimateTheme.typography.bodySemibold15,
            color = Gray06,
            maxLines = 1,
        )
        Spacer(modifier = modifier.weight(0.08f))
        if (!secondText.isNullOrBlank()) {
            Text(
                text = "#$secondText",
                textAlign = TextAlign.Start,
                style = FestimateTheme.typography.bodySemibold15,
                color = Gray06,
                maxLines = 1,
            )
        }
        Spacer(modifier = modifier.weight(0.92f))
    }
}

@Composable
fun MatchingMeetingClothInfo(
    modifier: Modifier,
    todayCloth: String,
) {
    Box {
        Image(painter = painterResource(id = R.drawable.img_success_matching_content), contentDescription = "cloth")
        Text(
            modifier = modifier
                .padding(vertical = 28.dp, horizontal = 14.dp)
                .verticalScroll(rememberScrollState()),
            text = todayCloth,
            textAlign = TextAlign.Start,
            style = FestimateTheme.typography.bodyMedium13,
            color = Gray05,
        )
    }
}
