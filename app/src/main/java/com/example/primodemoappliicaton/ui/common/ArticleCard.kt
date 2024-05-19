package com.example.primodemoappliicaton.ui.common

import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.primodemoappliicaton.ui.theme.ColorSecondaryContainer
import com.example.primodemoappliicaton.ui.theme.PRIMODemoAppliicatonTheme
import com.example.primodemoappliicaton.utils.addImageStyleToHtml

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    content: String = "",
    link: String = "",
    onClick: (() -> Unit)? = null,
) {
    val context = LocalContext.current
    Card(
        onClick = { onClick?.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            factory = {
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    settings.apply {
                        cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                        domStorageEnabled = true
                        javaScriptEnabled = true
                        setRenderPriority(WebSettings.RenderPriority.HIGH)
                    }
                    setBackgroundColor(ColorSecondaryContainer.toArgb())
                    loadDataWithBaseURL(
                        null,
                        addImageStyleToHtml(content),
                        "text/html",
                        "UTF-8",
                        null
                    )
                }
            },
            update = {
                it.loadDataWithBaseURL(
                    null,
                    addImageStyleToHtml(content),
                    "text/html",
                    "UTF-8",
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