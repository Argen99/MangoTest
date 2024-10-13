package com.example.core_ui.composable

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    shape: Shape = ShapeDefaults.Medium,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    enabled: Boolean = true,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        disabledTextColor = Color.Gray,
        disabledLabelColor = Color.DarkGray
    ),
    singleLine: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {

    OutlinedTextField(
        modifier = modifier,
        shape = shape,
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            label?.invoke()
        },
        isError = isError,
        enabled = enabled,
        colors = colors,
        singleLine = singleLine,
        placeholder = {
            placeholder?.invoke()
        },
        leadingIcon = {
            leadingIcon?.invoke()
        },
        trailingIcon = {
            trailingIcon?.invoke()
        }
    )
}