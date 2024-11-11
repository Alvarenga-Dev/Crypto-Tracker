package com.alvarengadev.cryptotracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.alvarengadev.cryptotracker.core.presentation.util.ObserverAsEvents
import com.alvarengadev.cryptotracker.core.presentation.util.toString
import com.alvarengadev.cryptotracker.crypto.presentation.coinList.CoinListEvent
import com.alvarengadev.cryptotracker.crypto.presentation.coinList.components.CoinListScreen
import com.alvarengadev.cryptotracker.crypto.presentation.coinList.CoinListViewModel
import com.alvarengadev.cryptotracker.ui.theme.CryptoTrackerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = koinViewModel<CoinListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle() //started value for CoinListState()
                    val context = LocalContext.current
                    ObserverAsEvents(events = viewModel.event) { event ->
                        when (event) {
                            is CoinListEvent.Error -> {
                                Toast.makeText(
                                    context,
                                    event.error.toString(context),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    CoinListScreen(
                        state = state,
                        events = viewModel.event,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
