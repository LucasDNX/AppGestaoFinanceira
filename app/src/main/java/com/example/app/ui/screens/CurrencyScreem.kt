package com.seuprojeto.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class CurrencyTest(
    val code: String,
    val name: String,
    val valor: Double
)

@Composable
fun CurrencyScreen() {
    val availableCurrencies = listOf("USD", "BRL", "EUR", "GBP") // Moedas disponíveis
    var searchQuery by remember { mutableStateOf("") }
    val currencies = listOf(
        CurrencyTest("Dólar Americano", "USD", 5.25),
        CurrencyTest("Euro", "EUR", 6.10),
        CurrencyTest("Libra", "GBP", 7.30)
    ).filter { it.name.contains(searchQuery, ignoreCase = true) || it.code.contains(searchQuery, ignoreCase = true) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Header com os seletores de moeda
        CurrencyHeaderSection()

        // Campo de busca
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Buscar moeda...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Lista de cotações
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
//            items(currencies) { currency ->
//                CurrencyCard(currency)
//            }
        }
    }
}


@Composable
fun CurrencyHeaderSection() {
    val availableCurrencies = listOf("USD", "BRL", "EUR", "GBP") // Lista de moedas disponíveis
    var fromCurrency by remember { mutableStateOf("USD") }      // Moeda de origem
    var toCurrency by remember { mutableStateOf("BRL") }        // Moeda de destino

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Selecionar moeda de origem
        CurrencySelector(
            availableCurrencies = availableCurrencies,
            selectedCurrency = fromCurrency,
            onCurrencySelected = { newCurrency -> fromCurrency = newCurrency }
        )

        Text(
            text = "= 5.25 BRL", // Mostra a cotação fixa (estática para este exemplo)
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        // Selecionar moeda de destino
        CurrencySelector(
            availableCurrencies = availableCurrencies,
            selectedCurrency = toCurrency,
            onCurrencySelected = { newCurrency -> toCurrency = newCurrency }
        )
    }
}


@Composable
fun CurrencySelector(
    availableCurrencies: List<String>, // Lista de moedas disponíveis, ex: ["USD", "BRL", "EUR"]
    selectedCurrency: String,         // Moeda atualmente selecionada
    onCurrencySelected: (String) -> Unit // Callback para quando uma moeda for selecionada
) {
    var expanded by remember { mutableStateOf(false) } // Controla se o menu está aberto

    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
        // Botão para abrir o menu
        Button(
            onClick = { expanded = true },
        ) {
            Text(selectedCurrency) // Mostra a moeda atualmente selecionada
        }

        // Dropdown com as opções
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            availableCurrencies.forEach { currency ->
                DropdownMenuItem(onClick = {
                    onCurrencySelected(currency) // Atualiza a moeda selecionada
                    expanded = false // Fecha o menu
                }) {
                    Text(currency) // Mostra o nome da moeda
                }
            }
        }
    }
}