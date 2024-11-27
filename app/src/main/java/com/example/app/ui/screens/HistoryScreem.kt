package com.seuprojeto.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.app.model.Transaction


// Telas do aplicativo
@Composable
fun HistoryScreen() {
    // Dados estáticos para simulação
    val transactions = mapOf(
        "Hoje" to listOf(
            Transaction("Título 1", "Categoria | Banco", 0.0, true),
            Transaction("Título 2", "Categoria | Banco", 0.0, false)
        ),
        "Quarta, 05" to listOf(
            Transaction("Título 3", "Categoria | Banco", 0.0, true),
            Transaction("Título 4", "Categoria | Banco", 0.0, false)
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        transactions.forEach { (date, transactionList) ->
            // Cabeçalho de Data
            item {
                Text(
                    text = date,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            // Lista de Transações
            items(transactionList) { transaction ->
                TransactionCard(transaction)
            }
        }
    }
}

@Composable
fun TransactionCard(transaction: Transaction) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícone
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Black, CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Informações da Transação
            Column(modifier = Modifier.weight(1f)) {
                Text(text = transaction.title, style = MaterialTheme.typography.bodyMedium)
                Text(
                    text = transaction.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Valor e Indicadores
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "R$ ${transaction.amount}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Row {
                    if (transaction.isPositive) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Entrada",
                            tint = Color.Green
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Saída",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}
