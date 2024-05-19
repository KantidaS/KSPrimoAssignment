package com.example.primodemoappliicaton.ui.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.primodemoappliicaton.ui.theme.PRIMODemoAppliicatonTheme

@Composable
fun BaseAlertDialog(
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues(16.dp),
    negativeCallback: () -> Unit = { },
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier,
    ) {
        Box(
            modifier = contentModifier
        ) {
            IconButton(
                onClick = negativeCallback,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(padding)
                    .fillMaxWidth()
            ) {
                content()
            }
        }
    }
}

@Composable
fun AppDialog(
    title: String? = null,
    content: @Composable (() -> Unit)? = null,
    negativeCallback: () -> Unit = { }
) {
    BaseAlertDialog(
        modifier = Modifier.fillMaxWidth(),
        padding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
        contentModifier = Modifier.fillMaxWidth(),
        negativeCallback = negativeCallback,
    ) {
        if (title != null) {
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                fontSize = 18.sp,
                text = title,
                textAlign = TextAlign.Center,
            )
        }
        if (content != null) {
            Spacer(modifier = Modifier.size(8.dp))
            content()
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
internal fun AppDialogPreview() {
    PRIMODemoAppliicatonTheme {
        AppDialog(
            title = "Title",
            content = {
                Text(text = "Dialog Content")
            }
        )
    }
}
