package com.alvarengadev.cryptotracker.crypto.presentation.coinList

import com.alvarengadev.cryptotracker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coin: CoinUi) : CoinListAction
    data object OnRefresh : CoinListAction
}
