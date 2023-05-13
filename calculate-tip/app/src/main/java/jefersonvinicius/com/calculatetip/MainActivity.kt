package jefersonvinicius.com.calculatetip

import android.icu.number.NumberFormatter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jefersonvinicius.com.calculatetip.ui.theme.CalculateTipTheme
import kotlinx.coroutines.delay
import java.text.NumberFormat
import androidx.annotation.DrawableRes
import androidx.annotation.VisibleForTesting
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import kotlin.math.ceil

fun String.isNumeric(): Boolean = this.trim() != "" && this.toDoubleOrNull() == null;

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculateTipTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculateTipApp()
                }
            }
        }
    }
}

@VisibleForTesting
internal fun calculateTip(cost: Double?, percentage: Double?, roundUp: Boolean): Double {
    var tip = (cost ?: 0.0) * ((percentage ?: 0.0) / 100)
    if (roundUp) tip = ceil(tip)
    return tip
}

@Composable
fun RoundUpTheTipRow(roundUp: Boolean, onRoundUpChanged: (Boolean) -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(stringResource(R.string.round_up_tip))
        Switch(checked = roundUp, onCheckedChange = onRoundUpChanged)
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CalculateTipApp() {
    var serviceCost by remember { mutableStateOf("") }
    var tipPercentage by remember { mutableStateOf("") }
    var roundUp by remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val numberFormatter = NumberFormat.getCurrencyInstance()

    val tipAmount = calculateTip(serviceCost.toDoubleOrNull(), tipPercentage.toDoubleOrNull(), roundUp)
    val isServiceCostInvalidNumber = serviceCost.isNumeric()
    val isTipPercentInvalidNumber = tipPercentage.isNumeric()

    LaunchedEffect(true) {
        focusRequester.requestFocus()
        delay(100)
        keyboardController?.show()
    }

    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        Text(stringResource(R.string.title),
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp))
        TextField(
            value = serviceCost,
            onValueChange = { serviceCost = it },
            label = { Text(stringResource(R.string.bill_amount_placeholder)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Next),
            isError = isServiceCostInvalidNumber,
            supportingText = { if (isServiceCostInvalidNumber) Text(stringResource(R.string.service_cost_error_message)) },
            leadingIcon = { Icon(painter = painterResource(R.drawable.money), null) },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester))
        TextField(
            value = tipPercentage,
            onValueChange = { tipPercentage = it },
            label = { Text(stringResource(R.string.tip_percentage_placeholder)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
            isError = isTipPercentInvalidNumber,
            supportingText = { if (isTipPercentInvalidNumber) Text(stringResource(R.string.service_cost_error_message)) },
            leadingIcon = { Icon(painter = painterResource(R.drawable.percent), null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp))
        RoundUpTheTipRow(
            roundUp = roundUp,
            onRoundUpChanged = { roundUp = it }
        )
        Text(stringResource(R.string.tip_amount, numberFormatter.format(tipAmount)),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    CalculateTipTheme {
        CalculateTipApp()
    }
}