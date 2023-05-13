package com.example.shortcutsbacana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shortcutsbacana.ui.theme.ShortcutsBacanaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShortcutsBacanaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // BirthdayGreetingWithImage(
                        //message = getString(R.string.happy_birthday_isa),
                        //from = getString(R.string.from_jeferson)
                    //)
                    // Article()
                    // TaskManager()
                    ComposeQuadrant()
                }
            }
        }
    }
}

@Composable
fun BirthdayGreetingWithText(message: String, from: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message, fontSize = 36.sp, modifier = Modifier.padding(top = 16.dp))
        Text(text = from, fontSize = 24.sp, modifier = Modifier
            .padding(top = 16.dp)
            .align(Alignment.End))
    }
}

@Composable
fun BirthdayGreetingWithImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.androidparty)
    Box {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        BirthdayGreetingWithText(message = message, from = from)
    }
}

@Composable
fun Article() {
    val image = painterResource(id = R.drawable.bg_compose_background)
    Column {
        Image (
            painter = image,
            contentDescription = null
        )
        Text(text = stringResource(R.string.title), fontSize = 24.sp, modifier = Modifier.padding(16.dp))
        Text(text = stringResource(R.string.presentation), textAlign = TextAlign.Justify, modifier = Modifier.padding(16.dp))
        Text(text = stringResource(R.string.tutorial),  textAlign = TextAlign.Justify, modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun TaskManager() {
    val image = painterResource(R.drawable.ic_task_completed)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = image, contentDescription = null)
        Text(text = stringResource(R.string.completed_tasks), fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 24.dp, bottom = 8.dp))
        Text(text = stringResource(R.string.nice_work), fontSize = 16.sp)
    }
}

@Composable
fun Quadrant(title: String, explanation: String, backgroundColor: Color, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(backgroundColor).fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))
        Text(text = explanation)
    }
}

@Composable
fun ComposeQuadrant() {
    Column (Modifier.fillMaxWidth()) {
        Row (Modifier.weight(1f)) {
            Quadrant(
                title = stringResource(R.string.text_title),
                explanation = stringResource(R.string.text_explanation),
                backgroundColor = Color.Green,
                modifier = Modifier.weight(1f)
            )
            Quadrant(
                title = stringResource(R.string.image_title),
                explanation = stringResource(R.string.image_explanation),
                backgroundColor = Color.Yellow,
                modifier = Modifier.weight(1f)
            )
        }
        Row (Modifier.weight(1f)) {
            Quadrant(
                title = stringResource(R.string.row_title),
                explanation = stringResource(R.string.row_explanation),
                backgroundColor = Color.Cyan,
                modifier = Modifier.weight(1f)
            )
            Quadrant(
                title = stringResource(R.string.column_title),
                explanation = stringResource(R.string.column_explanation),
                backgroundColor = Color.LightGray,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ShortcutsBacanaTheme {
        // BirthdayGreetingWithText(message = "Happy Birthday Isa!", from = "- from Jeferson")
        // BirthdayGreetingWithImage(message = "Happy Birthday Isa!", from = "- from Jeferson")
        // Article()
        // TaskManager()
        ComposeQuadrant()
    }
}
