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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GourmetinventorymobileTheme
import com.example.gourmet_inventory_mobile.ui.theme.GI_Orange
import com.example.gourmet_inventory_mobile.ui.theme.White

class ListaCardapioActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GourmetinventorymobileTheme {
                ListaCardapio()
            }
        }
    }
}

@Composable
fun ListaCardapio() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current

        var texto by remember { mutableStateOf("") }

        // Exemplo de itens
        val items = listOf(
            Item("X-Burguer", "Serve até 3 pessoas", 29.99, R.drawable.xburguer),
            Item("X-Salada", "Serve até 2 pessoas", 34.99, R.drawable.xburguer),
            Item("X-Bacon", "Serve até 3 pessoas", 39.99, R.drawable.xburguer)
        )

        // Filtra a lista com base no texto da pesquisa
        val filteredItems = items.filter { it.name.contains(texto, ignoreCase = true) }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

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
            Text(text = "Cardápio:", fontSize = 34.sp, modifier = Modifier.padding(bottom = 16.dp))

            // Campo de Pesquisa
            TextField(
                value = texto,
                onValueChange = { novoTexto -> texto = novoTexto },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                label = { Text("Pesquisar") },
                shape = RoundedCornerShape(8.dp)
            )

            // Lista de Itens Filtrada
            LazyColumn {
                items(filteredItems) { item ->
                    ItemCard(item)
                }
            }
        }

        // Barra Inferior Fixa
        Box(modifier = Modifier.fillMaxSize()) {
            ListaCardapioDownBar(
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun ItemCard(item: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(125.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
            ) {
                Text(text = item.name, fontSize = 20.sp, modifier = Modifier.padding(bottom = 10.dp))
                Text(text = item.description, fontSize = 16.sp)
                Text(text = "R$${item.price}", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun ListaCardapioDownBar(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Row(
        modifier = modifier
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

data class Item(val name: String, val description: String, val price: Double, val imageRes: Int)

@Preview(showBackground = true)
@Composable
fun ListaCardapioPreview() {
    ListaCardapio()
}