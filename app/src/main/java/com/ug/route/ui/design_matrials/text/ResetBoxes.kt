package com.ug.route.ui.design_matrials.text

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.unit.dp
import com.ug.route.utils.ResetCode

@SuppressLint("UnrememberedMutableState")
@Composable
fun ResetBoxes(
    modifier: Modifier = Modifier,
    isError: Boolean,
    resetCode: ResetCode,
    onChangeDigit: (Int, String) -> Unit,
) {
    val focusRequesters = remember {
        List(6) { FocusRequester() }
    }

    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        Arrangement.SpaceEvenly,
    ) {
        for (i in 0 until 6) {

            OneDigitTextField(
                modifier = Modifier.onKeyEvent {

                    if (it.key == Key.Backspace){
                        if (i > 0 && resetCode.getDigit(i).isEmpty()) {

                            onChangeDigit(i - 1, "")
                            focusRequesters[i - 1].requestFocus()
                        }
                        onChangeDigit(i, "")
                        true
                    } else false
                },
                value = resetCode.getDigit(i),
                isError = isError,
                onValueChange = { newValue ->

                    if (newValue.length <= 1 && newValue.all { it.isDigit() }){
                        onChangeDigit(i, newValue)
                        if (newValue.length == 1 && i < 5) {
                            focusRequesters[i + 1].requestFocus()
                        }
                    }
                },
                focusRequester = focusRequesters[i]
            )
        }
    }
}
