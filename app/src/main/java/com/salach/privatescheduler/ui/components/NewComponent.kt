package com.salach.privatescheduler.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.salach.privatescheduler.R

@Composable
fun CreateNewOverlay() {
    Column(
        modifier = Modifier
            .width(width = 380.dp)
            .height(260.dp) // added
            .padding(all = 16.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Color(0xfffafafa))
            .border(
                border = BorderStroke(1.dp, Color(0xffd8bfd8)),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(
            text = "Create New",
            color = Color(0xff242424),
            lineHeight = 1.09.em,
            style = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .wrapContentHeight(align = Alignment.CenterVertically))
        Spacer(
            modifier = Modifier.height(height = 16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
                ContentCard("Task", R.drawable.ic_home_black_24dp, null)
                ContentCard("Shopping list", R.drawable.ic_home_black_24dp, null)
                ContentCard("Recipe", R.drawable.ic_home_black_24dp, null)
                ContentCard("Blank page", R.drawable.ic_home_black_24dp, null)
                ContentCard("Event", R.drawable.ic_home_black_24dp, null)
        }
        Spacer(
            modifier = Modifier.height(height = 16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            ContentCard("Day plan", R.drawable.ic_home_black_24dp, null)
            ContentCard("Goal", R.drawable.ic_home_black_24dp, null)
            ContentCard("Activity plan", R.drawable.ic_home_black_24dp, null)
            ContentCard("Journal", R.drawable.ic_home_black_24dp, null)
            ContentCard("Memory", R.drawable.ic_home_black_24dp, null)
        }
    }
}

@Preview
@Composable
fun PreviewCreateNewOverlay(){
    CreateNewOverlay()
}


interface ContentCardClickListener{
    fun onCardClick()
}

@Composable
fun ContentCard(text: String, icon: Int, clickListener: ContentCardClickListener?) {
    val color = remember { mutableStateOf(Color.Black) }
//    val interactionSource = remember { MutableInteractionSource() }
//    val isPressed by interactionSource.collectIsPressedAsState()
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {
                clickListener?.onCardClick()
            }
            .width(width = 64.dp)
            .padding(all = 8.dp)
            .clip(shape = MaterialTheme.shapes.small)
            .background(color = Color(0xfffafafa)),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.height(height = 48.dp)
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "icon",
                modifier = Modifier.size(size = 32.dp),
                colorFilter = ColorFilter.tint(color.value))
            Text(
                text = text,
                color = Color(0xff242424),
                lineHeight = 1.2.em,
                style = TextStyle(fontSize = 10.sp),
                modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically))
        }
    }
}


@Preview
@Composable
fun PreviewContentCard(){
    ContentCard("House", R.drawable.ic_home_black_24dp, null)
}

