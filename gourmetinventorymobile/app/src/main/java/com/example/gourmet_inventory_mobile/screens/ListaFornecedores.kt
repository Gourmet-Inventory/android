package com.example.gourmet_inventory_mobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_Orange
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.utils.BottomBarGerente

//class ListaFornecedoresActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            GourmetinventorymobileTheme {
//                ListaFornecedoresScreen()
//            }
//        }
//    }
//}

@Composable
fun ListaFornecedoresScreen(
    navController: NavController,
    onListaFornecedoresClick: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(
                    onClick = {
                        onListaFornecedoresClick("perfil")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GI_Orange,
                        contentColor = White
                    ),
                    modifier = Modifier.padding(top = 16.dp, end = 16.dp)
                ) {
                    Text(text = "Mudar Perfil", color = Black)
                }
            }
        },
        bottomBar = {
            BottomBarGerente(navController = navController, onClick = onListaFornecedoresClick)
        }
    ) { padding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            val context = LocalContext.current

            // Estado para o texto do campo de pesquisa
            var texto by remember { mutableStateOf("") }

            // Lista de fornecedores
            val fornecedores = listOf(
                "Kibon" to Pair("(11)96574-2849", "Bom-bom"),
                "Garoto" to Pair("(11)4543-9854", "Chocolate"),
                "Nestlé" to Pair("(11)4002-8922", "Doces"),
                "Hershey's" to Pair("(11)91234-5678", "Chocolate"),
                "Cacau Show" to Pair("(11)99876-5432", "Doces"),
                "Frutap" to Pair("(11)98765-4321", "Frutas"),
                "Bauducco" to Pair("(11)12345-6789", "Biscoitos"),
                "Perdigão" to Pair("(11)23456-7890", "Carnes"),
                "Panco" to Pair("(11)34567-8901", "Pães"),
                "Sadia" to Pair("(11)45678-9012", "Carnes")
            )

            // Filtra a lista com base no texto da pesquisa
            val filteredFornecedores = fornecedores.filter {
                it.first.contains(texto, ignoreCase = true) ||
                        it.second.first.contains(texto) ||
                        it.second.second.contains(texto, ignoreCase = true)
            }

            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp , top = 70.dp)
                ) {
                    // Título
                    Text(
                        text = "Fornecedor:",
                        fontSize = 34.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Campo de Pesquisa
                    TextField(
                        value = texto,
                        onValueChange = { novoTexto -> texto = novoTexto },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        label = { Text("Pesquisar") },
                        placeholder = { Text("") },
                        shape = RoundedCornerShape(8.dp)
                    )

                    // Lista de Fornecedores Filtrada
                    LazyColumn(modifier = Modifier.fillMaxWidth().padding(bottom = 75.dp)) {
                        items(filteredFornecedores) { fornecedor ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .background(GI_Orange.copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp)
                                        .clickable(onClick = {
                                            onListaFornecedoresClick("fornecedorView")
                                        })
                                ) {
                                    Text(text = fornecedor.first, fontSize = 20.sp)
                                    Text(
                                        text = "Telefone: ${fornecedor.second.first}",
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = "Categoria: ${fornecedor.second.second}",
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview
@Composable
fun ListaFornecedoresPreview() {
    ListaFornecedoresScreen(
        navController = NavController(LocalContext.current),
        onListaFornecedoresClick = {},
    )
}