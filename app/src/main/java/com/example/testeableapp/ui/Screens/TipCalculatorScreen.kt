package com.example.testeableapp.ui.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun TipCalculatorScreen() {
    var billAmount by remember { mutableStateOf("") }
    var tipPercentage by remember { mutableStateOf(15) }
    var roundUp by remember { mutableStateOf(false) }
    var numberOfPeople by remember { mutableStateOf(1) }

    val bill = billAmount.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(bill, tipPercentage, roundUp)
    val totalPerPerson = if (numberOfPeople > 0) (bill + tip) / numberOfPeople else 0.0

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = billAmount,
            onValueChange = { billAmount = it },
            label = { Text("Monto") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("bill_input")
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = roundUp,
                onCheckedChange = { roundUp = it },
                modifier = Modifier.testTag("round_checkbox")
            )
            Text("Redondear propina")
        }

        Text(
            text = "Propina: \$${tip}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.testTag("tip_display")
        )

        Text("Porcentaje de propina: $tipPercentage%")
        Slider(
            value = tipPercentage.toFloat(),
            onValueChange = { tipPercentage = it.toInt() },
            valueRange = 0f..100f,
            modifier = Modifier.testTag("tip_slider")
        )

        OutlinedTextField(
            value = numberOfPeople.toString(),
            onValueChange = {
                numberOfPeople = it.toIntOrNull() ?: 1
            },
            label = { Text("Número de personas") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("people_input")
        )

        Text(
            text = "Total por persona: \$${"%.2f".format(totalPerPerson)}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.testTag("total_display")
        )
    }
}


fun calculateTip(amount: Double, tipPercent: Int, roundUp: Boolean): Double {
    var tip = amount * tipPercent / 100
    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }
    return tip
}