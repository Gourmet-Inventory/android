package com.example.gourmet_inventory_mobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_CianoClaro
import com.example.gourmet_inventory_mobile.ui.theme.GI_Orange
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.utils.BottomBarGerente

@Composable
fun ListaEstoqueScreen(
    navController: NavController,
    onListaEstoqueClick: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(
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
                        .padding(start = 16.dp, end = 16.dp, top = 70.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Título
                        Text(
                            text = "Estoque:",
                            fontSize = 34.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        AddButton(onAddClick = { showDialog = true })
                    }

                    SearchBoxEstoque(
                        searchText = texto,
                        mudaValorCampo = { novoTexto -> texto = novoTexto }
                    )

                    // Lista de Fornecedores Filtrada
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 75.dp)
                    ) {
                        items(filteredFornecedores) { fornecedor ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .border(1.dp, Black, RoundedCornerShape(8.dp))
                                    .background(
                                        GI_AzulMarinho.copy(alpha = 0.2f),
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clickable(onClick = {
                                        onListaEstoqueClick("itemEstoque")
                                    }),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .background(color = White)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(
                                                GI_AzulMarinho,
                                                RoundedCornerShape(
                                                    bottomEnd = 0.dp,
                                                    bottomStart = 0.dp,
                                                    topEnd = 8.dp,
                                                    topStart = 8.dp
                                                )
                                            )
                                            .height(30.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceAround
                                    ) {
                                        Text(
                                            text = fornecedor.first,
                                            fontSize = 18.sp,
                                            color = White
                                        )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 10.dp, bottom = 10.dp, start = 10.dp),
                                        verticalArrangement = Arrangement.SpaceAround
                                    ) {
                                        Text(
                                            text = "Data de Aviso: ${fornecedor.second.first}",
                                            fontSize = 16.sp
                                        )
                                        Text(
                                            text = "Quantidade: ${fornecedor.second.second}",
                                            fontSize = 16.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

            if (showDialog) {
                EscolhaTipoDeEstoque(
                    onListaEstoqueClick = onListaEstoqueClick,
                    showDialog = showDialog,
                    onDismiss = { showDialog = false }
                )
            }
        }
    }
}

@Composable
fun SearchBoxEstoque(searchText: String, mudaValorCampo: (String) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = { novoValorCampo: String ->
            mudaValorCampo(novoValorCampo)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(bottom = 16.dp, top = 16.dp)
            .background(color = GI_CianoClaro, shape = RoundedCornerShape(5.dp)),
        placeholder = {
            Text(
                text = "Pesquisar",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Black,
                ),
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Ícone de pesquisa"
            )
        },
        shape = RoundedCornerShape(5.dp),
    )
}

@Preview
@Composable
fun ListaEstoquePreview() {
    ListaEstoqueScreen(
        navController = NavController(LocalContext.current),
        onListaEstoqueClick = {}
    )
}

@Composable
fun AddButton(onAddClick: () -> Unit) {
    SmallFloatingActionButton(
        onClick = onAddClick,
        containerColor = GI_AzulMarinho,
        contentColor = White,
        modifier = Modifier.width(70.dp),
        shape = RoundedCornerShape(20.dp),
    ) {
        Icon(Icons.Filled.Add, "Small floating action button.")
    }
}

@Composable
fun EscolhaTipoDeEstoque(
    onListaEstoqueClick: (String) -> Unit,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            modifier = Modifier
                .background(White, shape = RoundedCornerShape(10.dp)),
            onDismissRequest = { onDismiss() },

            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Selecione o tipo:",
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .align(Alignment.CenterVertically),
                    )
                }
            },

            confirmButton = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedButton(
                        onClick = {
                            onListaEstoqueClick("cadastrarItemEstoque")
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GI_Orange,
                            contentColor = Black
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp),
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        Text("SIMPLES")
                    }
                    OutlinedButton(
                        onClick = {
                            onListaEstoqueClick("cadastrarItemEstoque")
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GI_Orange,
                            contentColor = Black
                        ),
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("MANIPULADO")
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun EscolhaPreview() {
    EscolhaTipoDeEstoque(onListaEstoqueClick = {}, showDialog = true, onDismiss = {})
}