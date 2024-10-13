package com.example.presentation.ui.screen.chats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.screen.chats.chat.ChatItem
import com.example.presentation.ui.screen.chats.mock.getMockChats

@Composable
fun ChatsScreen(
    navigateToChat: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(getMockChats()) { item ->
            ChatItem(item = item) { id->
                navigateToChat(id)
            }
        }
    }
}