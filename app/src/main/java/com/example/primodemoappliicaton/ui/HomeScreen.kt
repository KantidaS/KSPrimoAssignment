package com.example.primodemoappliicaton.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.primodemoappliicaton.R
import com.example.primodemoappliicaton.model.DialogRequest
import com.example.primodemoappliicaton.model.FeedItem
import com.example.primodemoappliicaton.model.Screen
import com.example.primodemoappliicaton.model.rememberDialogRequest
import com.example.primodemoappliicaton.ui.common.AppScaffold
import com.example.primodemoappliicaton.ui.common.ArticleCard
import com.example.primodemoappliicaton.viewmodel.HomeViewModel
import com.example.primodemoappliicaton.viewmodel.HomeViewModelEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = koinViewModel()
) {

    val context = LocalContext.current
    var alertRequest by rememberDialogRequest()
    val feedItems by viewModel.allFeedItems.observeAsState(emptyList())
    var isLoading by remember {
        mutableStateOf(false)
    }
    val event by viewModel.event.collectAsState()

    when(event) {
        is HomeViewModelEvent.Loading -> {
            isLoading = true
        }
        is HomeViewModelEvent.Error -> {
            isLoading = false
            alertRequest = DialogRequest(
                title = context.getString(R.string.common_error),
                content = context.getString(R.string.common_an_error_has_occurred),
                cancelable = true,
                negativeCallback = {
                    alertRequest = null
                }
            )
        }
        is HomeViewModelEvent.Success -> {
            isLoading = false
        }
        else -> {}
    }

    HomeScreenContent(
        alertRequest = alertRequest,
        isLoading = isLoading,
        itemList = feedItems,
        onClickArticle = {
            navController.navigate("${Screen.ARTICLE.name}/$it")
        }
    )
}

@Composable
internal fun HomeScreenContent(
    alertRequest: DialogRequest? = null,
    isLoading: Boolean = false,
    gridSpan: Int = 1,
    itemList: List<FeedItem> = emptyList(),
    onClickArticle: ((Int) -> Unit)? = null,
) {

    AppScaffold(
        dialogRequest = alertRequest,
        isLoading = isLoading,
    ) {
        LazyVerticalGrid(
            state = rememberLazyGridState(),
            columns = GridCells.Fixed(gridSpan),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            item(span = { GridItemSpan(gridSpan) }) {
                Text(text = stringResource(R.string.title_primo_application), fontSize = 24.sp)
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(itemList) { item ->
                ArticleCard(
                    content = item.content,
                    onClick = {
                        onClickArticle?.invoke(item.id)
                    }
                )
            }

        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreenContent()
}