package com.example.weather.presentation.weather.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopLeftRightButtons(onLeftClick: () -> Unit, onRightClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = onLeftClick) {
            Text(text = "Я тут", style = MaterialTheme.typography.titleMedium)
        }

        TextButton(onClick = onRightClick) {
            Text(text = "Добавить", style = MaterialTheme.typography.titleMedium)
        }
    }
}