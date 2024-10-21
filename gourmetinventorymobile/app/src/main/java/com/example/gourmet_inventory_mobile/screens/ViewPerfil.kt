package com.example.gourmet_inventory_mobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_Laranja
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.utils.BottomBarGarcom
import com.example.gourmet_inventory_mobile.utils.BottomBarGerente

//class ViewPerfilActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            GourmetinventorymobileTheme {
//                VizuPerfilScreen()
//
//            }
//        }
//    }
//}

@Composable
fun ViewPerfilScreen(
    user: String,
    navController: NavController,
    onViewPerfil: (String) -> Unit
) {
    var nome by remember {
        mutableStateOf("João Silva")
    }
    var cargo by remember {
        mutableStateOf("Garçom")
    }
    var celular by remember {
        mutableStateOf("11989898989")
    }
    var email by remember {
        mutableStateOf("joão silva@gmail.com")
    }

    Scaffold(
        bottomBar = {
            if (cargo == "Garçom") {
                BottomBarGarcom(
                    onClick = onViewPerfil,
                    navController = navController
                )
            }
            else {
                BottomBarGerente(
                    onClick = onViewPerfil,
                    navController = navController
                )
            }
        }
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
//                    .padding(top = 45.dp, start = 26.dp, end = 26.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.garcom),
                        contentDescription = "Foto do perfil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(100.dp)
                    )
                    Text(
                        text = nome,
                        fontSize = 20.sp
                    )
                }


                InfoPerfil(titulo = "Nome", valorCampo = nome) {
                }

                InfoPerfil(titulo = "Cargo", valorCampo = cargo) {
                }

                InfoPerfil(titulo = "Celular", valorCampo = celular) {
                }

                InfoPerfil(titulo = "E-mail", valorCampo = email) {
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
                            onViewPerfil("login")
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp),
                contentAlignment = androidx.compose.ui.Alignment.BottomCenter
            ) {
//            DownBarDeleteScreen()
            }
        }
    }
}

@Preview
@Composable
fun ViewPerfilPreview() {
    ViewPerfilScreen(
        user = "Garçom",
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

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .width(320.dp)
            .height(100.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .height(30.dp),
            text = "$titulo:",
            color = Black,
            fontSize = 24.sp,
            fontFamily = JostBold
        )

        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .height(30.dp),
            text = valorCampo,
            color = Black,
            fontSize = 22.sp
        )
    }
}