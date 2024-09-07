package com.example.gourmet_inventory_mobile

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho

class ComandaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Comanda()
        }
    }
}

@Composable
fun Comanda() {
    val context = LocalContext.current
    val pedidos = listOf(
        Pedido("Hambúrguer", 2, 128.00),
        Pedido("Esfirra", 1, 19.50),
        Pedido("Coca-Cola", 2, 40.00),
        Pedido("Hambúrguer", 2, 128.00)
    )

    val total = pedidos.sumOf { it.preco * it.quantidade }
    var isSent by remember { mutableStateOf(false) } // Estado do status

    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Container para o botão de voltar e os textos
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        Toast.makeText(context, "Voltar", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Voltar",
                            Modifier.size(44.dp),
                            tint = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }

                // Alinhando os textos
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Text(text = "Comanda 03", fontSize = 34.sp)
                    Text(text = "Mesa 1", fontSize = 22.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Pedidos", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))

            // Lista de Pedidos
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(250.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                items(pedidos) { pedido ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "${pedido.quantidade}x ${pedido.nome}")
                        Text(text = "R$${pedido.preco * pedido.quantidade},00")
                    }
                }

                // Linha divisória
                item {
                    Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
                }

                // Total
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Total:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = "R$$total,00", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(46.dp))

            // Botões de Ação
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        Toast.makeText(context, "Comanda cancelada", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text(text = "Cancelar")
                }

                Button(
                    onClick = {
                        Toast.makeText(context, "Comanda enviada", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500), contentColor = Color.White),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text(text = "Enviar")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Status com círculo interativo
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(6.dp)) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(if (isSent) Color.Red else Color.Gray, shape = RoundedCornerShape(12.dp))
                        .clickable {
                            isSent = !isSent // Alterna o estado
                        }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Status: Mandar comanda para cozinha", fontSize = 14.sp, color = Color.Gray)
            }
        }
    }

    // Barra Inferior Fixa
    Box(modifier = Modifier.fillMaxSize()) {
        ComandaDownBar(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

data class Pedido(val nome: String, val quantidade: Int, val preco: Double)

@Composable
fun ComandaDownBar(modifier: Modifier = Modifier) {
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
            painter = painterResource(id = R.drawable.db1),
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
            painter = painterResource(id = R.drawable.db2),
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

@Preview(showBackground = true)
@Composable
fun ComandaPreview() {
    Comanda()
}