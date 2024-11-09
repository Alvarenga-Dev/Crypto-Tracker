package com.alvarengadev.cryptotracker.crypto.presentation.coinList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alvarengadev.cryptotracker.core.domain.util.onError
import com.alvarengadev.cryptotracker.core.domain.util.onSuccess
import com.alvarengadev.cryptotracker.crypto.domain.Coin
import com.alvarengadev.cryptotracker.crypto.domain.CoinDataSource
import com.alvarengadev.cryptotracker.crypto.presentation.models.toCoinUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart { loadCoins() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.OnCoinClick -> {
                _state.update {
                    it.copy(selectedCoin = action.coin)
                }
            }
            CoinListAction.OnRefresh -> {
                loadCoins()
            }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            coinDataSource.getCoins()
                .onSuccess { coins ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coins = coins.map(Coin::toCoinUi)
                        )
                    }
                }
                .onError {
                    _state.update {
                        it.copy(isLoading = false)
                    }
                }
        }
    }
}
