package com.alvarengadev.cryptotracker.crypto.domain

import com.alvarengadev.cryptotracker.core.domain.util.NetworkError
import com.alvarengadev.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}
