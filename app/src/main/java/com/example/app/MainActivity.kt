package com.example.app
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.app.model.Currency
import com.example.app.network.PriceController
import com.example.app.ui.theme.AppTheme
import com.seuprojeto.ui.MainScreen
import com.seuprojeto.ui.screens.CurrencyScreen
import com.seuprojeto.ui.screens.HistoryScreen
import com.seuprojeto.ui.screens.HomeScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme(darkTheme = true) {
                MainScreen()
            }
            //AppGestaoFinanceira2()
            //App()
            //PriceAppTest()
        }
    }
}

//@Composable
//private fun AppGestaoFinanceira() {
//
//    val navItems = remember {
//        listOf(
//            NavItem(icon = Icons.Default.Home, label = "Home"),
//            NavItem(icon = Icons.Default.ShoppingCart, label = "Cotações"),
//            NavItem(icon = Icons.Default.List, label = "Histórico"),
//            NavItem(icon = Icons.Default.MoreVert, label = "More")
//        )
//    }
//
//    var selectedItem by remember { mutableStateOf(navItems.first()) }
//
//    val items = remember { mutableStateListOf<String>() } // Lista de itens para o histórico
//
//
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        bottomBar = {
//            NavigationBar {
//                navItems.forEach { item ->
//                    NavigationBarItem(
//                        selected = item == selectedItem,
//                        onClick = { selectedItem = item },
//                        icon = { Icon(item.icon, contentDescription = null) },
//                        label = { Text(item.label) }
//                    )
//                }
//            }
//        },
//        floatingActionButton = {
//            FloatingActionButton(onClick = {
//                items.add("Item ${items.size + 1}") // Adiciona um novo item à lista
//            }) {
//                Icon(Icons.Default.Add, contentDescription = "Adicionar Item")
//            }
//        }
//
//    ) { innerPadding ->
//        HistoryScreem(Modifier.padding(innerPadding), items)
//    }
//}

@Composable
fun PriceAppTest() {
    var selectedCurrency by remember { mutableStateOf("") }
    var coin by remember { mutableStateOf("") }
    var price by remember { mutableStateOf<Currency?>(null) }
    var errorMessage by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val controller = PriceController()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Campo de entrada para o ano
        Text(text = "Selecione a moeda:", modifier = Modifier.padding(8.dp)) // Adicione um label
        //CurrencySelector(selectedCurrency = selectedCurrency) { selectedCurrency = it }
        coin = selectedCurrency
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (coin.isNotEmpty()) {
                coroutineScope.launch(Dispatchers.IO) {
                    try {
                        val response = controller.getAwensomeApi(coin, "BRL")
                        // Retorne para a thread principal para atualizar a UI
                        withContext(Dispatchers.Main) {
                            price = response
                            errorMessage = ""
                        }
                    } catch (e: Exception) {
                        errorMessage = "Erro ao buscar dados: ${e.message}"
                        price = null
                    }
                }

            }
        },
            enabled = coin.isNotEmpty()
        ) {
            Text("Buscar Cotação")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
        if (price != null) {
            WritePrice(price!!)
        }
    }
}

@Composable
fun WritePrice(price: Currency?) {
    price?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = it.name)
            Text(text = it.high)
            Text(text = it.low)
        }
    }
}

// Modelo para cada item da barra de navegação
data class NavItem(val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val label: String)

@Composable
fun AppGestaoFinanceira2() {
    val navController = rememberNavController()

    // Itens da barra de navegação
    val navItems = remember {
        listOf(
            NavItem(route = "home", icon = Icons.Default.Home, label = "Home"),
            NavItem(route = "quotes", icon = Icons.Default.ShoppingCart, label = "Cotações"),
            NavItem(route = "history", icon = Icons.Default.List, label = "Histórico"),
            NavItem(route = "more", icon = Icons.Default.MoreVert, label = "Mais")
        )
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navItems = navItems, navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen() }
            composable("quotes") { CurrencyScreen() }
            composable("history") { HistoryScreen() }
            composable("more") { MoreScreen() }
        }
    }
}

// Barra de Navegação Inferior
@Composable
fun BottomNavigationBar(navItems: List<NavItem>, navController: NavHostController) {
    val currentRoute = navController.currentBackStackEntryAsState()?.value?.destination?.route

    BottomNavigation() {
        navItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}




@Composable
fun HeaderSection() {
    // Implementação da área de destaque
}

@Composable
fun CurrencyCard(currency: Currency) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = currency.name, style = MaterialTheme.typography.bodyLarge)
                //Text(text = "1 ${currency.code} = ${currency.rate} BRL", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun MoreScreen() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Text(text = "Tela Mais")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Greeting("Android")
    }
}