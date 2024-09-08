package com.example.gourmet_inventory_mobile.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import com.example.gourmet_inventory_mobile.ui.theme.GourmetinventorymobileTheme

class ListaEstoqueActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GourmetinventorymobileTheme {
                ListaEstoque()

            }
        }
    }
}

@Composable
fun ListaEstoque() {
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
            Column(modifier = Modifier
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
                            Toast.makeText(context, "Mudar Perfil", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = GI_Orange, contentColor = White),
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(text = "Mudar Perfil", color = Black)
                    }
                }

                // Título
                Text(text = "Estoque:", fontSize = 34.sp, modifier = Modifier.padding(bottom = 16.dp))

                // Campo de Pesquisa
                TextField(
                    value = texto,
                    onValueChange = { novoTexto -> texto = novoTexto },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
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
                                .background(GI_AzulMarinho.copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f).padding(8.dp)) {
                                Text(text = fornecedor.first, fontSize = 20.sp)
                                Text(text = "Data de Aviso: ${fornecedor.second.first}", fontSize = 14.sp)
                                Text(text = "Quantidade: ${fornecedor.second.second}", fontSize = 14.sp)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
            }

//            // Barra Inferior Fixa
//            Column(modifier = Modifier.align(Alignment.BottomCenter)) {
//                ListaFornecedorDownBar()
//            }
        }
    }
}

//@Composable
//fun ListaFornecedorDownBar() {
//    val context = LocalContext.current
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(color = GI_AzulMarinho)
//            .heightIn(75.dp),
//        horizontalArrangement = Arrangement.SpaceAround,
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.notes_icon),
//            contentDescription = "Ação 1",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(35.dp)
//                .clickable {
//                    Toast
//                        .makeText(context, "Ação 1", Toast.LENGTH_SHORT)
//                        .show()
//                }
//        )
//        Image(
//            painter = painterResource(id = R.drawable.book_icon),
//            contentDescription = "Ação 2",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(35.dp)
//                .clickable {
//                    Toast
//                        .makeText(context, "Ação 2", Toast.LENGTH_SHORT)
//                        .show()
//                }
//        )
//        Image(
//            painter = painterResource(id = R.drawable.account_icon),
//            contentDescription = "Ação 3",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(35.dp)
//                .clickable {
//                    Toast
//                        .makeText(context, "Ação 3", Toast.LENGTH_SHORT)
//                        .show()
//                }
//        )
//    }
//}

@Preview
@Composable
fun ListaEstoquePreview() {
    ListaEstoque()
}