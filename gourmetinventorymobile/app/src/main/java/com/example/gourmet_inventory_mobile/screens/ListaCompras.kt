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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_Orange
import com.example.gourmet_inventory_mobile.ui.theme.White
import androidx.compose.ui.Alignment
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.ui.theme.GI_BrancoFundo
import com.example.gourmet_inventory_mobile.ui.theme.GI_CianoClaro
import com.example.gourmet_inventory_mobile.ui.theme.JostBold

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
    onListaComprasMudarPerfilClick: () -> Unit,
    onListaComprasAcao1Click: () -> Unit,
    onListaComprasAcao2Click: () -> Unit,
    onListaComprasAcao3Click: () -> Unit,
    onListaComprasAcao4Click: () -> Unit
) {
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

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Botão Mudar Perfil
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.End
                ) {

                    OutlinedButton(
                        onClick = {
                            onListaComprasMudarPerfilClick()
                        },
                        modifier = Modifier
                            .width(170.dp)
                            .height(40.dp)
                            .padding(end = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GI_Orange,
                            contentColor = White
                        )
                    ) {
                        Text(
                            text = "Mudar Perfil",
                            color = Black,
                            fontSize = 18.sp
                        )
                    }
//                    Button(
//                        onClick = {
//                            onListaComprasMudarPerfilClick()
//                        },
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = GI_Orange,
//                            contentColor = White
//                        ),
//                        modifier = Modifier.padding(top = 16.dp, bottom = 20.dp)
//                    ) {
//                        Text(text = "Mudar Perfil", color = Black)
//                    }
                }

                // Título
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 25.dp),
                    horizontalArrangement = Arrangement.Center,
//                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Lista de Compras",
                        fontSize = 34.sp,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                }

//                // Campo de Pesquisa
//                TextField(
//                    value = texto,
//                    onValueChange = { novoTexto -> texto = novoTexto },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 16.dp)
//                        .background(GI_CianoClaro, RoundedCornerShape(8.dp)),
//                    label = { Text("Pesquisar") },
//                    placeholder = { Text("") },
//                    shape = RoundedCornerShape(8.dp)
//                )

                ListaDeComprasearSchBox(texto) { novoTexto ->
                    texto = novoTexto
                }

                // Cabeçalho da Lista
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Item", fontSize = 20.sp)
                    Divider(
                        color = Color.Black,
                        modifier = Modifier
                            .width(1.dp)
                            .height(20.dp)
                    )
                    Text(text = "Qt. Média", fontSize = 20.sp)
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
                                .padding(vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Checkbox(
                                checked = checkedState[index], // Estado da checkbox
                                onCheckedChange = { isChecked ->
                                    checkedState[index] = isChecked // Atualiza o estado
                                },
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = filteredItems[index])
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text = "20Kg", modifier = Modifier.padding(end = 16.dp))
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

            // Barra Inferior Fixa
            Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                ListaComprasDownBar(
                    onListaComprasAcao1Click = onListaComprasAcao1Click,
                    onListaComprasAcao2Click = onListaComprasAcao2Click,
                    onListaComprasAcao3Click = onListaComprasAcao3Click,
                    onListaComprasAcao4Click = onListaComprasAcao4Click
                )
            }
        }
    }
}

@Composable
fun ListaComprasDownBar(
    onListaComprasAcao1Click: () -> Unit = {},
    onListaComprasAcao2Click: () -> Unit = {},
    onListaComprasAcao3Click: () -> Unit = {},
    onListaComprasAcao4Click: () -> Unit = {}
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GI_AzulMarinho)
            .heightIn(70.dp),
//        horizontalArrangement = Arrangement.SpaceEvenly,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.fornecedores_db),
            contentDescription = "Ação 1",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(30.dp)
                .clickable {
                    onListaComprasAcao1Click()
                }
        )
//        Spacer(modifier = Modifier.height(60.dp))
        Image(
            painter = painterResource(id = R.drawable.opened_box),
            contentDescription = "Ação 2",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(30.dp)
                .clickable {
                    onListaComprasAcao2Click()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.cart),
            contentDescription = "Ação 3",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(30.dp)
                .clickable {
                    onListaComprasAcao3Click()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.account_icon),
            contentDescription = "Ação 4",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(35.dp)
                .clickable {
                    onListaComprasAcao4Click()
                }
        )
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
        onListaComprasMudarPerfilClick = {},
        onListaComprasAcao1Click = {},
        onListaComprasAcao2Click = {},
        onListaComprasAcao3Click = {},
        onListaComprasAcao4Click = {}
    )
}