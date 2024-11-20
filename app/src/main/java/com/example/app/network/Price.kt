package com.example.app.network

data class PriceResponse(
    val USDBRL: Price? = null,
    val EURBRL: Price? = null,
    // Adicione mais pares de moedas conforme necessário
) {
    operator fun get(currencyPair: String): Price? {
        return when (currencyPair) {
            "USD" -> USDBRL
            "EUR" -> EURBRL
            else -> null // Retornar null se o par não estiver mapeado
        }
    }
}

data class Price (
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
