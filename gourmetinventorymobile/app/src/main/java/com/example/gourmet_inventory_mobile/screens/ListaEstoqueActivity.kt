package com.example.gourmet_inventory_mobile.screens

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.model.Empresa
import com.example.gourmet_inventory_mobile.model.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.Medidas
import com.example.gourmet_inventory_mobile.model.User
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_CianoClaro
import com.example.gourmet_inventory_mobile.ui.theme.GI_Laranja
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.utils.BottomBarGerente
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
import com.example.gourmet_inventory_mobile.utils.SearchBox
import kotlinx.coroutines.flow.first
import java.time.LocalDate

@Composable
fun ListaEstoqueScreen(
    navController: NavController,
    onListaEstoqueClick: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    Log.d("ListaEstoqueScreen", "")

    val context = LocalContext.current

    var currentUser: User? by remember { mutableStateOf(null) }
    LaunchedEffect(Unit) {
        currentUser = DataStoreUtils(context = context).obterUsuario()?.first()
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(
                    onClick = {
                        Log.d("ListaEstoqueScreen", "currentUser: ${currentUser}")

                        if (currentUser?.role == "Gerente") {
                            onListaEstoqueClick("perfil")
                        } else {
                            Toast.makeText(context, "Acesso restrito a Gerentes", Toast.LENGTH_SHORT).show()
                        }                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GI_Laranja,
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

            val empresa = Empresa(idEmpresa = 1L, nomeFantasia = "Empresa A")

            val listaEstoqueConsulta = listOf(
                EstoqueConsulta(1,empresa,true,"Lote1","Item1","Categoria1",Medidas.UNIDADE,10,2.5,25.0,"Local1",LocalDate.now(),LocalDate.now().plusDays(10)),
                EstoqueConsulta(2,empresa,false,"Lote2","Item2","Categoria2",Medidas.QUILOGRAMA,5,3.0,15.0,"Local2",LocalDate.now(),LocalDate.now().plusDays(20)),
                EstoqueConsulta(3,empresa,true,"Lote3","Item3","Categoria3",Medidas.LITRO,8,1.5,12.0,"Local3",LocalDate.now(),LocalDate.now().plusDays(30)),
                EstoqueConsulta(4,empresa,false,"Lote4","Item4","Categoria4",Medidas.UNIDADE,12,2.0,24.0,"Local4",LocalDate.now(),LocalDate.now().plusDays(40)),
                EstoqueConsulta(5,empresa,true,"Lote5","Item5","Categoria5",Medidas.QUILOGRAMA,7,4.0,28.0,"Local5",LocalDate.now(),LocalDate.now().plusDays(50)),
                EstoqueConsulta(6,empresa,false,"Lote6","Item6","Categoria6",Medidas.LITRO,9,3.5,31.5,"Local6",LocalDate.now(),LocalDate.now().plusDays(60)),
                EstoqueConsulta(7,empresa,true,"Lote7","Item7","Categoria7",Medidas.UNIDADE,11,1.0,11.0,"Local7",LocalDate.now(),LocalDate.now().plusDays(70)),
                EstoqueConsulta(8,empresa,false,"Lote8","Item8","Categoria8",Medidas.QUILOGRAMA,6,2.5,15.0,"Local8",LocalDate.now(),LocalDate.now().plusDays(80)),
                EstoqueConsulta(9,empresa,true,"Lote9","Item9","Categoria9",Medidas.LITRO,4,5.0,20.0,"Local9",LocalDate.now(),LocalDate.now().plusDays(90)),
                EstoqueConsulta(10,empresa,false,"Lote10","Item10","Categoria10",Medidas.UNIDADE,3,6.0,18.0,"Local10",LocalDate.now(),LocalDate.now().plusDays(100))
            )

            // Filtra a lista com base no texto da pesquisa
            val filteredEstoque = listaEstoqueConsulta.filter {
                it.nome.contains(texto, ignoreCase = true) ||
                        it.categoria.contains(texto, ignoreCase = true) ||
                        it.localArmazenamento.contains(texto, ignoreCase = true)
            }

            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 20.dp, end = 20.dp, top = 70.dp)
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

                    SearchBox(
                        searchText = texto,
                        mudaValorCampo = { novoTexto -> texto = novoTexto },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(85.dp)
                            .padding(bottom = 30.dp)
                            .background(color = GI_CianoClaro, shape = RoundedCornerShape(5.dp)),
                    )

                    // Lista de Fornecedores Filtrada
                    ItensListaEstoque(
                        estoque = filteredEstoque,
                        onListaEstoqueClick = onListaEstoqueClick
                    )

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
        Icon(Icons.Filled.Add, "")
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
                .background(Color.Transparent, shape = RoundedCornerShape(10.dp)),
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
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedButton(
                        onClick = {
                            onListaEstoqueClick("cadastrarItemEstoque")
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GI_Laranja,
                            contentColor = Black
                        ),
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(75.dp)
                            .padding(bottom = 30.dp),
                    ) {
                        Text(
                            text = "SIMPLES",
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontFamily = JostBold
                            )
                        )
                    }
                    OutlinedButton(
                        onClick = {
                            onListaEstoqueClick("cadastrarItemEstoque")
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GI_Laranja,
                            contentColor = Black
                        ),
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(75.dp)
                            .padding(bottom = 30.dp)
                    ) {
                        Text(
                            text = "MANIPULADO",
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontFamily = JostBold
                            )
                        )
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

@Composable
fun ItensListaEstoque(
    estoque: List<EstoqueConsulta>,
    onListaEstoqueClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 75.dp)
    ) {
        val listScrollState = rememberLazyListState()
        LazyColumn(
//        state = listScrollState,
            modifier = Modifier
//                .height(345.dp)
//                .width(325.dp)
                .fillMaxSize()
                .padding(top = 3.dp, bottom = 3.dp),
//            .verticalScroll(rememberScrollState(), true),
            verticalArrangement = Arrangement.Top
        ) {
            items(estoque) { estoqueItem ->
                ItemListaEstoque(
                    estoqueItem = estoqueItem,
                    onListaEstoqueClick = onListaEstoqueClick,
                )
            }
        }
    }
}

@Composable
fun ItemListaEstoque(
    estoqueItem: EstoqueConsulta,
    onListaEstoqueClick: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(2.dp, GI_AzulMarinho, RoundedCornerShape(8.dp))
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
                            topStart = 8.dp,
                        )
                    )
                    .height(30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = estoqueItem.nome,
                    fontSize = 20.sp,
                    fontFamily = JostBold,
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
                    text = "Data de Aviso: ${estoqueItem.dtaAviso}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Quantidade: ${estoqueItem.unitario}",
                    fontSize = 16.sp
                )
            }
        }
    }
}