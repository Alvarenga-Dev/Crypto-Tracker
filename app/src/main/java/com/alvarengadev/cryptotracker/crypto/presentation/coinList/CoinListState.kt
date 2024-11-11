package com.alvarengadev.cryptotracker.crypto.presentation.coinList

import androidx.compose.runtime.Immutable
import com.alvarengadev.cryptotracker.crypto.presentation.models.CoinUi

@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUi> = emptyList(),
    val selectedCoin: CoinUi? = null
)
