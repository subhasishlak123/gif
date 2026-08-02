package com.subhasish.gfbot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GFApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GFApp() {
    var textInput by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf("Hi Subhasish! ❤️ How are you?" to false) }

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFE5DDD5))) {
        // WhatsApp Header
        SmallTopAppBar(
            title = { Text("Girlfriend", color = Color.White, fontWeight = FontWeight.Bold) },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF075E54))
        )

        // Chat History
        LazyColumn(
            modifier = Modifier.weight(1f).padding(horizontal = 8.dp),
            reverseLayout = false
        ) {
            items(messages) { (msg, isMe) ->
                ChatBubble(msg, isMe)
            }
        }

        // Input Bar
        Surface(tonalElevation = 2.dp) {
            Row(
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = textInput,
                    onValueChange = { textInput = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Type a message") },
                    shape = RoundedCornerShape(24.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (textInput.isNotBlank()) {
                            messages.add(textInput to true)
                            // Response logic
                            val reply = when {
                                textInput.contains("hello", true) -> "Hi Subhasish!"
                                textInput.contains("love", true) -> "I love you too, Subhasish!"
                                else -> "Tell me more, Subhasish."
                            }
                            messages.add(reply to false)
                            textInput = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF075E54)),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text("Send")
                }
            }
        }
    }
}

@Composable
fun ChatBubble(message: String, isMe: Boolean) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        contentAlignment = if (isMe) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Surface(
            color = if (isMe) Color(0xFFDCF8C6) else Color.White,
            shape = RoundedCornerShape(12.dp),
            shadowElevation = 1.dp
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(8.dp),
                fontSize = 16.sp
            )
        }
    }
}
