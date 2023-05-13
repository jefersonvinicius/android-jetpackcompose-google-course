package jefersonvinicius.com.businesscard

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jefersonvinicius.com.businesscard.ui.theme.BusinessCardTheme

val primaryColor = Color(0xFF3ddc84)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF073042)
                ) {
                    BusinessCard(name = "Jeferson Vinícius")
                }
            }
        }
    }
}

@Composable
fun InfoRow(icon: ImageVector, label: String, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.padding(horizontal = 32.dp, vertical = 5.dp)
    ) {
        Icon(icon, contentDescription = null, tint = primaryColor)
        Text(label, color = Color.White, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
        )
    }
}

@Composable
fun BusinessCard(name: String) {
    val image = painterResource(R.drawable.android_logo)
    Column {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = image, contentDescription = null)
            Text(name, color = Color.White, fontSize = 40.sp)
            Text(stringResource(R.string.extraordinaire), fontWeight = FontWeight.Bold, color = primaryColor)
        }
        Column (
            modifier = Modifier
                .padding(vertical = 30.dp)
                .weight(0.3f)
        ) {
            InfoRow(icon = Icons.Rounded.Phone, label = "+55 (35) 99764-8670")
            Spacer(Modifier.weight(1f))
            InfoRow(icon = Icons.Rounded.Share, label = "@jefersonvinicius")
            Spacer(Modifier.weight(1f))
            InfoRow(icon = Icons.Rounded.Email, label = "jeferson.viniciuscrc@gmail.com")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF073042)
        ) {
            BusinessCard(name = "Jeferson Vinícius")
        }
    }
}