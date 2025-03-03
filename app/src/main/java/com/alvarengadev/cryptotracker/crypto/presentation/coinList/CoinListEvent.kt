package com.alvarengadev.cryptotracker.crypto.presentation.coinList

import com.alvarengadev.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}