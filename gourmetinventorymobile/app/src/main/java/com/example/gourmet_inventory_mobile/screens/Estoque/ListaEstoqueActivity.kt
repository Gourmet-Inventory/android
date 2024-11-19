package com.example.gourmet_inventory_mobile.screens.Estoque

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.model.Usuario.User
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueItemDiscriminator
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_CianoClaro
import com.example.gourmet_inventory_mobile.ui.theme.GI_Laranja
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.utils.BottomBarGerente
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
import com.example.gourmet_inventory_mobile.utils.DrawScrollableView
import com.example.gourmet_inventory_mobile.utils.LoadingList
import com.example.gourmet_inventory_mobile.utils.SearchBox
import com.example.gourmet_inventory_mobile.viewmodel.EstoqueConsultaState
import com.example.gourmet_inventory_mobile.viewmodel.EstoqueViewModel
import kotlinx.coroutines.flow.first
import java.time.format.DateTimeFormatter

@Composable
fun ListaEstoqueScreen(
    viewModel: EstoqueViewModel,
    navController: NavController,
    onListaEstoqueClick: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
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

                        if (currentUser?.cargo == context.resources.getString(R.string.gerente)) {
                            onListaEstoqueClick("perfil")
                        } else {
                            Toast.makeText(
                                context,
                                "Acesso restrito a Gerentes",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
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
        Surface(
            color = Color.White,
            modifier = Modifier.fillMaxSize()
        ) {
            var texto by remember { mutableStateOf("") }

            // Obtém o ViewModel do Koin
            val estoqueState by viewModel.estoqueConsultaState.collectAsState()
            Log.d("ListaEstoqueScreen", "estoqueState: ${estoqueState}")

            val isLoading = estoqueState is EstoqueConsultaState.Loading
            val listaEstoque = (estoqueState as? EstoqueConsultaState.Success)?.estoqueConsulta ?: emptyList()

//            val listaEstoque = (estoqueState as? EstoqueConsultaState.Success)
//                ?.estoqueConsulta
//                ?.mapNotNull { it as? EstoqueConsulta }
//                ?: emptyList()
            Log.d("ListaEstoqueScreen", "listaEstoque: $listaEstoque")
            val filteredEstoque = listaEstoque.filter { estoqueItem ->
                val nome = estoqueItem.nome ?: ""
                val dtaAviso = estoqueItem.dtaAviso?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: ""
                val unitario = estoqueItem.unitario?.toString() ?: ""

                nome.contains(texto, ignoreCase = true) ||
                        dtaAviso.contains(texto, ignoreCase = true) ||
                        unitario.contains(texto, ignoreCase = true)
            }

            Log.d("ListaEstoqueScreen", "Filtered estoque: $filteredEstoque")

            // Filtra a lista com base no texto da pesquisa

//            val filteredEstoque =
//                (listaEstoque as? List<EstoqueItemDiscriminator>)?.filter { estoqueItem ->
//                    estoqueItem.nome.contains(texto, ignoreCase = true) ||
//                            estoqueItem.dtaAviso.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
//                                .contains(texto, ignoreCase = true) ||
//                            estoqueItem.unitario.toString().contains(texto, ignoreCase = true)
//                }
            Log.d("ListaEstoqueScreen", "filteredEstoque: $filteredEstoque")

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
                        AddButton(onAddClick = { showDialog = true }, containerColor = GI_AzulMarinho)
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

                    if (isLoading) {
//                        CircularProgressIndicator()
                        LoadingList()
                    } else if (estoqueState is EstoqueConsultaState.Success) {
                        if (filteredEstoque != null) {
                            if (filteredEstoque.isNullOrEmpty()) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = 20.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Nenhum estoque encontrado",
                                        fontSize = 20.sp,
                                        modifier = Modifier.padding(top = 20.dp)
                                    )
                                    Spacer(modifier = Modifier.height(40.dp))
                                    Image(
                                        painter = painterResource(id = R.drawable.estoquevazio),
                                        contentDescription = "imagem de estoque vazio",
                                        modifier = Modifier
                                            .padding(top = 20.dp)
                                            .height(200.dp)
                                            .width(200.dp)
                                    )
                                }
                            } else {
                                Log.d("ListaEstoqueScreen", "filteredEstoque: $filteredEstoque")
                                if (filteredEstoque != null) {
                                    ItensListaEstoque(
                                        estoque = filteredEstoque,
                                        onListaEstoqueClick = onListaEstoqueClick
                                    )
                                }
                            }
                        }
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Nenhum estoque encontrado",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(top = 20.dp)
                            )
                            Spacer(modifier = Modifier.height(40.dp))
                            Image(
                                painter = painterResource(id = R.drawable.estoquevazio),
                                contentDescription = "imagem de estoque vazio",
                                modifier = Modifier
                                    .padding(top = 20.dp)
                                    .height(200.dp)
                                    .width(200.dp)
                            )
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

//@Preview(showSystemUi = true)
//@Composable
//fun ListaEstoquePreview() {
//    ListaEstoqueScreen(
//        viewModel = EstoqueViewModel(
//            estoqueRepository = EstoqueRepository()
//        ),
//        navController = NavController(LocalContext.current),
//        onListaEstoqueClick = {}
//    )
//}

@Composable
fun AddButton(onAddClick: () -> Unit, containerColor: Color) {
    SmallFloatingActionButton(
        onClick = onAddClick,
        containerColor = containerColor,
        contentColor = White,
        modifier = Modifier.width(70.dp),
        shape = RoundedCornerShape(20.dp)
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
                .background(Color.White, shape = RoundedCornerShape(10.dp)),
            onDismissRequest = { onDismiss() },
            containerColor = Color.White,
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
                            text = "INDUSTRIALIZADO",
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontFamily = JostBold
                            )
                        )
                    }
                    OutlinedButton(
                        onClick = {
                            onListaEstoqueClick("cadastrarItemEstoqueManilpulado")
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
    estoque: List<EstoqueItemDiscriminator>,
    onListaEstoqueClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 75.dp)
    ) {
        DrawScrollableView(
            modifier = Modifier
                .fillMaxHeight(),
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 3.dp, bottom = 3.dp, end = 10.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    estoque.forEach() { estoqueItem ->
                        ItemListaEstoque(
                            estoqueItem = estoqueItem,
                            onListaEstoqueClick = onListaEstoqueClick,
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun ItemListaEstoque(
    estoqueItem: EstoqueItemDiscriminator,
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
                when (estoqueItem) {
                    is EstoqueItemDiscriminator.Manipulado -> {
                        onListaEstoqueClick("itemEstoqueManipulado/${estoqueItem.idItem}")
                    }

                    is EstoqueItemDiscriminator.Industrializado -> {
                        onListaEstoqueClick("itemEstoque/${estoqueItem.idItem}")
                    }
                }
//                if (estoqueItem.manipulado) {
//                    onListaEstoqueClick("itemEstoqueManipulado/${estoqueItem.idItem}")
//                } else {
//                    onListaEstoqueClick("itemEstoque/${estoqueItem.idItem}")
//                }
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
                    text = "Data de Aviso: ${
                        estoqueItem.dtaAviso.format(
                            DateTimeFormatter.ofPattern(
                                "dd/MM/yyyy"
                            )
                        )
                    }",
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

//@Preview(showSystemUi = true)
//@Composable
//fun ListaEstoquePreview() {
//    ListaEstoqueScreen(
//        viewModel = EstoqueViewModel(
//            estoqueRepository = EstoqueRepository()
//        ),
//        navController = NavController(LocalContext.current),
//        onListaEstoqueClick = {}
//    )
//}