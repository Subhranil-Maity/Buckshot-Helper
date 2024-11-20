package com.subhranil.buckhelper.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun GoToMatchActionButton(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        icon = { Icon(Icons.Filled.PlayArrow, "Play") },
        text = { Text(text = "Play") },
    )
}
@Composable
fun BurnerActionButton(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        icon = { Icon(Icons.Filled.Call, "add burner data") },
        text = { Text(text = "Burner Info") },
    )
}

@Composable
fun AddBurnerActionButton(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        text = { Text(text = "Add") },
        icon = { Icon(Icons.Filled.Add, "Add Burner Info") }
    )
}