package com.example.gourmet_inventory_mobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.model.EstoqueConsulta
import com.example.gourmet_inventory_mobile.model.Fornecedor
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_CianoClaro
import com.example.gourmet_inventory_mobile.ui.theme.GI_Laranja
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.utils.BottomBarGerente
import com.example.gourmet_inventory_mobile.utils.SearchBox
import com.example.gourmet_inventory_mobile.viewmodel.FornViewModel
import org.koin.compose.viewmodel.koinViewModel

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
            BottomBarGerente(navController = navController, onClick = onListaFornecedoresClick)
        }
    ) { padding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            val context = LocalContext.current

            var texto by remember { mutableStateOf("") }

            // Obtém o ViewModel do Koin
            val viewModel = koinViewModel<FornViewModel>()

            val listaFornecedores = viewModel.data
            val isLoading = viewModel.isLoading



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
                            text = "Fornecedores:",
                            fontSize = 34.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
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

                    // Exibe a lista de fornecedores filtrados
                    val fornecedoresFiltrados = listaFornecedores.filter {
                        fornecedor -> fornecedor.nomeFornecedor.contains(texto, ignoreCase = true)
                    }

                    if (isLoading){
                        CircularProgressIndicator()
                    } else{
                        ItensListaFornecedor(
                            fornecedores = fornecedoresFiltrados,
                            onListaFornecedoresClick = onListaFornecedoresClick
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListaFornecedoresPreview() {
    ListaFornecedoresScreen(
        navController = NavController(LocalContext.current),
        onListaFornecedoresClick = {},
    )
}
@Composable
fun ItensListaFornecedor(
    fornecedores: List<Fornecedor>,
    onListaFornecedoresClick: (String) -> Unit
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
            items(fornecedores) { fornecedor ->
                ItemListaFornecedor(
                    fornecedorItem = fornecedor,
                    onListaFornecedoresClick = onListaFornecedoresClick,
                )
            }
        }
    }
}

@Composable
fun ItemListaFornecedor(
    fornecedorItem: Fornecedor,
    onListaFornecedoresClick: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(2.dp, GI_Laranja, RoundedCornerShape(8.dp))
            .background(
                GI_AzulMarinho.copy(alpha = 0.2f),
                RoundedCornerShape(8.dp)
            )
            .clickable(onClick = {
                onListaFornecedoresClick("fornecedorView")
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
                        GI_Laranja,
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
                    text = fornecedorItem.nomeFornecedor,
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
                    text = "Telefone: ${fornecedorItem.telefone}",
                    fontSize = 16.sp
                )
                Text(
                    text = "Categoria: ${fornecedorItem.categoria}",
                    fontSize = 16.sp
                )
            }
        }
    }
}
