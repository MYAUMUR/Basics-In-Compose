package com.herasymchuk.lemonade

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.herasymchuk.lemonade.ui.theme.BasicsInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsInComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp(this)
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(context: Context) {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    when (currentStep) {
        1 -> LemonadeTextAndImage(
            "Tap the lemon tree to select a lemon",
            painterResource(id = R.drawable.lemon_tree),
            contentDescription = "Lemon tree",
            onImageClick = {
                currentStep = 2
                squeezeCount = (2..4).random()
            }
        )

        2 -> LemonadeTextAndImage(
            text = "Keep tapping the lemon to squeeze it",
            image = painterResource(id = R.drawable.lemon_squeeze),
            contentDescription = "Lemon",
            onImageClick = {
                squeezeCount--
                if (squeezeCount == 0) currentStep = 3
            }
        )

        3 -> LemonadeTextAndImage(
            text = "Tap the lemonade to drink it",
            image = painterResource(id = R.drawable.lemon_drink),
            contentDescription = "Glass of lemonade",
            onImageClick = {
                currentStep = 4
            }
        )

        4 -> LemonadeTextAndImage(
            text = "Tap the empty glass to start again",
            image = painterResource(id = R.drawable.lemon_restart),
            contentDescription = "Empty glass",
            onImageClick = {
                currentStep = 1
            }
        )

    }
}

@Composable
fun LemonadeTextAndImage(
    text: String,
    image: Painter,
    contentDescription: String,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = text, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = image,
            contentDescription = contentDescription,
            Modifier
                .wrapContentSize()
                .clickable(onClick = onImageClick)
                .border(BorderStroke(2.dp, Color(105, 206, 216)))
                .padding(16.dp)
        )
    }
}