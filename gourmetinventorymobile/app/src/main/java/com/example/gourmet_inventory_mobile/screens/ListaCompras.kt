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
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.gourmet_inventory_mobile.R

class ListaComprasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GourmetinventorymobileTheme {
                ListaCompras()
            }
        }
    }
}

class GourmetinventorymobileTheme(function: @Composable () -> Unit) {
}

@Composable
fun ListaCompras() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current

        // Estado para o texto do campo de pesquisa
        var texto by remember { mutableStateOf("") }

        // Lista de itens
        val items = listOf("Molho de tomate", "Molho de XYZ", "Tomate", "Azeite de oliva", "Azeite", "Sal grosso", "Sal", "Cebolinha", "Pimenta", "Cebola")

        // Estado da checkbox
        val checkedState = remember { mutableStateListOf(*Array(items.size) { false }) }

        // Filtra a lista com base no texto da pesquisa
        val filteredItems = items.filter { it.contains(texto, ignoreCase = true) }

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
                Text(text = "Lista de Compras", fontSize = 34.sp, modifier = Modifier.padding(bottom = 16.dp))

                // Campo de Pesquisa
                TextField(
                    value = texto,
                    onValueChange = { novoTexto -> texto = novoTexto },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                    label = { Text("Pesquisar") },
                    placeholder = { Text("") },
                    shape = RoundedCornerShape(8.dp)
                )

                // Cabeçalho da Lista
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Item", fontSize = 16.sp)
                    Text(text = "Qt. Média", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Lista de Itens Filtrada
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(filteredItems.indices.toList()) { index ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically // Alinha verticalmente
                        ) {
                            Checkbox(
                                checked = checkedState[index], // Estado da checkbox
                                onCheckedChange = { isChecked ->
                                    checkedState[index] = isChecked // Atualiza o estado
                                }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = filteredItems[index], modifier = Modifier.weight(1f))
                            Text(text = "20Kg")
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
            }

            // Barra Inferior Fixa
            Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                ListaComprasDownBar()
            }
        }
    }
}

@Composable
fun ListaComprasDownBar() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GI_AzulMarinho)
            .heightIn(75.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.notes_icon),
            contentDescription = "Ação 1",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(35.dp)
                .clickable {
                    Toast
                        .makeText(context, "Ação 1", Toast.LENGTH_SHORT)
                        .show()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.book_icon),
            contentDescription = "Ação 2",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(35.dp)
                .clickable {
                    Toast
                        .makeText(context, "Ação 2", Toast.LENGTH_SHORT)
                        .show()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.account_icon),
            contentDescription = "Ação 3",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(35.dp)
                .clickable {
                    Toast
                        .makeText(context, "Ação 3", Toast.LENGTH_SHORT)
                        .show()
                }
        )
    }
}

@Preview
@Composable
fun ListaComprasPreview() {
    ListaCompras()
}