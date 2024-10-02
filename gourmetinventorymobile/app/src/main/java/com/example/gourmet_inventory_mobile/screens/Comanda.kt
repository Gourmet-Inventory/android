package com.example.gourmet_inventory_mobile.screens

import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_BrancoFundo
import com.example.gourmet_inventory_mobile.ui.theme.GI_Orange
import com.example.gourmet_inventory_mobile.ui.theme.GI_Verde
import com.example.gourmet_inventory_mobile.utils.BottomBarGarcom

//class ComandaActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            ComandaScreen()
//        }
//    }
//}

@Composable
fun ComandaViewScreen(
    navController: NavController,
    onComandaViewClick: (String) -> Unit,
    onComandaViewVoltarClick: () -> Unit
) {
    val context = LocalContext.current
    val pedidos = listOf(
        Pedido("Hambúrguer", 2, 128.00),
        Pedido("Esfirra", 1, 19.50),
        Pedido("Coca-Cola", 2, 40.00),
        Pedido("Hambúrguer", 2, 128.00)
    )

    val total = pedidos.sumOf { it.preco * it.quantidade }
    var isSent by remember { mutableStateOf(false) } // Estado do status

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { onComandaViewVoltarClick() },
                    modifier = Modifier
                        .size(50.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize(),
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Voltar",
                    )
                }
            }
        }
    ) { padding ->
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                // Container para o botão de voltar e os textos
                Column(modifier = Modifier.fillMaxWidth()){

                    // Alinhando os textos
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(text = "Comanda 03", fontSize = 34.sp)
                        Text(text = "Mesa 1", fontSize = 22.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

//            Text(text = "Pedidos", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))

                // Lista de Pedidos
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .background(GI_BrancoFundo, RoundedCornerShape(8.dp))
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(250.dp)
                            .background(GI_BrancoFundo, RoundedCornerShape(8.dp))
                            .padding(25.dp)
                    ) {
                        item(content = {
                            Text(
                                text = "Pedido",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Divider(
                                color = Color.Gray,
                                thickness = 1.dp,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                        )

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
                            Divider(
                                color = Color.Gray,
                                thickness = 1.dp,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }

                        // Total
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Total:",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "R$$total,00",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }


                Spacer(modifier = Modifier.height(46.dp))

                // Botões de Ação
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            onComandaViewClick("comandaList")
                            Toast.makeText(context, "Comanda enviada", Toast.LENGTH_SHORT).show()
                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = GI_Verde, contentColor = Color.White
                        ), modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                            .height(45.dp),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Send,
                            contentDescription = "Enviar"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Enviar")
                    }
                    Button(
                        onClick = {
                            onComandaViewClick("comandaList")
                            Toast.makeText(context, "Comanda cancelada", Toast.LENGTH_SHORT).show()
                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red, contentColor = Color.White
                        ), modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                            .height(45.dp),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Close,
                            contentDescription = "Cancelar"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Cancelar")
                    }
                }

//            Spacer(modifier = Modifier.height(1.dp))

                // Status com círculo interativo
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                ) {
                    Box(modifier = Modifier
                        .size(24.dp)
                        .background(
                            if (isSent) Color.Red else Color.Gray,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            isSent = !isSent // Alterna o estado
                        })
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Status: Mandar comanda para cozinha",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }

    }

}

data class Pedido(val nome: String, val quantidade: Int, val preco: Double)

@Composable
fun ComandaDownBar(
    modifier: Modifier = Modifier,
    onComandaViewAcao1Click: () -> Unit,
    onComandaViewAcao2Click: () -> Unit,
    onComandaViewAcao3Click: () -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = GI_AzulMarinho)
            .heightIn(70.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(painter = painterResource(id = R.drawable.notes_icon),
            contentDescription = "Ação 1",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(30.dp)
                .clickable {
                    onComandaViewAcao1Click()
                })
        Image(painter = painterResource(id = R.drawable.book_icon),
            contentDescription = "Ação 2",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(30.dp)
                .clickable {
                    onComandaViewAcao2Click()
                })
        Image(painter = painterResource(id = R.drawable.account_icon),
            contentDescription = "Ação 3",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(35.dp)
                .clickable {
                    onComandaViewAcao3Click()
                })
    }
}

@Preview(showBackground = true)
@Composable
fun ComandaPreview() {
    ComandaViewScreen(
        onComandaViewVoltarClick = { },
        navController = NavController(LocalContext.current),
        onComandaViewClick = { }
    )
}