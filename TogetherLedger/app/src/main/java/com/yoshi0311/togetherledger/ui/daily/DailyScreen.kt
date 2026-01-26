package com.yoshi0311.togetherledger.ui.daily

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yoshi0311.togetherledger.model.LedgerItem
import java.text.NumberFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.random.Random

@Preview(showBackground = true)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyScreen() {

    val startDate = LocalDate.of(2026, 1, 1)
    val endDate = LocalDate.of(2026, 1, 12)

    // 날짜 리스트 역순
    val dates = (0..endDate.dayOfMonth - startDate.dayOfMonth).map {
        endDate.minusDays(it.toLong())
    }

    // 날짜별 아이템 생성
    val dateWithItems = dates.map { date ->
        val itemCount = Random.nextInt(2, 7) // 0~5개 아이템
        date to List(itemCount) { "금액: ${Random.nextInt(1000, 100_000)}원" }
    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        dateWithItems.forEach { (date, items) ->
            item {
                HeaderItem(text = "${date.monthValue.toString().padStart(2,'0')}월${date.dayOfMonth.toString().padStart(2,'0')}일(${date.dayOfWeek.name.take(3)})")
            }
            items(items) { ledgerItem ->
                LedgerListItem(item = LedgerItem(
                    category = "식비",
                    content = "BHC 저녁",
                    timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                    amount = 50_000,
                    assetType = "국민은행",
                    isIncome = false,
                    id = 0
                ))
            }
        }
    }
}

@Composable
fun LedgerListItem(item: LedgerItem) {
    val dateTime = LocalDateTime.parse(item.timestamp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // 좌측: 분류
        Text(
            text = item.category,
            modifier = Modifier.weight(1f), // 좌측 정렬
            fontSize = 14.sp
        )

        // 중앙: 내용 + 시각
        Column(
            modifier = Modifier.weight(3f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item.content,
                fontSize = 16.sp
            )
            Text(
                // text = item.timestamp,
                text = dateTime.format(DateTimeFormatter.ofPattern("a hh:mm:ss")),
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        // 우측: 금액
        Box(
            modifier = Modifier
                .weight(1f)  // 좌/중/우 비율 유지
                .fillMaxWidth(), // Box가 전체 weight 공간을 차지
            contentAlignment = Alignment.CenterEnd // 우측 정렬
        ) {
            Text(
                // text = "${item.amount}원",
                text = "${NumberFormat.getNumberInstance(Locale.KOREA).format(item.amount)}원",
                fontSize = 16.sp,
                color = if (item.isIncome) Color.Red else Color.Blue
            )
        }
    }
}

@Composable
fun HeaderItem(text: String = "01월12일(월)") {
    Text(text = text)
}