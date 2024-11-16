package com.example.gourmet_inventory_mobile.screens.Usuario

import android.content.Context
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

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
    EscolhaPerfilScreen({})
}

@Composable
fun ProfileImages(onPerfilClick: (String) -> Unit) {
    val context = LocalContext.current
    val resours = context.resources
    var cargoSelecionado = ""
    val coroutineScope = rememberCoroutineScope()

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
                    cargoSelecionado = resours.getString(R.string.garcom)
                    Log.d("ProfileImages", "Cargo selecionado: $cargoSelecionado")

                    coroutineScope.launch {
                        try {
                            DataStoreUtils(context).salvarCargo(role = cargoSelecionado)
                            val cargo = DataStoreUtils(context).obterCargo().firstOrNull()
                            Log.d(
                                "ProfileImages",
                                "Cargo Guardado: $cargo"
                            )
                        } catch (e: Exception) {
                            Log.e("ProfileImages", "Error saving cargo: ${e.message}")
                        }
                    }
                    onPerfilClick(resours.getString(R.string.garcom))
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
            painter = painterResource(id = R.drawable.manager),
            contentDescription = "LogoGerente",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(160.dp)
                .clickable {
                    cargoSelecionado = resours.getString(R.string.gerente)
                    Log.d("ProfileImages", "Cargo selecionado: $cargoSelecionado")

                    coroutineScope.launch {
                        try {
                            DataStoreUtils(context).salvarCargo(role = cargoSelecionado)
                            val cargo = DataStoreUtils(context).obterCargo().firstOrNull()
                            Log.d(
                                "ProfileImages",
                                "Cargo Guardado: $cargo"
                            )
                        } catch (e: Exception) {
                            Log.e("ProfileImages", "Error saving cargo: ${e.message}")
                        }
                    }
                    onPerfilClick(resours.getString(R.string.gerente))
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