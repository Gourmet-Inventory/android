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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.model.Prato
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_BrancoFundo
import com.example.gourmet_inventory_mobile.ui.theme.GI_Verde
import com.example.gourmet_inventory_mobile.utils.DrawScrollableView
import com.example.gourmet_inventory_mobile.viewmodel.ComandaViewModel

@Composable
fun ComandaViewScreen(
    viewModel: ComandaViewModel,
    navController: NavController,
    onComandaViewClick: (String) -> Unit,
    onComandaViewVoltarClick: () -> Unit
) {
    val context = LocalContext.current
    val pedidos = viewModel.listaPratosComanda

    val total = pedidos.sumOf { it.preco }
//    var isSent by remember { mutableStateOf(false) } // Estado do status

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

                        ItensComanda(pedidos, viewModel, onComandaViewClick)

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
                            imageVector = Icons.Default.Send,
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
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cancelar"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Cancelar")
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                ) {
                    Box(modifier = Modifier
                        .size(24.dp)
//                        .background(
//                            if (isSent) Color.Red else Color.Gray,
//                            shape = RoundedCornerShape(12.dp)
//                        )
                        .clickable {
//                            isSent = !isSent
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

@Composable
fun ItensComanda(
    pedidos: List<Prato>,
    viewModel: ComandaViewModel,
    onComandaViewClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth()
    ) {
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
                            onComandaViewClick = onComandaViewClick
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
fun ItemComanda(pedido: Prato, onComandaViewClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${pedido.idPrato}x ${pedido.nome}",
            modifier = Modifier
                .clickable {
                    onComandaViewClick("cardapioItem")
                },
            textDecoration = TextDecoration.Underline
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Text(text = "R$${pedido.preco * 1},00")
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Deletar",
                tint = Black,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(30.dp)
//                    .clip(RoundedCornerShape(8.dp))
//                    .clickable {
//                        removerItemComanda(pedido, pedidios)
//                    }
            )
        }
    }
}

//fun removerItemComanda(pedido: Prato, pedidos: List<Prato>): List<Prato> {
//    val pedidosAtualizados = pedidos.toMutableList()
//    pedidosAtualizados.remove(pedido)
//    return pedidosAtualizados
//}