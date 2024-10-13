package com.example.presentation.ui.screen.profile.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun UserAvatar(
    modifier: Modifier = Modifier,
    model: Any?
) {
    AsyncImage(
        modifier = modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.inverseOnSurface),
        contentScale = ContentScale.Crop,
        model = model,
        contentDescription = null,
        placeholder = rememberVectorPainter(image = Icons.Default.Face),
        error = rememberVectorPainter(image = Icons.Default.Face)
    )
}