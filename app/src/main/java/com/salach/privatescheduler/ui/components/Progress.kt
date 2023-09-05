package com.salach.privatescheduler.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Progress(progress: Float) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 4.dp)
    )
    {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 0.dp)
                .fillMaxWidth()
                .height(height = 4.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .background(color = Color(0xffb3b3b3)))
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp, y = 0.dp)
                .fillMaxWidth(progress)
                .height(height = 4.dp)
                .clip(shape = MaterialTheme.shapes.small)
                .background(color = Color(0xffd8bfd8)))
    }
}

@Preview
@Composable
fun PreviewProgress() {
    Progress(0.17f)
}
