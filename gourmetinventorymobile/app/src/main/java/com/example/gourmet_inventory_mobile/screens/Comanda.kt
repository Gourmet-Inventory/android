package com.example.gourmet_inventory_mobile.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_BrancoFundo
import com.example.gourmet_inventory_mobile.ui.theme.GI_Verde
import com.example.gourmet_inventory_mobile.utils.DrawScrollableView

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
    val pedidos = remember {
        mutableListOf(
            Pedido("Hambúrguer", 2, 10.00),
            Pedido("Esfirra", 1, 19.50),
            Pedido("Coca-Cola", 2, 20.00),
            Pedido("Hambúrguer2", 2, 12.00),
            Pedido("Hambúrguer3", 2, 18.00),
            Pedido("Hambúrguer4", 2, 10.00),
            Pedido("Hambúrguer5", 2, 18.00),
            Pedido("Hambúrguer6", 2, 18.00),
            Pedido("Hambúrguer7", 2, 12.00),
            Pedido("Hambúrguer8", 2, 12.00),
            Pedido("Hambúrguer9", 2, 12.00)
        )
    }

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
                    onClick = { onComandaViewVoltarClick() }, modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Voltar",
                    )
                }
            }
        }) { padding ->
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Container para o botão de voltar e os textos


                // Alinhando os textos
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp, start = 16.dp, bottom = 10.dp, top = 20.dp)
                ) {
                    Text(text = "Comanda 03", fontSize = 34.sp)
                    Text(text = "Mesa 1", fontSize = 22.sp)
                }


                Spacer(modifier = Modifier.height(16.dp))

//            Text(text = "Pedidos", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))

                // Lista de Pedidos
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(500.dp)
                        .background(GI_BrancoFundo, RoundedCornerShape(8.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = GI_AzulMarinho, contentColor = Color.White
                                ),
                                onClick = {}
                            ) {
                                Text(text = "Adicionar pedido")
                            }
                        }

                        Divider(
                            color = Black,
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )

                        ItensComanda(pedidos, onComandaViewClick = onComandaViewClick)

                        Divider(
                            color = Black,
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
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
                Spacer(modifier = Modifier.height(20.dp))

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
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GI_Verde, contentColor = Color.White
                        ),
                        modifier = Modifier
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
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red, contentColor = Color.White
                        ),
                        modifier = Modifier
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
    ComandaViewScreen(onComandaViewVoltarClick = { },
        navController = NavController(LocalContext.current),
        onComandaViewClick = { })
}

@Composable
fun ItensComanda(pedidos: List<Pedido>, onComandaViewClick: (String) -> Unit) {

    Box(
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth()
    ) {
//        val listScrollState = rememberLazyListState()
//
//        DrawScrollableView(
//            content = {
//                LazyColumn(
//                    state = listScrollState,
//                    modifier = Modifier
//                        .height(345.dp)
//                        .width(325.dp)
//                        .padding(top = 3.dp, start = 8.dp, end = 0.dp, bottom = 3.dp),
////                .verticalScroll(rememberScrollState(), true),
//                    verticalArrangement = Arrangement.Top
//                ) {
//                    items(pedidos) { pedido ->
//                        ItemComanda(
//                            pedido = pedido,
//                            onComandaViewClick = onComandaViewClick,
//                            pedidios = pedidos
//                        )
//                    }
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight()
//        )
        DrawScrollableView(
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 3.dp, start = 8.dp, end = 8.dp, bottom = 3.dp)
                ) {
                    pedidos.forEach { pedido ->
                        ItemComanda(
                            pedido = pedido,
                            onComandaViewClick = onComandaViewClick,
                            pedidios = pedidos
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}

@Composable
fun ItemComanda(pedido: Pedido, onComandaViewClick: (String) -> Unit, pedidios: List<Pedido>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${pedido.quantidade}x ${pedido.nome}",
            modifier = Modifier
                .semantics { }
                .clickable {
                    onComandaViewClick("cardapioItem")
                },
            textDecoration = TextDecoration.Underline
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(end = 8.dp)
        ) {
            Text(text = "R$${pedido.preco * pedido.quantidade},00")
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Deletar",
                tint = Black,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(30.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        removerItemComanda(pedido, pedidios)
                    }
            )
        }
    }
}

fun removerItemComanda(pedido: Pedido, pedidos: List<Pedido>): List<Pedido> {
    val pedidosAtualizados = pedidos.toMutableList()
    pedidosAtualizados.remove(pedido)
    return pedidosAtualizados
}