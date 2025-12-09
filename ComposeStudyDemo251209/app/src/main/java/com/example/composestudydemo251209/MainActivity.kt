package com.example.composestudydemo251209

import android.R.attr.name
import android.R.attr.onClick
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composestudydemo251209.ui.theme.ComposeStudyDemo251209Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeStudyDemo251209Theme {

            }
        }
    }
}

@Composable
fun SlotDemo(middleContent: @Composable () -> Unit) {
    Column {
        Text("111")
        middleContent()
        Text("333")
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    val txt = remember { mutableStateOf("GOOD") }
    ComposeStudyDemo251209Theme {
        SlotDemo(
            middleContent =  { // <- 이렇게 람다가 들어가야 함
                Button(
                    onClick = { txt.value = "HELLO" }
                ) {
                    Text(txt.value)
                }
            }
        )
    }
}