package com.alvarengadev.cryptotracker.di

import com.alvarengadev.cryptotracker.core.data.networking.HttpClientFactory
import com.alvarengadev.cryptotracker.crypto.data.networking.RemoteCoinDataSource
import com.alvarengadev.cryptotracker.crypto.domain.CoinDataSource
import com.alvarengadev.cryptotracker.crypto.presentation.coinList.viewmodel.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModules = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()
    //singleOf for RemoteCoinDataSource(get()) and bind is interface CoinDataSource

    viewModelOf(::CoinListViewModel)
}
