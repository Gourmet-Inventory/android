@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gourmet_inventory_mobile.screens

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_Orange
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White

@Composable
fun PratoScreen(navController: NavController, onClickPratoItem: (String) -> Unit, onPratoItemVoltarClick: (String) -> Unit) {
    var contagemPratos by remember {
        mutableStateOf("1")
    }

    Scaffold(
        topBar = { FotoTop(onClickPratoItem, onPratoItemVoltarClick) }
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            val context = LocalContext.current
            var selectedOptionIndex by remember { mutableStateOf(-1) }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    fontSize = 27.sp,
                    modifier = Modifier
                        .width(370.dp)
                        .padding(top = 20.dp),
                    text = "X-Burguer",
                    fontFamily = JostBold
                )

                Text(
                    fontSize = 18.sp,
                    modifier = Modifier
                        .width(365.dp)
                        .padding(top = 10.dp),
                    text = "É um prato comum em lanchonetes e fast foods, apreciado pela combinação" +
                            " harmoniosa de sabores e pela facilidade de preparo."
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
                    onClick = { onClickPratoItem("cardapio") },
                    modifier = Modifier
                        .width(150.dp)
                        .padding(top = 20.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(GI_Orange),
                ) {
                    Text(
                        text = "FINALIZAR",
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


@Preview
@Composable
fun PratoPreview() {
    PratoScreen(
        navController = NavController(LocalContext.current),
        onClickPratoItem = { },
        onPratoItemVoltarClick = { }
    )
}


@Composable
fun FotoTop(onClickPratoItem: (String) -> Unit, onPratoItemVoltarClick: (String) -> Unit) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.prato),
            contentDescription = "Prato",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
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