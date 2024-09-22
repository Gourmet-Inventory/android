@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gourmet_inventory_mobile.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
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
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.utils.BottomBarGarcom

@Composable
fun ComandaListScreen(
    navController: NavController,
    onComandaClick: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton (
                    onClick = {
                        onComandaClick("perfil")
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
            BottomBarGarcom(navController = navController, onClick = onComandaClick)
        }
    ) { padding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            val context = LocalContext.current
            var searchText by remember { mutableStateOf("") }
            var selectedOptionIndex by remember { mutableStateOf(0) }
            var isSent: String by remember { mutableStateOf("not_sent") }

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
                    .padding(top = 40.dp)
            ) {
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
                        .weight(1f)
                        .padding(start = 26.dp, end = 26.dp, bottom = 70.dp)
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
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    Text(
                                        text = comanda.nomeComanda,
                                        fontSize = 20.sp,
                                        style = TextStyle(
                                            fontFamily = JostBold,
                                            color = White
                                        ),
                                        modifier = Modifier
//                                            .fillMaxWidth()
                                            .padding(8.dp),
//                                        textAlign = TextAlign.Center
                                    )
                                    Box(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .background(
                                                if (isSent == "enviado") Color.Red else if (isSent == "pendente") Color.Yellow else Color.Gray,
                                                shape = RoundedCornerShape(12.dp)
                                            )
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
        }
    }
}

@Preview
@Composable
fun ComandaListPreview() {
        ComandaListScreen(
            navController = NavController(LocalContext.current),
            onComandaClick = {},
        )
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