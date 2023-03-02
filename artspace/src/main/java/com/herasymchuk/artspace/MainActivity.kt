package com.herasymchuk.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.herasymchuk.artspace.data.Datasource
import com.herasymchuk.artspace.ui.theme.BasicsInComposeTheme

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
                    ArtSpaceScreen()
                }
            }
        }
    }
}

/**
 * This composable function displays the art space screen
 */
@Composable
fun ArtSpaceScreen() {
    // Load all the photos from the datasource
    val photos by remember { mutableStateOf(Datasource().allPhotos) }

    // Keep track of the current photo being displayed
    var currentPhoto by remember { mutableStateOf(photos.first()) }

    // Create a vertical column
    Column(modifier = Modifier.fillMaxSize()) {

        // Display the current photo as an artwork grid
        ArtworkWall(
            imageId = currentPhoto.imageId,
            title = currentPhoto.title,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentSize()
                .border(BorderStroke(2.dp, Color.Gray))
                .padding(16.dp)
        )

        // Display the current photo's title and description
        ArtWorkDescriptor(
            title = currentPhoto.title,
            description = currentPhoto.description,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
                .border(BorderStroke(1.dp, Color.Gray))
                .wrapContentWidth()
                .padding(16.dp)
        )

        // Display the previous and next buttons
        DisplayController(
            onPreviousClick = {
                // When the user clicks the "Previous button", move to the previous photo
                currentPhoto = if (currentPhoto == photos.first()) {
                    photos.last()
                } else {
                    photos[photos.indexOf(currentPhoto) - 1]
                }
            },
            onNextClick = {
                // When the user clicks the "Next" button, move to the next photo
                currentPhoto = if (currentPhoto == photos.last()) {
                    photos.first()
                } else {
                    photos[photos.indexOf(currentPhoto) + 1]
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, start = 32.dp, end = 32.dp)
        )
    }
}

/**
 * This composable function displays the artwork wall
 */
@Composable
fun ArtworkWall(
    @DrawableRes imageId: Int,
    title: String,
    modifier: Modifier = Modifier,
) {

    // Display the image as an Image composable
    Image(
        painter = painterResource(id = imageId),
        contentDescription = title,
        modifier = modifier
    )
}

/**
 * This composable function displays the current photo's title and description
 */
@Composable
fun ArtWorkDescriptor(title: String, description: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(text = description)
    }
}

/**
 * This composable function displays the previous and the next buttons
 */
@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val buttonWidth = 150.dp
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Button(onClick = onPreviousClick, modifier = Modifier.width(buttonWidth)) {
            Text(text = "Previous")
        }
        Button(onClick = onNextClick, modifier = Modifier.width(buttonWidth)) {
            Text(text = "Next")
        }
    }
}