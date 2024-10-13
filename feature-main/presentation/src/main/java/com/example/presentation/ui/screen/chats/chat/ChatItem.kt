package com.example.presentation.ui.screen.chats.chat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.composable.CustomText
import com.example.presentation.ui.screen.chats.mock.ChatModel
import com.example.presentation.ui.screen.profile.composable.UserAvatar

@Composable
fun ChatItem(
    modifier: Modifier = Modifier,
    item: ChatModel,
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .clickable {
                onItemClick(item.id)
            },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserAvatar(
                modifier = Modifier
                    .size(60.dp),
                model = item.avatar
            )
            Column(
                modifier = Modifier.padding(start = 8.dp),
            ) {
                CustomText(text = item.name)
                CustomText(text = item.lastMessage, color = Color.Gray, fontSize = 14.sp)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
    }
}