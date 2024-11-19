@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gourmet_inventory_mobile.screens.Cardapio

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.model.Prato
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_Laranja
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.viewmodel.ComandaViewModel

@Composable
fun PratoScreen(
    viewModel: ComandaViewModel,
    prato: Prato,
    navController: NavController,
    onClickPratoItem: (String) -> Unit,
    onPratoItemVoltarClick: (String) -> Unit,
) {
    // Lista de pratos adicionados à comanda

    // Acesso à lista de pratos adicionados à comanda
    val listaPratosComanda = viewModel.listaPratosComanda

    // Função para adicionar o prato atual à lista de comanda
    fun adicionarPratoNaComanda() {
        viewModel.adicionarPrato(prato)
        onClickPratoItem("cardapio")
        Log.d("PratoScreen", "Prato adicionado: ${listaPratosComanda.value.size} - $listaPratosComanda")
    }


    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var alergicosRestricoes by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var receitaPrato by remember { mutableStateOf("") }
    var foto by remember { mutableStateOf("") }
    var URLAssinada by remember { mutableStateOf("") }

    if (prato != null) {
        nome = prato.nome
        descricao = prato.descricao
        preco = prato.preco.toString()
        alergicosRestricoes = prato.alergicosRestricoes.toString()
        categoria = prato.categoria
        receitaPrato = prato.receitaPrato.toString()
        foto = prato.foto ?: ""
        URLAssinada = prato.URLAssinada ?: ""
    }

    Log.d("PratoScreen", "Prato: $listaPratosComanda")

    var contagemPratos by remember { mutableStateOf("1") }

    Scaffold(
        topBar = { FotoTop(onClickPratoItem, onPratoItemVoltarClick) }
    ) { padding ->
        Surface(
            color = Color.White,
            modifier = Modifier.fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Exibição dos dados do prato
                Text(
                    fontSize = 27.sp,
                    modifier = Modifier
                        .width(370.dp)
                        .padding(top = 20.dp),
                    text = nome,
                    fontFamily = JostBold
                )

                Text(
                    fontSize = 18.sp,
                    modifier = Modifier
                        .width(365.dp)
                        .padding(top = 10.dp),
                    text = descricao
                )

                Row(
                    modifier = Modifier
                        .width(370.dp)
                        .padding(top = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        fontSize = 22.sp,
                        modifier = Modifier
                            .width(330.dp),
                        text = "Adicionar Observação",

                        )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Voltar",
                        modifier = Modifier.size(45.dp)
                    )

                }

                Row(
                    modifier = Modifier
                        .width(300.dp)
                        .padding(top = 120.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.diminuir),
                        contentDescription = "Diminuir",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .clickable {
                                contagemPratos = if (contagemPratos.toInt() == 0) "0" else (contagemPratos.toInt() - 1).toString()
                            }
                    )
                    Text(
                        text = contagemPratos,
                        fontSize = 20.sp
                    )
                    Image(
                        painter = painterResource(id = R.drawable.aumentar),
                        contentDescription = "Aumentar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .clickable {
                                contagemPratos = (contagemPratos.toInt() + 1).toString()
                            }
                    )
                }

                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )

                Button(
                    onClick = { adicionarPratoNaComanda() },
                    modifier = Modifier
                        .width(150.dp)
                        .padding(top = 20.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(GI_Laranja),
                ) {
                    Text(
                        text = "ADICIONAR",
                        color = Black,
                        fontSize = 16.sp
                    )
                }


                Button(
                    onClick = { onClickPratoItem("cardapio") },
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(
                        text = "VOLTAR",
                        color = Black,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun PratoPreview() {
//    PratoScreen(
//        navController = NavController(LocalContext.current),
//        onClickPratoItem = { },
//        onPratoItemVoltarClick = { }
//    )
//}


@Composable
fun FotoTop(onClickPratoItem: (String) -> Unit, onPratoItemVoltarClick: (String) -> Unit) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.bro),
            contentDescription = "Prato",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        IconButton(
            onClick = { onPratoItemVoltarClick("Voltar") },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .size(50.dp)
        ) {
            Icon(
                modifier = Modifier
                    .background(color = White, shape = RoundedCornerShape(50))
                    .fillMaxSize()
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(50)),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Voltar",
            )
        }
    }
}