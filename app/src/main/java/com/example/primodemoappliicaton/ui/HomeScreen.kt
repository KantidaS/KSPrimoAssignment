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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.primodemoappliicaton.model.FeedItem
import com.example.primodemoappliicaton.ui.common.AppScaffold
import com.example.primodemoappliicaton.ui.common.ArticleCard
import com.example.primodemoappliicaton.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {

    HomeScreenContent(
        isLoading = viewModel.isLoading.value,
        title = viewModel.title.value,
        itemList = viewModel.itemList,
    )
}

@Composable
fun HomeScreenContent(
    isLoading: Boolean = false,
    title: String = "",
    gridSpan: Int = 1,
    itemList: List<FeedItem> = emptyList(),
) {

    AppScaffold(
        isLoading = isLoading
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
                Text(text = title)
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(itemList) { item ->
                ArticleCard(
                    content = item.content,
                    link = item.link,
                    onClick = {

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