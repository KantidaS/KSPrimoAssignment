package com.example.primodemoappliicaton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.primodemoappliicaton.ui.HomeScreen
import com.example.primodemoappliicaton.ui.theme.PRIMODemoAppliicatonTheme
import com.example.primodemoappliicaton.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel: HomeViewModel = koinViewModel()
            PRIMODemoAppliicatonTheme {
                HomeScreen(viewModel)
            }
        }
    }
}
