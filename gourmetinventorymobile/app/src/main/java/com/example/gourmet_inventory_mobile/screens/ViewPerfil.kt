package com.example.gourmet_inventory_mobile.screens

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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_Orange
import com.example.gourmet_inventory_mobile.ui.theme.JostBold

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
    onViewPerfilSair: () -> Unit
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

    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {

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
                Text(text = nome,
                    fontSize = 20.sp)
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
            ){
                Button(
                    onClick = {
                        onViewPerfilSair()
                    },
                    modifier = Modifier
                        .height(45.dp)
                        .width(195.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GI_Orange,
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

@Preview
@Composable
fun ViewPerfilPreview() {
    ViewPerfilScreen(
        onViewPerfilSair = { }
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


@Composable
fun DownBarScreen() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GI_AzulMarinho)
            .heightIn(70.dp),
//        horizontalArrangement = Arrangement.SpaceEvenly,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.fornecedores_db),
            contentDescription = "Ação 1",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(30.dp)
                .clickable {
                    Toast
                        .makeText(context, "Ação 1", Toast.LENGTH_SHORT)
                        .show()
                }
        )
//        Spacer(modifier = Modifier.height(60.dp))
        Image(
            painter = painterResource(id = R.drawable.opened_box),
            contentDescription = "Ação 2",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(30.dp)
                .clickable {
                    Toast
                        .makeText(context, "Ação 2", Toast.LENGTH_SHORT)
                        .show()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.carrinho_de_compraspng),
            contentDescription = "Ação 3",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(30.dp)
                .clickable {
                    Toast
                        .makeText(context, "Ação 3", Toast.LENGTH_SHORT)
                        .show()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.account_icon),
            contentDescription = "Ação 4",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(35.dp)
                .clickable {
                    Toast
                        .makeText(context, "Ação 4", Toast.LENGTH_SHORT)
                        .show()
                }
        )
    }
}

@Preview
@Composable
fun DownBarScreenPreview() {
    DownBarScreen()
}
