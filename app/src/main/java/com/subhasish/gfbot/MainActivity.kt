package com.subhasish.gfbot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var input by remember { mutableStateOf("") }
            val messages = remember { mutableStateListOf("Hi Subhasish! ❤️" to false) }

            Column(Modifier.fillMaxSize().background(Color(0xFFECE5DD))) {
                // Header
                Box(Modifier.fillMaxWidth().background(Color(0xFF075E54)).padding(16.dp)) {
                    Text("Girlfriend", color = Color.White)
                }

                // Chat Area
                LazyColumn(Modifier.weight(1f).padding(8.dp)) {
                    items(messages) { (text, isMe) ->
                        Box(Modifier.fillMaxWidth(), contentAlignment = if(isMe) Alignment.CenterEnd else Alignment.CenterStart) {
                            Surface(
                                color = if(isMe) Color(0xFFDCF8C6) else Color.White,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.padding(4.dp)
                            ) {
                                Text(text, Modifier.padding(8.dp))
                            }
                        }
                    }
                }

                // Input Bar
                Row(Modifier.padding(8.dp).fillMaxWidth()) {
                    TextField(value = input, onValueChange = { input = it }, Modifier.weight(1f), placeholder = { Text("Ask me anything...") })
                    Button(onClick = {
                        if (input.isNotBlank()) {
                            messages.add(input to true)
                            messages.add("I am always here for you, Subhasish." to false)
                            input = ""
                        }
                    }, Modifier.padding(start = 4.dp)) { Text("Send") }
                }
            }
        }
    }
}
