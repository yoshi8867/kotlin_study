package com.example.composestudydemo20252016

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composestudydemo20252016.ui.theme.ComposeStudyDemo20252016Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeStudyDemo20252016Theme {
                ScreenSetup()
            }
        }
    }
}

@Composable
fun ScreenSetup(viewModel: DemoViewModel = DemoViewModel()) {
    MainScreen(
        isFarenheit = viewModel.isFarenheit,
        result = viewModel.result,
        convertTemp = { viewModel.convertTemp(it) },
        switchChange = { viewModel.switchChange() },
    )
}

@Composable
fun MainScreen(
    isFarenheit: Boolean,
    result: String,
    convertTemp: (String) -> Unit,
    switchChange: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        var textState by remember { mutableStateOf("") }
        val onTextChange = { text: String ->
            textState = text
        }
        Text("온도 변환스~",
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.headlineMedium,
        )
        InputRow(
            isFarenheit = isFarenheit,
            textState = textState,
            switchChange = switchChange,
            onTextChange = onTextChange,
        )
        Text(result,
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.headlineMedium,
        )
        Button(
            onClick = { convertTemp(textState) }
        ) {
            Text("convert")
        }
    }
}

@Composable
fun InputRow(
    isFarenheit: Boolean,
    textState: String,
    switchChange: () -> Unit,
    onTextChange: (String) -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Switch(
            checked = isFarenheit,
            onCheckedChange = { switchChange() }
        )
        OutlinedTextField(
            value = textState,
            onValueChange = { onTextChange(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            label = { Text("Enter temp") },
            modifier = Modifier.padding(10.dp),
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                ),
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.outline_ac_unit_24),
                    contentDescription = "frost",
                    modifier = Modifier.size(40.dp),
                )
            }
        )

        Crossfade(
            targetState = isFarenheit,
            animationSpec = tween(3000),
        ) { visible ->
            when(visible) {
                true -> Text("\u2109", style = MaterialTheme.typography.headlineMedium)
                false -> Text("\u2103", style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview(viewModel: DemoViewModel = DemoViewModel()) {
    ComposeStudyDemo20252016Theme {
        MainScreen(
            isFarenheit = viewModel.isFarenheit,
            result = viewModel.result,
            convertTemp = { viewModel.convertTemp(it) },
            switchChange = { viewModel.switchChange() },
        )
    }
}