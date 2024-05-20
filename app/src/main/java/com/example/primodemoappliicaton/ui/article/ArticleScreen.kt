package com.example.primodemoappliicaton.ui.article

import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.primodemoappliicaton.R
import com.example.primodemoappliicaton.model.DialogRequest
import com.example.primodemoappliicaton.model.FeedItem
import com.example.primodemoappliicaton.model.rememberDialogRequest
import com.example.primodemoappliicaton.ui.common.AppScaffold
import com.example.primodemoappliicaton.viewmodel.ArticleViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticleScreen(
    navController: NavHostController,
    feedId: Int? = null,
    viewModel: ArticleViewModel = koinViewModel(),
) {

    val context = LocalContext.current
    var alertRequest by rememberDialogRequest()

    LaunchedEffect(key1 = Unit) {
        if(feedId == null) {
            alertRequest = DialogRequest(
                title = context.getString(R.string.common_error),
                content = context.getString(R.string.common_an_error_has_occurred),
                cancelable = false,
                negativeCallback = {
                    navController.popBackStack()
                }
            )
        } else {
            viewModel.getFeedItemById(feedId)
        }
    }
    ArticleScreenContent(
        alertRequest = alertRequest,
    )
}

@Composable
internal fun ArticleScreenContent(
    alertRequest: DialogRequest? = null,
    isLoading: Boolean = false,
    feedItem: FeedItem? = null,
) {
    val context = LocalContext.current

    AppScaffold(
        isLoading = isLoading,
        dialogRequest = alertRequest,
    ) {
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
                    loadUrl(feedItem?.link ?: "")
                }
            },
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        )
    }
}