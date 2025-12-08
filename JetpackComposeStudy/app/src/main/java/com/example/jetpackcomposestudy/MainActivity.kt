package com.example.jetpackcomposestudy

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "main",
            ) {
                composable("main") {
                    MainScreen(navController)
                }
                composable("first") {
                    FirstScreen(navController)
                }
                composable("second/{value}") {backStackEntry ->
                    SecondScreen(
                        navController = navController,
                        text = backStackEntry.arguments?.getString("value") ?: "",
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    val (value, setValue) = rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("MAIN")
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                navController.navigate("first")
            }
        ) {
            Text("first")
        }
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = value,
            onValueChange = setValue,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                navController.navigate("second/$value")
            }
        ) {
            Text("second")
        }
    }
}

@Composable
fun FirstScreen(navController: NavController) {
    val (value, setValue) = rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("FIRST")
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                navController.navigateUp()
            }
        ) {
            Text("back")
        }
    }

}

@Composable
fun SecondScreen(navController: NavController, text: String) {
    val (value, setValue) = rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("SECOND $text")
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                navController.navigateUp()
            }
        ) {
            Text("back")
        }
    }

}

@Composable
fun StudyTextField() {
    // val text by remember { mutableStateOf("") } 와 같이 해도 됨
    val (text, setValue) = remember { // 구조분해
        mutableStateOf("")
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextField(
                value = text,
                onValueChange = setValue,
            )
            Button(
                onClick = {
                    keyboardController?.hide()
                    scope.launch {
                        snackbarHostState.showSnackbar("hello $text")
                    }
                },
            ) {
                Text("snack bar")
            }
        }
    }
}

@Composable
fun StudyCard(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onTabFavorite: (Boolean) -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Box(
            modifier = Modifier
                .height(300.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_card_sample),
                contentDescription = "card image",
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd,
            ) {
                IconButton(onClick = {
                    onTabFavorite(!isFavorite)
                }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favorite",
                        tint = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
fun StudyLazyColumnComposable() {
    LazyColumn(
        modifier = Modifier
            .background(color = Color.Green)
            .fillMaxWidth(),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(50) { index ->
            Text("아이템 $index")
        }
    }
}

@Composable
fun StudyButtonClick() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = "",
            onValueChange = {},
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {}) {
            Text("Click!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudyLazyColumnPreview() {
}