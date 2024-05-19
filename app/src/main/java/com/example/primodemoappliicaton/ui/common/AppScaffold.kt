package com.example.primodemoappliicaton.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.primodemoappliicaton.model.DialogRequest
import com.example.primodemoappliicaton.ui.dialog.AppDialog
import com.example.primodemoappliicaton.ui.theme.ColorPrimary
import com.example.primodemoappliicaton.ui.theme.PRIMODemoAppliicatonTheme

@Composable
fun AppScaffold (
    modifier: Modifier = Modifier,
    title: String = "",
    isLoading: Boolean = false,
    scrollable: Boolean = false,
    onBackPress: (() -> Unit)? = null,
    dialogRequest: DialogRequest? = null,
    rightWidget: @Composable (() -> Unit) = {},
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            modifier = modifier,
            topBar = {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = ColorPrimary)
                        .height(58.dp)
                ) {
                    val (btnBack, tvTitle, contentWidget) = createRefs()

                    onBackPress?.let {
                        IconButton(
                            onClick = { it.invoke() },
                            modifier = Modifier.constrainAs(btnBack) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(24.dp),
                            )
                        }
                    }

                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .constrainAs(tvTitle) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            },
                    )

                    Box (
                        modifier = Modifier
                            .constrainAs(contentWidget) {
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                    ) {
                        rightWidget()
                    }

                }
            }
        ) {
            Box(
                modifier = Modifier.padding(it)
            ) {
                Column(
                    modifier = if(scrollable) Modifier.verticalScroll(rememberScrollState()) else Modifier
                ) {
                    content()
                }
            }
        }
    }

    if(dialogRequest != null){
        Dialog(
            onDismissRequest = { dialogRequest.negativeCallback() },
            properties = DialogProperties(dismissOnBackPress = dialogRequest.cancelable, dismissOnClickOutside = dialogRequest.cancelable)
        ) {
            AppDialog(
                title = dialogRequest.title,
                content = dialogRequest.content,
                negativeCallback = dialogRequest.negativeCallback
            )
        }
    }

    if(isLoading) {
        Dialog(onDismissRequest = {  }) {
            Surface(
                modifier = Modifier
                    .size(80.dp),
                shape = RoundedCornerShape(8.dp),
                color = Color.White
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        color = ColorPrimary,
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
internal fun AppScaffoldPreview() {
    PRIMODemoAppliicatonTheme {
        AppScaffold(
            content = {}
        )
    }
}