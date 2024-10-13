package com.example.presentation.ui.screen.chats.mock

fun getMockChats(): List<ChatModel> {
    return listOf(
        ChatModel(
            id = "1",
            name = "Анна",
            lastMessage = "Привет, как дела?",
            avatar = "https://img.freepik.com/free-vector/funny-serious-cat-animal-meme_23-2148974916.jpg",
            messages = listOf(
                Message(
                    senderName = "Анна",
                    content = "Привет! Как дела?",
                    isSentByUser = false
                ),
                Message(
                    senderName = "Вы",
                    content = "Привет! Все хорошо, а у тебя?",
                    isSentByUser = true
                ),
                Message(
                    senderName = "Анна",
                    content = "Хорошо, спасибо!",
                    isSentByUser = false
                )
            )
        ),
        ChatModel(
            id = "2",
            name = "Борис",
            lastMessage = "До встречи завтра!",
            avatar = "https://buffer.com/library/content/images/size/w1200/2023/10/free-images.jpg",
            messages = listOf(
                Message(
                    senderName = "Борис",
                    content = "Привет! Как дела?",
                    isSentByUser = false
                ),
                Message(
                    senderName = "Вы",
                    content = "Привет! Все хорошо, а у тебя?",
                    isSentByUser = true
                ),
                Message(
                    senderName = "Борис",
                    content = "Хорошо, спасибо!",
                    isSentByUser = false
                )
            )
        ),
        ChatModel(
            id = "3",
            name = "Серега Сварщик",
            lastMessage = "Здарова!",
            avatar = "https://thumbs.dreamstime.com/b/woman-praying-free-birds-to-nature-sunset-background-woman-praying-free-birds-enjoying-nature-sunset-99680945.jpg",
            messages = listOf(
                Message(
                    senderName = "Серега Сварщик",
                    content = "Привет! Как дела?",
                    isSentByUser = false
                ),
                Message(
                    senderName = "Вы",
                    content = "Привет! Все хорошо, а у тебя?",
                    isSentByUser = true
                ),
                Message(
                    senderName = "Серега Сварщик",
                    content = "Хорошо, спасибо!",
                    isSentByUser = false
                )
            )
        ),
        ChatModel(
            id = "4",
            name = "Катя",
            lastMessage = "Спасибо!",
            avatar = "https://i0.wp.com/picjumbo.com/wp-content/uploads/woman-with-sun-glasses-in-flower-field-summer-free-photo.jpg",
            messages = listOf(
                Message(
                    senderName = "Катя",
                    content = "Привет! Как дела?",
                    isSentByUser = false
                ),
                Message(
                    senderName = "Вы",
                    content = "Привет! Все хорошо, а у тебя?",
                    isSentByUser = true
                ),
                Message(
                    senderName = "Катя",
                    content = "Хорошо, спасибо!",
                    isSentByUser = false
                )
            )
        ),
        ChatModel(
            id = "5",
            name = "Дружище",
            lastMessage = "Привет, как дела?",
            avatar = "https://gratisography.com/wp-content/uploads/2024/01/gratisography-cyber-kitty-800x525.jpg",
            messages = listOf(
                Message(
                    senderName = "Дружище",
                    content = "Привет! Как дела?",
                    isSentByUser = false
                ),
                Message(
                    senderName = "Вы",
                    content = "Привет! Все хорошо, а у тебя?",
                    isSentByUser = true
                ),
                Message(
                    senderName = "Дружище",
                    content = "Хорошо, спасибо!",
                    isSentByUser = false
                )
            )
        )
    )
}