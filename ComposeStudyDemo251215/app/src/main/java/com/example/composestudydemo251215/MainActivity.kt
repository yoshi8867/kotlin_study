package com.example.composestudydemo251215

import android.R.attr.name
import android.R.attr.y
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring.DampingRatioHighBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composestudydemo251215.ui.theme.ComposeStudyDemo251215Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

enum class BoxColor {
    Red, Magenta
}

enum class BoxPosition {
    Start, End
}

@Composable
fun MotionDemo() {
    var boxState by remember{ mutableStateOf(BoxPosition.Start) }
    var boxSideLength = 70.dp
    var screenWidth = (LocalConfiguration.current.screenWidthDp.dp)

    val boxPositon: Dp by animateDpAsState(
        targetValue = when (boxState) {
            BoxPosition.Start -> 0.dp
            BoxPosition.End -> screenWidth - boxSideLength
        },
        animationSpec = spring(
            dampingRatio = DampingRatioHighBouncy,
            stiffness = StiffnessVeryLow,
            ),
    )


    Column(Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .offset(x = boxPositon, y = 20.dp)
                .size(boxSideLength)
                .background(Color.Red)
        )

        Button(
            onClick = {
                boxState = when (boxState) {
                    BoxPosition.Start -> BoxPosition.End
                    BoxPosition.End -> BoxPosition.Start
                }
            }
        ) {
            Text("CLICK")
        }
    }

}

@Composable
fun ColorChangeDemo() {
    var colorState by remember{ mutableStateOf(BoxColor.Red) }

    val animatedColor: Color by animateColorAsState(
        targetValue = when (colorState) {
            BoxColor.Red -> Color.Red
            BoxColor.Magenta -> Color.Magenta
        },
    )

    Column() {
        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(20.dp)
                .background(animatedColor),
        )

        Button(
            onClick = {
                colorState = when (colorState) {
                    BoxColor.Red -> BoxColor.Magenta
                    BoxColor.Magenta -> BoxColor.Red
                }
            }
        ) {
            Text("CLICK")
        }
    }
}

@Composable
fun RotateDemo() {
    var rotated by remember { mutableStateOf(false) }

    val angle by animateFloatAsState(
        targetValue = if (rotated) 360f else 0f,
        animationSpec = tween(durationMillis = 2500)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
        ) {
        Image(
            painter = painterResource(R.drawable.propeller),
            contentDescription = "fan",
            modifier = Modifier
                .rotate(angle)
                .padding(10.dp)
                .size(300.dp)
        )

        Button(
            onClick = {
                rotated = !rotated
            },
            modifier = Modifier.padding(10.dp),
        ) {
            Text("Click")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeStudyDemo251215Theme {
        MotionDemo()
    }
}