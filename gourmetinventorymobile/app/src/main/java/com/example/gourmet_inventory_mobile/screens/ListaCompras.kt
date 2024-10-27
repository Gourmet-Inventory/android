package com.example.gourmet_inventory_mobile.screens

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_BrancoFundo
import com.example.gourmet_inventory_mobile.ui.theme.GI_CianoClaro
import com.example.gourmet_inventory_mobile.ui.theme.GI_Laranja
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.utils.BottomBarGerente
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
import kotlinx.coroutines.flow.first

//class ListaComprasActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            GourmetinventorymobileTheme {
//                ListaCompras()
//            }
//        }
//    }
//}

@Composable
fun ListaComprasScreen(
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
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(
                    onClick = {
                        Log.d("ListaEstoqueScreen", "currentUser: ${currentUser}")

                        if (currentUser?.cargo == context.resources.getString(R.string.gerente)) {
                            onListaComprasClick("perfil")
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
            BottomBarGerente(navController = navController, onClick = onListaComprasClick)
        }
    ){ padding ->

        Surface(modifier = Modifier.fillMaxSize()) {
            val context = LocalContext.current

            // Estado para o texto do campo de pesquisa
            var texto by remember { mutableStateOf("") }

            // Lista de itens
            val items = listOf(
                "Molho de tomate",
                "Molho de XYZ",
                "Tomate",
                "Azeite de oliva",
                "Azeite",
                "Sal grosso",
                "Sal",
                "Cebolinha",
                "Pimenta",
                "Cebola"
            )

            // Estado da checkbox
            val checkedState = remember { mutableStateListOf(*Array(items.size) { false }) }

            // Filtra a lista com base no texto da pesquisa
            val filteredItems = items.filter { it.contains(texto, ignoreCase = true) }

            Box(modifier = Modifier.fillMaxSize().padding(top = 40.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Título
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 25.dp),
                        horizontalArrangement = Arrangement.Center,
//                    verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Lista de Compras",
                            fontSize = 30.sp,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                    }

                    ListaDeComprasearSchBox(texto) { novoTexto ->
                        texto = novoTexto
                    }

                    // Cabeçalho da Lista
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

                    // Lista de Itens Filtrada
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 70.dp)
                            .background(GI_BrancoFundo, RoundedCornerShape(8.dp))
                    ) {
                        items(filteredItems.indices.toList()) { index ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp)
                                    .background(if (checkedState[index]) Color.LightGray else Color.Transparent),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Checkbox(
                                    checked = checkedState[index], // Estado da checkbox
                                    onCheckedChange = { isChecked ->
                                        checkedState[index] = isChecked // Atualiza o estado
                                    },
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = filteredItems[index]
//                                textDecoration = if (checkedState[index]) TextDecoration.LineThrough else TextDecoration.None
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    text = "20Kg", modifier = Modifier.padding(end = 16.dp)
//                                textDecoration = if (checkedState[index]) TextDecoration.LineThrough else TextDecoration.None
                                )
                            }
                            Divider(
                                color = Color.Gray,
                                thickness = 1.dp,
                                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                            )
                        }
                    }
//                Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun ListaDeComprasearSchBox(searchText: String, mudaValorCampo: (String) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = { novoValorCampo: String ->
            mudaValorCampo(novoValorCampo)
        },
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
//                    fontFamily =  JostLight
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
fun ListaComprasPreview() {
    ListaComprasScreen(
        navController = NavController(LocalContext.current),
        onListaComprasClick = { },
    )
}