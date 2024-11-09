@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gourmet_inventory_mobile.screens.Cardapio

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.gourmet_inventory_mobile.model.Usuario.User
import com.example.gourmet_inventory_mobile.repository.estoque.ComandaRepositoryImplLocal
import com.example.gourmet_inventory_mobile.repository.estoque.PratoRepositoryImplLocal
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_CianoClaro
import com.example.gourmet_inventory_mobile.ui.theme.GI_Laranja
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.utils.BottomBarGarcom
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
import com.example.gourmet_inventory_mobile.utils.DrawScrollableView
import com.example.gourmet_inventory_mobile.utils.LoadingList
import com.example.gourmet_inventory_mobile.utils.SearchBox
import com.example.gourmet_inventory_mobile.viewmodel.ComandaViewModel
import com.example.gourmet_inventory_mobile.viewmodel.PratoViewModel
import kotlinx.coroutines.flow.first

@Composable
fun CardapioListScreen(
    pratoViewModel: PratoViewModel,
    comandaViewModel: ComandaViewModel,
    navController: NavController,
    onCardapioClick: (String) -> Unit,
) {
    val context = LocalContext.current

    var currentUser: User? by remember { mutableStateOf(null) }
    LaunchedEffect(Unit) {
        currentUser = DataStoreUtils(context = context).obterUsuario()?.first()
    }

    val resourses = context.resources

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                OutlinedButton(
                    onClick = {
                        Log.d("ListaEstoqueScreen", "currentUser: ${currentUser}")

                        if (currentUser?.cargo == resourses.getString(R.string.gerente)) {
                            onCardapioClick("perfil")
                        } else {
                            Toast.makeText(
                                context,
                                "Acesso restrito a Gerentes",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GI_Laranja,
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
    ) { padding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            val context = LocalContext.current
            var searchText by remember { mutableStateOf("") }

            val listaPratos = pratoViewModel.data
            val isLoading = pratoViewModel.isLoading

            // Filtra a lista com base no texto da pesquisa
            val filteredCardapio = listaPratos.filter { prato ->
                prato.nome.contains(searchText, ignoreCase = true)
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 45.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
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
                    Button(
                        modifier = Modifier
                            .padding(top = 16.dp, end = 16.dp)
                            .width(140.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GI_AzulMarinho,
                            contentColor = White
                        ),
                        onClick = {
                            if(comandaViewModel.listaPratosComanda.value.isNullOrEmpty() == true) {
                                Toast.makeText(
                                    context,
                                    "Adicione itens à comanda",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                navController.navigate("comandaView")
                            }
                        }) {
                        Text(text = "Ver Comanda")
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 26.dp, end = 26.dp, top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SearchBox(
                        searchText = searchText,
                        mudaValorCampo = { novoTexto -> searchText = novoTexto },
                        modifier = Modifier
                            .width(360.dp)
                            .height(80.dp)
                            .padding(bottom = 25.dp)
                            .background(color = GI_CianoClaro, shape = RoundedCornerShape(5.dp)),
                    )
                }

                if (isLoading) {
                    Box(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    ) {
                        LoadingList()
                    }

                } else {
                    if (filteredCardapio.isEmpty()) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Nenhum prato encontrado",
                                fontSize = 20.sp,
                                modifier = Modifier.padding(top = 20.dp)
                            )
                        }
                    } else {
                        Log.d("ListaEstoqueScreen", "filteredEstoque: $filteredCardapio")
                        ItensCardapio(
                            pratos = filteredCardapio,
                            onCardapioClick = onCardapioClick
                        )
                    }
                }

                ItensCardapio(
                    pratos = filteredCardapio,
                    onCardapioClick = onCardapioClick
                )

            }
        }
    }
}

@Composable
fun ItensCardapio(
    pratos: List<Prato>,
    onCardapioClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        DrawScrollableView(
            modifier = Modifier,
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                ) {
                    pratos.forEach { prato ->
                        ItemPrato(
                            prato = prato,
                            onCardapioClick = onCardapioClick,
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun ItemPrato(
    prato: Prato,
    onCardapioClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                onCardapioClick("cardapioItem/${prato.idPrato}")
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
                text = prato.descricao,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp),
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
            painter = painterResource(id = R.drawable.bro),
            contentDescription = "Imagem do prato",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(150.dp)
        )
    }
    Divider(
        color = Color.Black,
        thickness = 1.dp,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun CardapioListScreenPreview() {
    CardapioListScreen(
        pratoViewModel = PratoViewModel(PratoRepositoryImplLocal()),
        navController = NavController(
            context = LocalContext.current
        ),
        onCardapioClick = {},
        comandaViewModel = ComandaViewModel(
            ComandaRepositoryImplLocal(),
        )
    )
}