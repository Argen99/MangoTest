package com.example.presentation.ui.screen.profile.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.composable.CustomText

@Composable
fun ProfileItem(
    value: String?,
    label: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp, bottom = 4.dp)
        ) {
            CustomText(
                text = value ?: "Пусто"
            )
            CustomText(
                text = label,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}