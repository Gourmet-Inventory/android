package com.example.gourmet_inventory_mobile.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White

@Composable
fun EscolhaPerfilScreen(onPerfilClick: (String) -> Unit) {
//fun EscolhaPerfilScreen(user: String, onPerfilClick: (String) -> String) {
    Surface(modifier = Modifier.fillMaxSize(), color = Black) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(top = 102.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Text(
                text = "ESCOLHA SEU PERFIL :",
                modifier = Modifier,
                color = White,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 24.sp,
                    fontFamily = JostBold,
                )
            )
            ProfileImages { perfil ->
                val destination = onPerfilClick(perfil)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EscolhaPerfilScreenPreview() {
//    val user = "Teste"
    EscolhaPerfilScreen() { }
}

@Composable
fun ProfileImages(onPerfilClick: (String) -> Unit) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .offset(y = (-20).dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.garcom),
            contentDescription = "LogoGarçom",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(160.dp)
                .clickable {
                    onPerfilClick("Garçom")
                    Toast
                        .makeText(context, "Logado como Garçom", Toast.LENGTH_SHORT)
                        .show()
                }
        )
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Garçom",
            color = White,
            fontSize = 24.sp,
            fontFamily = JostBold
        )
        Spacer(modifier = Modifier.height(60.dp))
        Image(
            painter = painterResource(id = R.drawable.gerente),
            contentDescription = "LogoGerente",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(160.dp)
                .clickable {
                    onPerfilClick("Gerente")
                    Toast
                        .makeText(context, "Logado como Gerente", Toast.LENGTH_SHORT)
                        .show()
                }
        )
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Gerente",
            color = White,
            fontSize = 24.sp,
            fontFamily = JostBold
        )
    }
}

@Preview
@Composable
fun ProfileImagesPreview() {
    ProfileImages {}
}