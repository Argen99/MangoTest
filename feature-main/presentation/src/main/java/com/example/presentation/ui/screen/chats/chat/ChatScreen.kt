package com.example.presentation.ui.screen.chats.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.core_ui.composable.CustomText
import com.example.presentation.ui.screen.chats.mock.getMockChats
import com.example.presentation.ui.screen.profile.composable.UserAvatar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    argument: String
) {
    val value = remember { mutableStateOf("") }
    val currentChat = getMockChats().find { it.id == argument }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            modifier = Modifier.shadow(elevation = 4.dp),
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    UserAvatar(modifier = Modifier.size(60.dp), model = currentChat?.avatar)
                    Spacer(modifier = Modifier.width(12.dp))
                    CustomText(text = currentChat?.name ?: "")
                }
            },
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            reverseLayout = true
        ) {
            items(currentChat?.messages?.reversed() ?: emptyList()) { message ->
                MessageItem(message = message)
            }
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            value = value.value,
            onValueChange = {
                value.value = it
            },
            placeholder = {
                CustomText(text = "Введите сообщение")
            },
            trailingIcon = {
                Icon(imageVector = Icons.AutoMirrored.Default.Send, contentDescription = null)
            }
        )
    }
}