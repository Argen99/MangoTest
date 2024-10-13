package com.example.presentation.ui.screen.chats.mock

data class ChatModel(
    val id: String,
    val avatar: String,
    val name: String,
    val lastMessage: String,
    val messages: List<Message>
)

data class Message(
    val senderName: String,
    val content: String,
    val isSentByUser: Boolean
)
