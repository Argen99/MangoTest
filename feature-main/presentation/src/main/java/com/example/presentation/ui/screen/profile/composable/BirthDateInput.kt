package com.example.presentation.ui.screen.profile.composable

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun BirthDateInput(
    modifier: Modifier = Modifier,
    value: String,
    shape: Shape = ShapeDefaults.Medium,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    label: @Composable() (() -> Unit)? = null,
) {

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            if (it.length <= 8) {
                onValueChange(it)
            }
        },
        shape = shape,
        label = { label?.invoke() },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = DateVisualTransformation(),
        singleLine = singleLine,
    )
}

class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val input = text.text.filter { it.isDigit() }
        val out = buildString {
            input.forEachIndexed { index, c ->
                append(c)
                if (index == 3 && c != '-' || index == 5 && c != '-') append("-")
            }
        }

        val dateOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 3 -> offset
                    offset <= 5 -> offset + 1
                    offset <= 8 -> offset + 2
                    else -> 10
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 4 -> offset
                    offset <= 7 -> offset - 1
                    offset <= 10 -> offset - 2
                    else -> 8
                }
            }
        }
        return TransformedText(AnnotatedString(out), dateOffsetTranslator)
    }
}