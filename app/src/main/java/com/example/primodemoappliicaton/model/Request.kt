package com.example.primodemoappliicaton.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.primodemoappliicaton.R

class DialogRequest(
    val title: String? = null,
    val content: @Composable (() -> Unit)? = null,
    val positive: Int = R.string.common_confirm,
    val negative: Int? = null,
    val hideButton: Boolean = false,
    val cancelable: Boolean = true,
    val positiveCallback: () -> Unit = {},
    val negativeCallback: () -> Unit = {}
)

@Composable
fun rememberDialogRequest(init: (() -> DialogRequest?)? = null): MutableState<DialogRequest?> {
    return remember {
        mutableStateOf(init?.invoke())
    }
}