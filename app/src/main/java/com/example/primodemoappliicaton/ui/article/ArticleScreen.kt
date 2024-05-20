package com.example.primodemoappliicaton.ui.article

import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.primodemoappliicaton.R
import com.example.primodemoappliicaton.model.DialogRequest
import com.example.primodemoappliicaton.model.FeedItem
import com.example.primodemoappliicaton.model.rememberDialogRequest
import com.example.primodemoappliicaton.ui.common.AppScaffold
import com.example.primodemoappliicaton.ui.common.CustomWebViewClient
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
    val feedItem by viewModel.feedItem.observeAsState()

    var isLoading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        if(feedId == null) {
            alertRequest = DialogRequest(
                title = context.getString(R.string.common_error),
                content = context.getString(R.string.common_an_error_has_occurred),
                cancelable = false,
                negativeCallback = {
                    alertRequest = null
                    navController.popBackStack()
                }
            )
        } else {
            isLoading = true
            viewModel.getFeedItemById(feedId)
        }
    }
    ArticleScreenContent(
        alertRequest = alertRequest,
        isLoading = isLoading,
        feedItem = feedItem,
        onPageFinished = {
            isLoading = false
        }
    )
}

@Composable
internal fun ArticleScreenContent(
    alertRequest: DialogRequest? = null,
    isLoading: Boolean = false,
    feedItem: FeedItem? = null,
    onPageFinished: () -> Unit = {},
) {
    val context = LocalContext.current

    AppScaffold(
        isLoading = isLoading,
        dialogRequest = alertRequest,
    ) {
        AndroidView(
            factory = {
                WebView(context).apply {
                    webViewClient = CustomWebViewClient(
                        onPageFinishedCallback = {
                            onPageFinished.invoke()
                        }
                    )

                    feedItem?.link?.let { link ->
                        loadUrl(link)
                    }
                }
            },
            update = {
                feedItem?.link?.let { link ->
                    it.loadUrl(link)
                }
            },
        )
    }
}