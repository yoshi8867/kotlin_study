package com.example.composestudydemo251209

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composestudydemo251209.ui.theme.ComposeStudyDemo251209Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
        }
    }
}

@Composable
fun MainScreen() {
    var linearSelected by remember { mutableStateOf(true) }
    var imageSelected by remember { mutableStateOf(true) }

    val onLinearClick = { value : Boolean ->
        linearSelected = value
    }

    val onTitleClick = { value : Boolean ->
        imageSelected = value
    }

    ScreenContent(
        linearSelected = linearSelected,
        imageSelected = imageSelected,
        onTitleClick = onTitleClick,
        onLinearClick = onLinearClick,
        titleContent = {
            if (imageSelected) {
                TitleImage(drawing = R.drawable.outline_cloud_download_24)
            } else {
                Text("Downloading",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(30.dp),
                    )
            }
        },
        progressContent = {
            if (linearSelected) {
                LinearProgressIndicator(Modifier.height(40.dp))
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.size(200.dp),
                    strokeWidth = 18.dp
                    )
            }
        },
    )
}

@Composable
fun ScreenContent(
    linearSelected: Boolean,
    imageSelected: Boolean,
    onTitleClick: (Boolean) -> Unit,
    onLinearClick: (Boolean) -> Unit,
    titleContent: @Composable () -> Unit,
    progressContent: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        titleContent()
        progressContent()
        Checkboxes(
            imageSelected = imageSelected,
            linearSelected = linearSelected,
            onTitleClick = onTitleClick,
            onLinearClick = onLinearClick,
        )
    }
}

@Composable
fun Checkboxes(
    imageSelected: Boolean,
    linearSelected: Boolean,
    onTitleClick: (Boolean) -> Unit,
    onLinearClick: (Boolean) -> Unit,
) {
    Row(
        Modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = imageSelected,
            onCheckedChange = onTitleClick,
        )
        Text("Image Title")
        Spacer(modifier = Modifier.width(20.dp))
        Checkbox(
            checked = linearSelected,
            onCheckedChange = onLinearClick,
        )
        Text("Linear Progress")
    }
}

@Composable
fun TitleImage(drawing: Int) {
    Image(
        painter = painterResource(drawing),
        contentDescription = "title inage",
    )
}

@Composable
fun ModiStudyScreen() {
    val modifier = Modifier
        .border(width = 2.dp, color = Color.Black)
        .padding(all = 10.dp)
    Text(
        text = "HELLO WORLD",
        modifier = modifier,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun CustomImage(image: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(image),
        contentDescription = "yoshi!",
        modifier = modifier,
    )
}

@Composable
fun RowColDemoScreen() {
    Row (
        modifier = Modifier
            .border(width = 2.dp, color = Color.Blue)
            .size(450.dp, 150.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextCell("1", Modifier.weight(weight = 0.2f, fill = true))
        TextCell("2", Modifier.weight(weight = 0.3f, fill = true))
        TextCell("3", Modifier.weight(weight = 0.5f, fill = true))
    }

    Column (
        modifier = Modifier
            .border(width = 2.dp, color = Color.Black)
            .size(150.dp, 550.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End,
    ) {
        TextCell("1", Modifier.align(Alignment.CenterHorizontally))
        TextCell("2", Modifier.align(Alignment.Start))
        TextCell("3", Modifier.align(Alignment.End))
    }
}

@Composable
fun TextCell(text: String, modifier: Modifier = Modifier) {
    val cellModifier = Modifier
        .padding(all = 4.dp)
        .size(100.dp, 100.dp)
        .border(width = 2.dp, color = Color.Magenta)

    Text(text = text,
        cellModifier.then(modifier),
        fontSize = 70.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun RowColDemoPreview() {
    RowColDemoScreen()
}

//@Preview(
//    showBackground = true,
//    showSystemUi = true,
//)
@Composable
fun Preview() {
    val txt = remember { mutableStateOf("GOOD") }
    ComposeStudyDemo251209Theme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            ModiStudyScreen()
            Spacer(modifier = Modifier.height(10.dp))
            CustomImage(
                image = R.drawable.yoshi,
                Modifier
                    .border(width = 2.dp, color = Color.Black)
                    .padding(all = 10.dp)
                    .clip(shape = RoundedCornerShape(60.dp))
            )
        }
    }
}