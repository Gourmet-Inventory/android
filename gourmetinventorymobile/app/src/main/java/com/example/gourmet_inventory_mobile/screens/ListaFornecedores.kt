package com.example.gourmet_inventory_mobile.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.ui.theme.GourmetinventorymobileTheme

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
    onListaFornecedorClickMudarPerfil: () -> Unit,
    onListaFornecedorClickAcao1: () -> Unit,
    onListaFornecedorClickAcao2: () -> Unit,
    onListaFornecedorClickAcao3: () -> Unit,
    onListaFornecedorClickAcao4: () -> Unit
) {
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
                    .padding(16.dp)
            ) {
                // Botão Mudar Perfil
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            onListaFornecedorClickMudarPerfil()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GI_Orange,
                            contentColor = White
                        ),
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(text = "Mudar Perfil", color = Black)
                    }
                }

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
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(filteredFornecedores) { fornecedor ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .background(GI_Orange.copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)) {
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

            // Barra Inferior Fixa
            Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                ListaFornecedorDownBar(
                    onListaFornecedorClickAcao1 = onListaFornecedorClickAcao1,
                    onListaFornecedorClickAcao2 = onListaFornecedorClickAcao2,
                    onListaFornecedorClickAcao3 = onListaFornecedorClickAcao3,
                    onListaFornecedorClickAcao4 = onListaFornecedorClickAcao4
                )
            }
        }
    }
}

@Composable
fun ListaFornecedorDownBar(
    onListaFornecedorClickAcao1: () -> Unit,
    onListaFornecedorClickAcao2: () -> Unit,
    onListaFornecedorClickAcao3: () -> Unit,
    onListaFornecedorClickAcao4: () -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GI_AzulMarinho)
            .heightIn(70.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.fornecedores_db),
            contentDescription = "Ação 1",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(35.dp)
                .clickable {
                    onListaFornecedorClickAcao1()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.opened_box),
            contentDescription = "Ação 2",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(35.dp)
                .clickable {
                    onListaFornecedorClickAcao2()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.carrinho_de_compraspng),
            contentDescription = "Ação 3",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(35.dp)
                .clickable {
                    onListaFornecedorClickAcao3()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.account_icon),
            contentDescription = "Ação 3",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(35.dp)
                .clickable {
                    onListaFornecedorClickAcao4()
                }
        )
    }
}

@Preview
@Composable
fun ListaFornecedoresPreview() {
    ListaFornecedoresScreen(
        onListaFornecedorClickMudarPerfil = {},
        onListaFornecedorClickAcao1 = {},
        onListaFornecedorClickAcao2 = {},
        onListaFornecedorClickAcao3 = {},
        onListaFornecedorClickAcao4 = {}
    )
}