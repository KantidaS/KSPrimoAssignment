package com.example.primodemoappliicaton.ui.common

import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.example.primodemoappliicaton.ui.theme.ColorSecondaryContainer
import com.example.primodemoappliicaton.ui.theme.PRIMODemoAppliicatonTheme
import com.example.primodemoappliicaton.utils.CoilImageGetter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    content: String = "",
    onClick: (() -> Unit)? = null,
) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    Card(
        onClick = { onClick?.invoke() },
        colors = CardDefaults.cardColors(
            containerColor = ColorSecondaryContainer
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
            .onSizeChanged { size = it }
    ) {
        AndroidView(
            modifier = modifier.padding(8.dp),
            factory = { context -> TextView(context) },
            update = {
                it.text = HtmlCompat.fromHtml(
                    content,
                    HtmlCompat.FROM_HTML_MODE_COMPACT,
                    CoilImageGetter(
                        fixedImageWidth = size.width,
                        textView = it
                    ),
                    null
                )
            }
        )
    }
}

@Preview
@Composable
internal fun ArticleCardPreview() {
    PRIMODemoAppliicatonTheme {
        ArticleCard(
            content = "Title"
        )
    }
}