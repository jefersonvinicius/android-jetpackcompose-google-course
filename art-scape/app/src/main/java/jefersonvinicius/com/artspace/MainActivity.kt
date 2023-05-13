package jefersonvinicius.com.artspace

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jefersonvinicius.com.artspace.ui.theme.ArtSpaceTheme

data class Art(
    val artist: String,
    val title: String,
    val description: String,
    val year: Int,
    val image: Int)

fun createArts(context: Context): List<Art> {
    return listOf(
        Art(
            artist = context.getString(R.string.starry_night_artist),
            title = context.getString(R.string.starry_night_title),
            description = context.getString(R.string.starry_night_description),
            year = 1889,
            image = R.drawable.starry_night
        ),
        Art(
            artist = context.getString(R.string.lat_supper_artist),
            title = context.getString(R.string.last_supper_title),
            description = context.getString(R.string.last_supper_description),
            year = 1498,
            image = R.drawable.last_supper
        )
    )
}

class MainActivity : ComponentActivity() {
    private lateinit var arts: List<Art>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arts = createArts(this)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp(arts)
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArtSpaceApp(arts: List<Art>) {
    val pagerState = rememberPagerState()
    HorizontalPager(pageCount = arts.size, state = pagerState) {
        ArtCard(arts[it])
    }
    Row(
        Modifier
            .height(50.dp)
            .fillMaxWidth()
            .align(Alignment.BottomCenter),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(arts.size) {
            val color = if (pagerState.currentPage == it) Color.DarkGray else Color.LightGray
            Box(modifier = Modifier
                .padding(2.dp)
                .clip(CircleShape)
                .background(color)
                .size(16.dp))
        }
    }
}

@Composable
fun ArtCard(art: Art) {
    Column(modifier = Modifier.fillMaxSize()) {
        Image(painterResource(art.image), contentDescription = art.title)
        Text(art.title)
        Text(art.description)
        Row {
            Text(art.artist)
            Text(stringResource(R.string.art_year, art.year))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp(arts = createArts(LocalContext.current))
    }
}