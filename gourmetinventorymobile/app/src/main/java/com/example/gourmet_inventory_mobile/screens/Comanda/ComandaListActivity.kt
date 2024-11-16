@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gourmet_inventory_mobile.screens.Comanda

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.model.Comanda
import com.example.gourmet_inventory_mobile.model.Usuario.User
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_CianoClaro
import com.example.gourmet_inventory_mobile.ui.theme.GI_Laranja
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.utils.BottomBarGarcom
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
import com.example.gourmet_inventory_mobile.utils.DrawScrollableView
import com.example.gourmet_inventory_mobile.utils.SearchBox
import com.example.gourmet_inventory_mobile.viewmodel.ComandaViewModel
import kotlinx.coroutines.flow.first
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ComandaListScreen(
    navController: NavController,
    onComandaClick: (String) -> Unit,
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
                            onComandaClick("perfil")
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
            BottomBarGarcom(navController = navController, onClick = onComandaClick)
        }
    ) { padding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            val context = LocalContext.current
            var searchText by remember { mutableStateOf("") }
            var selectedOptionIndex by remember { mutableStateOf(0) }
            var isSent: String by remember { mutableStateOf("not_sent") }

            // Obtém o ViewModel do Koin
            val viewModel = koinViewModel<ComandaViewModel>()

            val listaComandas = viewModel.data
            val isLoading = viewModel.isLoading

//            val empresa = Empresa(1, "123456789")
//            val userGarcom1 =
//                User(1,"garcomum@gmail.com","123456", "garçom", "Garçom Um", "11999999999", empresa)
//            val userGarcom2 =
//                User(2,"garcomdois@gmail.com", "123456", "garçom", "Garçom Dois", "11999999999", empresa)

            // Filtra a lista com base no texto da pesquisa e na opção selecionada
            val filteredComandas = listaComandas.filter { comanda ->
                comanda.mesa.contains(searchText, ignoreCase = true) ||
                        comanda.titulo.contains(searchText, ignoreCase = true)
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

                // Campo de pesquisa
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
                            .background(color = GI_CianoClaro, shape = RoundedCornerShape(5.dp))
                    )
                }

                if (filteredComandas.isEmpty()) {
                    Column (
                        modifier = Modifier.fillMaxSize().padding(top = 40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "Nenhuma comanda encontrada",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 20.dp)
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        Image(
                            painter = painterResource(id = R.drawable.comandavazia),
                            contentDescription = "imagem de comandas vazias",
                            modifier = Modifier.padding(top = 20.dp)
                        )
                    }

                } else{
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    ) {
                        RadioButton(
                            colors = RadioButtonColors(
                                selectedColor = GI_AzulMarinho,
                                unselectedColor = GI_AzulMarinho,
                                disabledSelectedColor = GI_AzulMarinho,
                                disabledUnselectedColor = GI_AzulMarinho
                            ),
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

                    // Lista de comandas filtradas por responsável
                    when (selectedOptionIndex) {
                        0 -> {
                            ItensComanda(
                                comandas = filteredComandas,
                                onComandaClick = onComandaClick,
                                isSent = isSent
                            )
                        }

                        1 -> {
                            val comandasDoUsuario = filteredComandas.filter {
                                it.idGarcom == currentUser?.id
                            }
                            ItensComanda(
                                comandas = comandasDoUsuario,
                                onComandaClick = onComandaClick,
                                isSent = isSent
                            )
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
fun ItensComanda(
    comandas: List<Comanda>,
    onComandaClick: (String) -> Unit,
    isSent: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 75.dp, start = 20.dp, end = 20.dp)
    ) {
        DrawScrollableView(
            modifier = Modifier,
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp, bottom = 70.dp)
                ) {
                    comandas.forEach() { comanda ->
                        ItemComanda(
                            comanda = comanda,
                            onComandaClick = onComandaClick,
                            isSent = isSent
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun ItemComanda(
    comanda: Comanda,
    onComandaClick: (String) -> Unit,
    isSent: String
) {
    val resourses = LocalContext.current.resources

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(White.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
            .border(1.dp, Black, RoundedCornerShape(8.dp))
            .clickable {
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
                    text = "Comanda ${comanda.id}",
                    fontSize = 20.sp,
                    style = TextStyle(
                        fontFamily = JostBold,
                        color = White
                    ),
                    modifier = Modifier
                        .padding(8.dp),
                )
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            if (isSent == resourses.getString(R.string.comandaStatus1)) Color.Yellow
                            else if (isSent == resourses.getString(R.string.comandaStatus2)) Color.Blue
                            else if (isSent == resourses.getString(R.string.comandaStatus3)) Color.Red
                            else Color.Gray,
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
                Text(text = comanda.status, fontSize = 18.sp)
            }
        }
    }
}