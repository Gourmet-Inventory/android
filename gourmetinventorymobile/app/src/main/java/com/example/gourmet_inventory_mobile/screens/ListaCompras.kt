package com.example.gourmet_inventory_mobile.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.model.CategoriaEstoque
import com.example.gourmet_inventory_mobile.model.Empresa
import com.example.gourmet_inventory_mobile.model.ItemListaCompras
import com.example.gourmet_inventory_mobile.model.Medidas
import com.example.gourmet_inventory_mobile.model.Usuario.User
import com.example.gourmet_inventory_mobile.model.estoque.Estoque
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_BrancoFundo
import com.example.gourmet_inventory_mobile.ui.theme.GI_CianoClaro
import com.example.gourmet_inventory_mobile.ui.theme.GI_Laranja
import com.example.gourmet_inventory_mobile.ui.theme.GI_Vermelho
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.utils.BottomBarGerente
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
import com.example.gourmet_inventory_mobile.utils.DrawScrollableView
import com.example.gourmet_inventory_mobile.viewmodel.ListaComprasViewModel
import kotlinx.coroutines.flow.first
import java.time.LocalDate

@Composable
fun ListaComprasSearchBox(searchText: String, onSearchTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchTextChanged,
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(10.dp)
            .background(color = GI_CianoClaro, shape = RoundedCornerShape(5.dp)),
        placeholder = {
            Text(
                text = "Pesquisar",
                style = TextStyle(
                    fontSize = 16.sp,
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaComprasScreen(
    viewModel: ListaComprasViewModel,
    navController: NavController,
    onListaComprasClick: (String) -> Unit,
) {
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
                        Log.d("ListaComprasScreen", "currentUser: $currentUser")
                        if (currentUser?.cargo == context.resources.getString(R.string.gerente)) {
                            onListaComprasClick("perfil")
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
            BottomBarGerente(navController = navController, onClick = onListaComprasClick)
        }
    ) { padding ->

        Surface(
            color = Color.White,
            modifier = Modifier.fillMaxSize()
        ) {
            var texto by remember { mutableStateOf("") }

            val listaCompras = viewModel.data
            val isLoading = viewModel.isLoading

            Log.d("ListaComprasScreen", "listaCompras: $listaCompras")

//            val checkedState = remember { mutableStateListOf(*Array(listaCompras.size) { false }) }

            // Filtra a lista de compras de acordo com o texto digitado
//            val filteredItems = listaCompras.filter { item ->
//                item.nome.contains(texto, ignoreCase = true)
//            }

//            val itens = remember {
//                mutableStateListOf(
//                    *listOf(
//                        ItemListaCompras(
//                            idItemLista = Long.MAX_VALUE,
//                            nome = "Queijo Prato",
//                            estoqueIngrediente = Estoque(
//                                idItem = Long.MIN_VALUE,
//                                lote = "Q1",
//                                empresa = Empresa(
//                                    idEmpresa = 1,
//                                    nomeFantasia = "Empresa"
//                                ),
//                                nome = "Queijo Prato",
//                                categoria = CategoriaEstoque.QUEIJOS_E_FRIOS,
//                                tipoMedida = Medidas.GRAMAS,
//                                unitario = 0,
//                                valorMedida = 10.0,
//                                valorTotal = 0.0,
//                                localArmazenamento = "Geladeira",
//                                dtaCadastro = LocalDate.now(),
//                                dtaAviso = LocalDate.now().plusMonths(1),
//                                marca = "Italac"
//                            )
//                        ),
//                        ItemListaCompras(
//                            idItemLista = Long.MAX_VALUE - 1,
//                            nome = "Presunto",
//                            estoqueIngrediente = Estoque(
//                                idItem = Long.MIN_VALUE + 1,
//                                lote = "P1",
//                                empresa = Empresa(
//                                    idEmpresa = 2,
//                                    nomeFantasia = "Empresa 2"
//                                ),
//                                nome = "Presunto",
//                                categoria = CategoriaEstoque.QUEIJOS_E_FRIOS,
//                                tipoMedida = Medidas.GRAMAS,
//                                unitario = 0,
//                                valorMedida = 15.0,
//                                valorTotal = 0.0,
//                                localArmazenamento = "Geladeira",
//                                dtaCadastro = LocalDate.now(),
//                                dtaAviso = LocalDate.now().plusMonths(1),
//                                marca = "Sadia"
//                            )
//                        )
//                    ).toTypedArray()
//                )
//            }
//


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 25.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Lista de Compras",
                            fontSize = 30.sp,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                    }

                    ListaComprasSearchBox(texto) { novoTexto ->
                        texto = novoTexto
                    }

                    if (listaCompras.isEmpty()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 40.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Nenhum item encontrado",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(top = 20.dp)
                            )
                            Spacer(modifier = Modifier.height(40.dp))
                            Image(
                                painter = painterResource(id = R.drawable.listacomprasvazia),
                                contentDescription = "imagem de lista de compras vazia",
                                modifier = Modifier.padding(top = 20.dp)
                            )
                        }
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Item", fontSize = 18.sp)
                            Divider(
                                color = Color.Black,
                                modifier = Modifier
                                    .width(1.dp)
                                    .height(20.dp)
                            )
                            Text(text = "Qt. Média", fontSize = 18.sp)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        ItensListaCompras(
                            listaCompras = listaCompras.toList(),
                            onListaComprasClick = { idItem ->
                                onListaComprasClick("itemEstoque/$idItem")
                            },
                            onDeleteListItem = { itemToDelete ->
                                viewModel.deletarItemListaCompras(itemToDelete.idItemLista)
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItensListaCompras(
    listaCompras: List<ItemListaCompras>,
    onListaComprasClick: (String) -> Unit,
    onDeleteListItem: (ItemListaCompras) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 75.dp, start = 8.dp, end = 8.dp)
    ) {
        DrawScrollableView(
            modifier = Modifier
                .fillMaxHeight(),
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 70.dp)
                        .background(GI_BrancoFundo, RoundedCornerShape(8.dp))
                ) {
                    listaCompras.forEach { itemLista ->
                        key(itemLista.idItemLista) {
                            val swipeToDismissBoxState = rememberSwipeToDismissBoxState()

                            LaunchedEffect(swipeToDismissBoxState.currentValue) {
                                if (swipeToDismissBoxState.currentValue == SwipeToDismissBoxValue.EndToStart) {
                                    onDeleteListItem(itemLista) // Certifique-se de que o ID está sendo passado
                                }
                            }


                            SwipeToDismissBox(
                                state = swipeToDismissBoxState,
                                backgroundContent = {
                                    when (swipeToDismissBoxState.dismissDirection) {
                                        SwipeToDismissBoxValue.StartToEnd -> {}
                                        SwipeToDismissBoxValue.EndToStart -> {
                                            Box(
                                                Modifier
                                                    .fillMaxSize()
                                                    .background(GI_Vermelho)
                                            ) {
                                                Row(
                                                    modifier = Modifier.fillMaxSize(),
                                                    horizontalArrangement = Arrangement.End,
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
//                                                    Spacer(modifier = Modifier.width(16.dp))
                                                    Text(
                                                        text = "Remover",
                                                        style = TextStyle(
                                                            color = Color.White,
                                                            fontSize = 20.sp,
                                                            fontWeight = FontWeight.Bold
                                                        ),
                                                        modifier = Modifier
                                                            .padding(16.dp)
//                                                            .align(Alignment.CenterEnd)
                                                    )
                                                    Icon(
                                                        imageVector = Icons.Default.Delete,
                                                        contentDescription = "Delete",
                                                        tint = Color.White,
                                                        modifier = Modifier
                                                            .padding(
                                                                top = 16.dp,
                                                                end = 16.dp,
                                                                bottom = 16.dp
                                                            )
//                                                            .align(Alignment.CenterEnd)
                                                    )
                                                }
                                            }
                                        }

                                        SwipeToDismissBoxValue.Settled -> {}
                                    }
                                },
                                enableDismissFromStartToEnd = false
                            ) {
                                ItemListaCompras(
                                    itemLista = itemLista,
                                    listaCompras = listaCompras
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun ItemListaCompras(
    itemLista: ItemListaCompras,
    listaCompras: List<ItemListaCompras>
) {
    Column {
        Row(
            modifier = Modifier
                .background(GI_BrancoFundo)
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Row {
                    Text(text = "Nome: ")
                    Text(text = itemLista.nome)
                }
                Row {
                    Text(text = "Marca: ")
                    Text(text = itemLista.estoqueIngrediente.marca)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "${itemLista.estoqueIngrediente.valorTotal} ${itemLista.estoqueIngrediente.tipoMedida.abreviacao}",
                modifier = Modifier.padding(end = 16.dp)
            )
        }
        if (itemLista != listaCompras.last()) {
            Divider()
        }
    }
}

@Composable
fun Divider() {
    Divider(
        color = Color.Gray,
        thickness = 1.dp,
        modifier = Modifier.padding(vertical = 0.dp, horizontal = 16.dp)
    )
}

//@Preview(showSystemUi = true)
//@Composable
//fun ListaComprasPreview() {
//    ListaComprasScreen(
//        navController = NavController(LocalContext.current),
//        viewModel = ListaComprasViewModel(),
//        onListaComprasClick = { },
//    )
//}
