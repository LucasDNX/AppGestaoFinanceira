package com.example.app.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PriceController {
    suspend fun getAwensomeApi(baseCoin: String, targetCoin: String = "BRL"): Price? {
        val coinPair = if (targetCoin == "BRL") baseCoin else "$baseCoin-$targetCoin"
        return withContext(Dispatchers.IO) {
            RetrofitInstance.api.getAwensomeApi(coinPair).get(coinPair)
        }
    }
}

//class FeriadoController {
//    suspend fun fetchFeriados(year: Int): List<Feriado> {
//        return withContext(Dispatchers.IO) {
//            RetrofitInstance.api.getFeriados(year)
//        }
//   }
//