package com.example.gourmet_inventory_mobile.screens.Usuario

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.model.Usuario.User
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_Laranja
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.utils.BottomBarGarcom
import com.example.gourmet_inventory_mobile.utils.BottomBarGerente
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

@Composable
fun ViewPerfilScreen(
    navController: NavController,
    onViewPerfil: (String) -> Unit
) {
    val context = LocalContext.current
    val resources = context.resources
    var currentUser: User? by remember { mutableStateOf(null) }
    val coroutineScope = rememberCoroutineScope()
    var cargoSelecionado by remember { mutableStateOf("") }

    var nome by remember {
        mutableStateOf("")
    }
    var cargo by remember {
        mutableStateOf("")
    }
    var telefone by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    val photo by remember {
        mutableStateOf(if (cargo == resources.getString(R.string.garcom)) R.drawable.garcom else R.drawable.manager)
    }

    LaunchedEffect(Unit) {
        cargoSelecionado = DataStoreUtils(context = context).obterCargo().firstOrNull() ?: ""
        currentUser = DataStoreUtils(context = context).obterUsuario()
    }

    nome = currentUser?.name ?: ""
    cargo = currentUser?.cargo ?: ""
    telefone = currentUser?.telefone ?: ""
    email = currentUser?.email ?: ""

    Log.d("ViewPerfilScreen", "currentUser: $currentUser")
    Log.d("ViewPerfilScreen", "cargoSelecionado: $cargoSelecionado")

    Scaffold(
        bottomBar = {
            if (cargoSelecionado == resources.getString(R.string.garcom)) {
                BottomBarGarcom(
                    onClick = onViewPerfil,
                    navController = navController
                )
            } else if (cargoSelecionado == resources.getString(R.string.gerente)) {
                BottomBarGerente(
                    onClick = onViewPerfil,
                    navController = navController
                )
            }
//            else {
//                Toast.makeText(context, "Cargo não identificado", Toast.LENGTH_SHORT).show()
//            }
        }
    ) { padding ->
        Surface(
            color = Color.White,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .width(320.dp)
                        .height(150.dp)
                        .padding(bottom = 15.dp)
                ) {
                    Image(
                        painter = painterResource(id = photo),
                        contentDescription = "Foto do perfil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(120.dp)
                    )
                }


                nome?.let {
                    InfoPerfil(titulo = "Nome", valorCampo = it) {
                    }
                }

                cargo?.let {
                    InfoPerfil(titulo = "Cargo", valorCampo = it) {
                    }
                }

                telefone?.let {
                    InfoPerfil(titulo = "Celular", valorCampo = it) {
                    }
                }

                email?.let {
                    InfoPerfil(titulo = "E-mail", valorCampo = it) {
                    }
                }

                Row(
                    modifier = Modifier
                        .width(330.dp)
                        .height(100.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                DataStoreUtils(context = context).limparDados()
                                Log.d("ViewPerfilScreen", "Saindo da Gourmet: $currentUser")
                                onViewPerfil("login")
                            }
                        },
                        modifier = Modifier
                            .height(45.dp)
                            .width(195.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GI_Laranja,
                            contentColor = colorResource(id = R.color.white)
                        )
                    ) {
                        Text(
                            text = "Sair da Gourmet",
                            color = Black,
                            fontSize = 18.sp
                        )
                    }
                }


            }
        }
    }
}

@Preview
@Composable
fun ViewPerfilPreview() {
    ViewPerfilScreen(
        navController = NavController(LocalContext.current),
        onViewPerfil = {}
    )
}


@Composable
fun InfoPerfil(
    titulo: String,
    valorCampo: String,
    mudaValor: (String) -> Unit
) {

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .width(320.dp)
            .height(100.dp)
            .padding(bottom = 15.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .height(35.dp),
            text = "$titulo",
            color = Black,
            fontSize = 24.sp,
            fontFamily = JostBold
        )

        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .height(50.dp),
            text = valorCampo,
            color = Black,
            fontSize = 22.sp
        )
    }
}