package com.alvarengadev.cryptotracker.crypto.data.mappers

import com.alvarengadev.cryptotracker.crypto.data.networking.dto.CoinDto
import com.alvarengadev.cryptotracker.crypto.domain.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank.toInt(),
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}
