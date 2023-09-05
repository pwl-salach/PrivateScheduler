package com.salach.privatescheduler.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.salach.privatescheduler.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import com.salach.privatescheduler.db.models.Goal


@Composable
fun GoalTracker(icon: Int, title: String, subtitle: String, progress: Float) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .background(color = Color(0xfffafafa))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "icon",
                    modifier = Modifier.size(size = 32.dp)
                )
                Spacer(
                    modifier = Modifier.width(width = 8.dp)
                )
                Column() {
                    Text(
                        text = title,
                        color = Color(0xff272830),
                        lineHeight = 1.14.em,
                        style = TextStyle(
                            fontSize = 14.sp,
                            letterSpacing = 0.1.sp
                        )
                    )
                    Spacer(
                        modifier = Modifier
                            .height(height = 4.dp)
                    )
                    Text(
                        text = subtitle,
                        color = Color(0xff464646),
                        lineHeight = 1.2.em,
                        style = TextStyle(
                            fontSize = 10.sp,
                            letterSpacing = 0.1.sp
                        )
                    )
                }
            }
            Spacer(
                modifier = Modifier.width(width = 220.dp)
            )
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_home_black_24dp),
                        contentDescription = "Add / remove"
                    )
                    Spacer(
                        modifier = Modifier
                            .width(width = 8.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Add / remove"
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier
                .height(height = 8.dp)
        )
        Progress(progress)
    }
}


@Preview
@Composable
fun PreviewGoalTracker() {
    GoalTracker(
        R.drawable.icon_bootle_foreground,
        "Drink water",
        "250ml cups",
        0.30f
    )
}


@Composable
fun CurrentGoals(goals: LiveData<List<Goal>>) {
    val itemsState by goals.observeAsState(emptyList())

    LazyColumn {
        itemsIndexed(itemsState) { _, goal ->
            GoalTracker(goal.icon, goal.title, goal.subtitle, goal.progress)
        }
    }
}

//@Preview
//@Composable
//fun PreviewCurrentGoals() {
//    CurrentGoals(
//        listOf(
//            Goal(R.drawable.icon_bootle_foreground, "Test1", "small test", 0.52f),
//            Goal(R.drawable.ic_dashboard_black_24dp, "Test2", "small test", 0.78f),
//            Goal(R.drawable.ic_launcher_foreground, "Test3", "small test", 0.15f)
//        )
//    )
//}
