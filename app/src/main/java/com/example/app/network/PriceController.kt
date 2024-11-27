package com.example.app.network

import com.example.app.model.Currency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PriceController {
    suspend fun getAwensomeApi(baseCoin: String, targetCoin: String = "BRL"): Currency? {
        val coinPair = if (targetCoin == "BRL") baseCoin else "$baseCoin-$targetCoin"
        return withContext(Dispatchers.IO) {
            RetrofitInstance.api.getAwensomeApi(coinPair).get(coinPair)
        }
    }
}
