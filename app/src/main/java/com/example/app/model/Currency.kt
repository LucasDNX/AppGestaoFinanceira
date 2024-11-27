package com.example.app.model

data class CurrencyResponse(
    val USDBRL: Currency? = null,
    val EURBRL: Currency? = null,
) {
    operator fun get(currencyPair: String): Currency? {
        return when (currencyPair) {
            "USD" -> USDBRL
            "EUR" -> EURBRL
            else -> null
        }
    }
}

data class Currency (
    val code: String,         // Código da moeda original (ex: "USD")
    val codein: String,       // Código da moeda de destino (ex: "BRL")
    val name: String,         // Nome da taxa de câmbio (ex: "Dólar Americano/Real Brasileiro")
    val high: String,         // Maior valor da moeda no período
    val low: String,          // Menor valor da moeda no período
    val varBid: String,       // Variação do valor de compra
    val pctChange: String,    // Percentual de variação
    val bid: String,          // Preço de compra
    val ask: String,          // Preço de venda
    val timestamp: String,    // Timestamp do dado
    val create_date: String   // Data de criação do dado
)
