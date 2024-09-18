@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gourmet_inventory_mobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.model.Comanda
import com.example.gourmet_inventory_mobile.model.User
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_CianoClaro
import com.example.gourmet_inventory_mobile.ui.theme.GI_Orange
import com.example.gourmet_inventory_mobile.ui.theme.GourmetinventorymobileTheme
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.utils.BottomNavBar

@Composable
fun ComandaListScreen(
    navController: NavController,
//    onComandaListClickMudarPerfil: () -> Unit,
    onComandaClick: (String) -> Unit,
//    onComandaListComandaView: () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomBar (
                onComandaClick = {
                    navController.navigate(it)
                },
                navController = {}
            )
        }
    ) { padding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            val context = LocalContext.current
            var searchText by remember { mutableStateOf("") }
            var selectedOptionIndex by remember { mutableStateOf(0) }

            val userGarcom1 = User("garcomum@gmail.com", "123456", "garçom")
            val userGarcom2 = User("garcomdois@gmail.com", "123456", "garçom")

            val comandas = listOf(
                Comanda("Mesa 1", "João Silva", "Comanda 123", userGarcom1),
                Comanda("Mesa 2", "Maria Oliveira", "Comanda 456", userGarcom1),
                Comanda("Mesa 3", "Pedro Souza", "Comanda 789", userGarcom1),
                Comanda("Mesa 4", "Ana Pereira", "Comanda 101", userGarcom2),
                Comanda("Mesa 5", "Carlos Rodrigues", "Comanda 112", userGarcom2),
                Comanda("Mesa 6", "Fábio Teixeira", "Comanda 111", userGarcom2),
                Comanda("Mesa 7", "Gislaino Portoloto", "Comanda 01", userGarcom2),
            )

            // Filtra a lista com base no texto da pesquisa e na opção selecionada
            val filteredComandas = comandas.filter {
                (selectedOptionIndex != 1 || it.garcom == userGarcom1) &&
                        (it.mesa.contains(searchText, ignoreCase = true) ||
                                it.nomeCliente.contains(searchText, ignoreCase = true) ||
                                it.nomeComanda.contains(searchText, ignoreCase = true))
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 45.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Top
                ) {
                    OutlinedButton(
                        onClick = {
//                            onComandaListClickMudarPerfil()
                            onComandaClick("perfil")
                        },
                        modifier = Modifier
                            .width(170.dp)
                            .height(45.dp)
                            .padding(end = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GI_Orange,
                            contentColor = White
                        )
                    ) {
                        Text(
                            text = "Mudar Perfil",
                            color = Black,
                            fontSize = 18.sp
                        )
                    }
                }
                Text(
                    text = "Comanda: ",
                    modifier = Modifier
                        .padding(start = 26.dp, top = 35.dp),
                    style = TextStyle(
                        fontSize = 30.sp,
                        color = Black
                    )
                )

                // Campo de Pesquisa
                SearchBox(
                    searchText = searchText,
                    mudaValorCampo = { novoValorCampo: String ->
                        searchText = novoValorCampo
                    }
                )

                // Botões de filtro
//            var selectedOptionIndex by remember { mutableStateOf(1)}
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    RadioButton(
                        selected = selectedOptionIndex == 0,
                        onClick = { selectedOptionIndex = 0 }
                    )
                    Text(text = "Todas", fontSize = 16.sp, modifier = Modifier.padding(end = 4.dp))

                    RadioButton(
                        selected = selectedOptionIndex == 1,
                        onClick = { selectedOptionIndex = 1 }
                    )
                    Text(text = "Minhas", fontSize = 16.sp)
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 70.dp)
                        .weight(1f)
                        .padding(start = 26.dp, end = 26.dp)
                ) {
                    items(filteredComandas) { comanda ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .background(White.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                                .border(1.dp, Black, RoundedCornerShape(8.dp))
                                .clickable {
//                                    onComandaListComandaView()
                                    onComandaClick("comandaView")
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            GI_AzulMarinho,
                                            RoundedCornerShape(
                                                bottomEnd = 0.dp,
                                                bottomStart = 0.dp,
                                                topEnd = 8.dp,
                                                topStart = 8.dp
                                            )
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = comanda.nomeComanda,
                                        fontSize = 20.sp,
                                        style = TextStyle(
                                            fontFamily = JostBold,
                                            color = White
                                        ),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 10.dp, bottom = 10.dp),
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    Text(text = comanda.mesa, fontSize = 18.sp)
                                    Text(text = comanda.nomeCliente, fontSize = 18.sp)
                                }
                            }
                        }
                    }
                }
            }
//            Box(
//                modifier = Modifier
//                    .fillMaxSize(),
//                contentAlignment = Alignment.BottomCenter
//            ) {
//                DownBar(
//                    onComandaListClickAcao1 = onComandaListClickAcao1,
//                    onComandaListClickAcao2 = onComandaListClickAcao2,
//                    onComandaListClickAcao3 = onComandaListClickAcao3
//                )
//            }
        }
    }
}

@Preview
@Composable
fun ComandaListPreview() {
    GourmetinventorymobileTheme {
        ComandaListScreen(
            navController = NavController(LocalContext.current),
//            onComandaListClickMudarPerfil = {},
            onComandaClick = {},
//            onComandaListClickAcao1 = {},
//            onComandaListClickAcao2 = {},
//            onComandaListClickAcao3 = {},
//            onComandaListComandaView = {}
        )
    }
}

@Composable
fun SearchBox(searchText: String, mudaValorCampo: (String) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = { novoValorCampo: String ->
            mudaValorCampo(novoValorCampo)
        },
        modifier = Modifier
            .fillMaxWidth()
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
//        colors = TextFieldDefaults.outlinedTextFieldColors(
//            focusedBorderColor = Color.Blue, // Cor da borda quando em foco
//            unfocusedBorderColor = Color.Gray, // Cor da borda quando não está em foco
//            cursorColor = Color.Black // Cor do cursor
//        )
    )
}

@Preview
@Composable
fun SearchBoxPreview() {
    SearchBox(searchText = "", mudaValorCampo = {})
}

//@Composable
//fun DownBar(
//    onComandaListClickAcao1: () -> Unit,
//    onComandaListClickAcao2: () -> Unit,
//    onComandaListClickAcao3: () -> Unit
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
//                    onComandaListClickAcao1()
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
//                    onComandaListClickAcao2()
//                }
//        )
//        Image(
//            painter = painterResource(id = R.drawable.account_icon),
//            contentDescription = "Ação 3",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .height(35.dp)
//                .clickable {
//                    onComandaListClickAcao3()
//                }
//        )
//    }
//}

//@Preview()
//@Composable
//fun DownBarPreview() {
//    DownBar(
//        onComandaListClickAcao1 = {},
//        onComandaListClickAcao2 = {},
//        onComandaListClickAcao3 = {}
//    )
//}

@Composable
fun BottomBar(navController: NavController.() -> Unit, onComandaClick: (String) -> Unit) {
    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    val items = listOf(
        BottomNavBar.Comandas,
        BottomNavBar.Cardapio,
        BottomNavBar.PerfilView,
    )

    NavigationBar(
        containerColor = GI_AzulMarinho,
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = selectedIndex == index
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = if (isSelected) Black else Color.White
                    )
                },
                label = { Text(text = item.title, color = Color.White) },
                selected = isSelected,
                onClick = {
                    selectedIndex = index
                    onComandaClick(item.route)
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = if (isSelected) White else Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar(onComandaClick = {}, navController = {})
}