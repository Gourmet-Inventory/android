package com.example.gourmet_inventory_mobile.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_Orange
import com.example.gourmet_inventory_mobile.ui.theme.White
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.utils.BottomBarGerente

@Composable
fun ListaEstoqueScreen(
    navController: NavController,
    onListaEstoqueClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton (
                    onClick = {
                        onListaEstoqueClick("perfil")
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
            BottomBarGerente(navController = navController, onClick = onListaEstoqueClick)
        }

    ) { padding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            val context = LocalContext.current

            // Estado para o texto do campo de pesquisa
            var texto by remember { mutableStateOf("") }

            val fornecedores = listOf(
                "Molho de Tomate" to Pair(" 20/05/2024", "3 Kg"),
                "Macarrão" to Pair("20/05/2024", "3 Kg"),
                "Tomate" to Pair("20/05/2024", "3 Kg"),
                "Farinha" to Pair("20/05/2024", "3 Kg"),
                "Leite" to Pair("20/05/2024", "3 L"),
                "Açúcar Refinado" to Pair("20/05/2024", "3 Kg"),
                "Carne Bovina" to Pair("20/05/2024", "3 Kg"),
                "Barra de Chocolate" to Pair("20/05/2024", "3 Kg"),
                "Pão" to Pair("20/05/2024", "3 Kg"),
                "Sal" to Pair("20/05/2024", "3 Kg")
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

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Título
                        Text(
                            text = "Estoque:",
                            fontSize = 34.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        AddButton(onListaEstoqueClick)
                    }

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
                    LazyColumn(modifier = Modifier.fillMaxWidth().padding(bottom = 73.dp)) {
                        items(filteredFornecedores) { fornecedor ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .background(
                                        GI_AzulMarinho.copy(alpha = 0.2f),
                                        RoundedCornerShape(8.dp)
                                    ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(8.dp)
                                        .clickable(onClick = {
                                            onListaEstoqueClick("itemEstoque")
                                        })
                                ) {
                                    Text(text = fornecedor.first, fontSize = 20.sp)
                                    Text(
                                        text = "Data de Aviso: ${fornecedor.second.first}",
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = "Quantidade: ${fornecedor.second.second}",
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

//@Composable
//fun ListaEstoquerDownBar(
//    onListaEstoqueClickAcao1: () -> Unit,
//    onListaEstoqueClickAcao2: () -> Unit,
//    onListaEstoqueClickAcao3: () -> Unit,
//    onListaEstoqueClickAcao4: () -> Unit
//) {
//    val context = LocalContext.current
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(color = GI_AzulMarinho)
//            .heightIn(70.dp),
////        horizontalArrangement = Arrangement.SpaceEvenly,
//        horizontalArrangement = Arrangement.SpaceAround,
//        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.fornecedores_db),
//            contentDescription = "Ação 1",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(30.dp)
//                .clickable {
//                    onListaEstoqueClickAcao1()
//                }
//        )
////        Spacer(modifier = Modifier.height(60.dp))
//        Image(
//            painter = painterResource(id = R.drawable.opened_box),
//            contentDescription = "Ação 2",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(30.dp)
//                .clickable {
//                    onListaEstoqueClickAcao2()
//                }
//        )
//        Image(
//            painter = painterResource(id = R.drawable.cart),
//            contentDescription = "Ação 3",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(30.dp)
//                .clickable {
//                    onListaEstoqueClickAcao3()
//                }
//        )
//        Image(
//            painter = painterResource(id = R.drawable.account_icon),
//            contentDescription = "Ação 4",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(35.dp)
//                .clickable {
//                    onListaEstoqueClickAcao4()
//                }
//        )
//    }
//}

@Preview
@Composable
fun ListaEstoquePreview() {
    ListaEstoqueScreen(
        navController = NavController(LocalContext.current),
        onListaEstoqueClick = {}
    )
}

@Composable
fun AddButton(onListaEstoqueClick: (String) -> Unit) {
    val context = LocalContext.current
    SmallFloatingActionButton(
        onClick = {
            onListaEstoqueClick("cadastrarItemEstoque")
        },
        containerColor = GI_AzulMarinho,
        contentColor = White,
        modifier = Modifier.width(70.dp),
        shape = RoundedCornerShape(20.dp),
    ) {
        Icon(Icons.Filled.Add, "Small floating action button.")
    }
}