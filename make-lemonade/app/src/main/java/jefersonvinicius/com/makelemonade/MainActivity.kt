package jefersonvinicius.com.makelemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jefersonvinicius.com.makelemonade.ui.theme.MakeLemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MakeLemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MakeLemonadeApp()
                }
            }
        }
    }
}

open class Step(val textResourceId: Int, val drawableResourceId: Int) {
    companion object {
        val treeStep = Step(R.string.tree_screen_description, R.drawable.lemon_tree)
        val lemonStep = MultiClicksStep(R.string.lemon_screen_description, R.drawable.lemon_squeeze)
        val drinkStep = Step(R.string.glass_of_lemonade_screen_description, R.drawable.lemon_drink)
        val emptyGlassStep = Step(R.string.empty_glass_screen_description, R.drawable.lemon_restart)
    }

    open fun handleClick(): Step? {
        return getNextStep()
    }
    
    protected fun getNextStep():Step {
        if (this == treeStep) return lemonStep
        if (this == lemonStep) return drinkStep
        if (this == drinkStep) return emptyGlassStep
        return treeStep
    }
}

class MultiClicksStep(textResourceId: Int, drawableResourceId: Int): Step(textResourceId, drawableResourceId) {
    private var numberOfClicks = 0
    private var expectedNumberOfClicks = 0

    override fun handleClick(): Step? {
        if (expectedNumberOfClicks == 0)
            expectedNumberOfClicks = (2..4).random()

        numberOfClicks++
        if (numberOfClicks == expectedNumberOfClicks) {
            numberOfClicks = 0
            expectedNumberOfClicks = 0
            return getNextStep()
        }
        return null
    }
}

@Composable
fun MakeLemonadeApp() {
    var step by remember { mutableStateOf(Step.treeStep) }

    fun handleImageClick() {
        val nextStep = step.handleClick()
        if (nextStep != null) step = nextStep
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val image = painterResource(step.drawableResourceId)
        Text(
            text = stringResource(step.textResourceId),
            modifier = Modifier.padding(bottom = 16.dp))
        Image(
            painter = image,
            contentDescription = stringResource(R.string.lemon_tree),
            modifier = Modifier.clickable { handleImageClick() })
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MakeLemonadeTheme {
        MakeLemonadeApp()
    }
}