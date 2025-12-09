package com.example.democompositionallocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.democompositionallocal.ui.theme.DemoCompositionalLocalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemoCompositionalLocalTheme {

            }
        }
    }
}

val LocalColor = staticCompositionLocalOf { Color(0xFFffdbcf) }

@Composable
fun Compo1() {
    var color = if (isSystemInDarkTheme()) {
        Color(0xFFa08d87)
    } else {
        Color(0xFFffdbcf)
    }

    Column {
        Compo2()
        CompositionLocalProvider(LocalColor provides color) {
            Compo3()
        }
    }
}

@Composable
fun Compo2() {
    Compo4()
}

@Composable
fun Compo3() {
    Text("Compo3")
    Compo5()
}

@Composable
fun Compo4() {
    Compo6()
}

@Composable
fun Compo5() {
    Text("Compo5")
    Compo7()
    Compo8()
}

@Composable
fun Compo6() {
    Text("Compo6")
}

@Composable
fun Compo7() {
}

@Composable
fun Compo8() {
    Text("Compo8",
        modifier = Modifier.background(LocalColor.current))
}

@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES,
//    showSystemUi = true,
    )
@Composable
fun DarkPreview() {
    Compo1()
}

@Preview(
    showBackground = true,
//    showSystemUi = true,
)
@Composable
fun LightPreview() {
    Compo1()
}