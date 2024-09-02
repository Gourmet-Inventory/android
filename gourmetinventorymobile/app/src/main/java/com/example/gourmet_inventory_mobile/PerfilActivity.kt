package com.example.gourmet_inventory_mobile

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GourmetinventorymobileTheme
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White

class PerfilActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GourmetinventorymobileTheme {
                EscolhaPerfilScreen()

            }
        }
    }
}

@Composable
fun EscolhaPerfilScreen() {
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
                text = "ESCOLHA SEU PERFIL:",
                modifier = Modifier,
                color = White,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 24.sp,
                    fontFamily = JostBold,
                )
            )
            ProfileImages()

        }
    }
}

@Preview(showBackground = true)
@Composable
fun EscolhaPerfilScreenPreview() {
    EscolhaPerfilScreen()
}

@Composable
fun ProfileImages() {
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
    ProfileImages()
}