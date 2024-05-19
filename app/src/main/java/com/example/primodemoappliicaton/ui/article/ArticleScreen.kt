package com.example.primodemoappliicaton.ui.article

import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.primodemoappliicaton.ui.common.AppScaffold

@Composable
fun ArticleScreen(
    navController: NavHostController,
    link: String,
) {
    ArticleScreenContent(
        link = link
    )
}

@Composable
internal fun ArticleScreenContent(
    link: String = ""
) {
    val context = LocalContext.current

    AppScaffold() {
        Text(text = "Article Screen $link")
        AndroidView(
            factory = {
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    settings.apply {
                        cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                        domStorageEnabled = true
                        javaScriptEnabled = true
                        setRenderPriority(WebSettings.RenderPriority.HIGH)
                    }
                    loadUrl(link)
                }
            },
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        )
    }
}