package com.example.gourmet_inventory_mobile.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.model.User
import com.example.gourmet_inventory_mobile.ui.theme.*
import com.example.gourmet_inventory_mobile.utils.BottomBarGerente
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
import com.example.gourmet_inventory_mobile.viewmodel.ListaComprasViewModel
import kotlinx.coroutines.flow.first
import org.koin.compose.viewmodel.koinViewModel

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
                            Toast.makeText(context, "Acesso restrito a Gerentes", Toast.LENGTH_SHORT).show()
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

        Surface(modifier = Modifier.fillMaxSize()) {
            var texto by remember { mutableStateOf("") }
//            val viewModel = koinViewModel<ListaComprasViewModel>()
            val listaCompras = viewModel.data
            val isLoading = viewModel.isLoading

            Log.d("ListaComprasScreen", "listaCompras: $listaCompras")

            val checkedState = remember { mutableStateListOf(*Array(listaCompras.size) { false }) }

            val filteredItems = listaCompras.filter { item ->
                item.nome.contains(texto, ignoreCase = true)
            }

            Box(modifier = Modifier.fillMaxSize().padding(top = 40.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
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

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 70.dp)
                            .background(GI_BrancoFundo, RoundedCornerShape(8.dp))
                    ) {
                        items(filteredItems.indices.toList()) { index ->
                            val item = filteredItems[index]
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp)
                                    .background(if (checkedState[index]) Color.LightGray else Color.Transparent),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Checkbox(
                                    checked = checkedState[index],
                                    onCheckedChange = { isChecked ->
                                        checkedState[index] = isChecked
                                    },
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = item.nome)
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = item.qtdMedia.toString(), // Exibe a quantidade
                                    modifier = Modifier.padding(end = 16.dp)
                                )
                            }
                            Divider(
                                color = Color.Gray,
                                thickness = 1.dp,
                                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListaComprasSearchBox(searchText: String, onSearchTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onSearchTextChanged,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
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

//@Preview
//@Composable
//fun ListaComprasPreview() {
//    ListaComprasScreen(
//        navController = NavController(LocalContext.current),
//        viewModel = ListaComprasViewModel(),
//        onListaComprasClick = { },
//    )
//}
