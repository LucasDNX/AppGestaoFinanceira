package com.seuprojeto.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Card Principal
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Janeiro", style = MaterialTheme.typography.titleMedium)

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Sobra do mês",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "R$ 3.333,33",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = "Receitas",
                            color = Color.Green,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(text = "R$ 4.000,00", color = Color.Green)
                    }
                    Column {
                        Text(
                            text = "Despesas",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(text = "R$ 666,66", color = Color.Red)
                    }
                }
            }
        }

        // Card de Resumo Mensal
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // Para expandir proporcionalmente
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Despesas do mês",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Placeholder do gráfico
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.LightGray, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Gráfico Placeholder")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Detalhes de categorias
                Column {
                    Text(text = "Salário: R$ 4.000,00", color = Color(0xFFFFEB3B)) // Amarelo
                    Text(text = "Poupança: R$ 1.400,00", color = Color(0xFF00BCD4)) // Azul
                    Text(text = "Cartão: R$ 666,66", color = Color(0xFFF44336)) // Vermelho
                }
            }
        }
    }
}
