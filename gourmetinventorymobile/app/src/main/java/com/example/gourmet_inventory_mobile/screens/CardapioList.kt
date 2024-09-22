@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gourmet_inventory_mobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.model.Prato
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_CianoClaro
import com.example.gourmet_inventory_mobile.ui.theme.GI_Orange
import com.example.gourmet_inventory_mobile.ui.theme.GourmetinventorymobileTheme
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.utils.BottomBarGarcom

@Composable
fun CardapioListScreen(
    navController: NavController,
    onCardapioClick: (String) -> Unit
) {
    Scaffold (
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton (
                    onClick = {
                        onCardapioClick("perfil")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GI_Orange,
                        contentColor = White
                    ),
                    modifier = Modifier.padding(top = 16.dp, end = 16.dp)
                ) {
                    Text(text = "Mudar Perfil", color = Black)
                }
            }
        },
        bottomBar = {
            BottomBarGarcom(navController = navController, onClick = onCardapioClick)
        }
    ){ padding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            val context = LocalContext.current
            var searchText by remember { mutableStateOf("") }
            var selectedOptionIndex by remember { mutableStateOf(-1) }

            val pratos = listOf(
                Prato("Feijoada", 25.0, "4 pessoas"),
                Prato("Lasanha", 30.0, "6 pessoas"),
                Prato("Moqueca", 35.0, "5 pessoas"),
                Prato("Strogonoff", 28.0, "4 pessoas"),
                Prato("Churrasco", 50.0, "10 pessoas"),
                Prato("Pizza", 20.0, "3 pessoas"),
                Prato("Sushi", 40.0, "2 pessoas")
            )

            // Filtra a lista com base no texto da pesquisa
            val filteredCardapio = pratos.filter {
                it.nome.contains(searchText, ignoreCase = true) ||
                        try {
                            it.preco == searchText.toDouble()
                        } catch (e: NumberFormatException) {
                            false
                        } ||
                        it.rendimento.contains(searchText, ignoreCase = true)
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp)
            ) {
                Text(
                    text = "Cardápio: ",
                    modifier = Modifier
                        .padding(start = 26.dp, top = 35.dp),
                    style = TextStyle(
                        fontSize = 30.sp,
                        color = Black
                    )
                )

                // Campo de Pesquisa
                CardapioSearchBox(
                    searchText = searchText,
                    mudaValorCampo = { novoValorCampo: String ->
                        searchText = novoValorCampo
                    }
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 26.dp, end = 26.dp, bottom = 70.dp)
                ) {
                    items(filteredCardapio) { prato ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable {
                                    onCardapioClick("cardapioItem")
                                }
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                            ) {
                                Text(
                                    text = prato.nome,
                                    fontSize = 20.sp,
                                    style = TextStyle(
                                        fontFamily = JostBold,
                                        color = Black
                                    ),
                                    modifier = Modifier
                                        .padding(start = 8.dp),
                                    textAlign = TextAlign.Start
                                )
                                Text(
                                    text = prato.rendimento,
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(start = 8.dp),
                                    textAlign = TextAlign.Start
                                )
                                Text(
                                    text = "R$" + prato.preco.toString(),
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(start = 8.dp, top = 14.dp),
                                    textAlign = TextAlign.Start
                                )

                            }
                            Image(
                                painter = painterResource(id = R.drawable.prato_exemplo),
                                contentDescription = "Imagem do prato",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(100.dp)
                            )
                        }
                        Divider(
                            color = Color.Black,
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CardapioListPreview() {
    GourmetinventorymobileTheme {
        CardapioListScreen(
            navController = NavController(LocalContext.current),
            onCardapioClick = {}
        )
    }
}

@Composable
fun CardapioSearchBox(searchText: String, mudaValorCampo: (String) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = { novoValorCampo: String ->
            mudaValorCampo(novoValorCampo)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(16.dp)
            .background(color = GI_CianoClaro, shape = RoundedCornerShape(5.dp)),
        placeholder = {
            Text(
                text = "Pesquisar",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Black,
//                    fontFamily =  JostLight
                ),
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Ícone de pesquisa"
            )
        },
        shape = RoundedCornerShape(5.dp),
    )
}

@Preview
@Composable
fun CardapioSearchBoxPreview() {
    SearchBox(searchText = "", mudaValorCampo = {})
}

//@Composable
//fun CardapioDownBar(
//    onCardapioClick: (String) -> Unit
//) {
//    val context = LocalContext.current
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(color = GI_AzulMarinho)
//            .heightIn(70.dp),
////        horizontalArrangement = Arrangement.SpaceEvenly,
//        horizontalArrangement = Arrangement.SpaceAround,
//        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.notes_icon),
//            contentDescription = "Ação 1",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(30.dp)
//                .clickable {
//                    onCardapioClick("comandaList")
//                }
//        )
////        Spacer(modifier = Modifier.height(60.dp))
//        Image(
//            painter = painterResource(id = R.drawable.book_icon),
//            contentDescription = "Ação 2",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(30.dp)
//                .clickable {
//                    onCardapioClick("cardapio")
//                }
//        )
//        Image(
//            painter = painterResource(id = R.drawable.account_icon),
//            contentDescription = "Ação 3",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(35.dp)
//                .clickable {
//                    onCardapioClick("viewPerfil")
//                }
//        )
//    }
//}
//
//@Preview()
//@Composable
//fun CardapioDownBarPreview() {
//    CardapioDownBar(
//        onCardapioClick = {}
//    )
//}