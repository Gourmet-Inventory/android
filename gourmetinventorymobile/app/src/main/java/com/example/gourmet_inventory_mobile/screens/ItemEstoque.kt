package com.example.gourmet_inventory_mobile.screens

import android.icu.text.TimeZoneFormat.Style
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_Orange
import com.example.gourmet_inventory_mobile.ui.theme.White
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp

import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.ui.theme.GourmetinventorymobileTheme

class ItemEstoque : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GourmetinventorymobileTheme {
                ItemEstoque()
            }
        }
    }
}
@Composable
fun Item() {
    Column(Modifier.fillMaxSize()) {
        Row { IconButton(onClick = {
        }) {
            Icon(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Voltar",
                Modifier.size(44.dp),
                tint = Color.Black
            )
        }
            Spacer(modifier = Modifier.width(8.dp)) }
        Row {
            Text(text = "Molho de Tomate",
                fontSize = 35.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(
                    bottom = 40.dp,
                    top = 20.dp,start = 60.dp, end = 60.dp)
            )
        }
        Column(){
            InfoItem("Lote:", "Lote 1")
            InfoItem("Categoria:", "Molhos", topPadding = 31.dp)
            InfoItem("Local Armazenamento:", "Geladeira", topPadding = 34.dp)
            InfoItem("Quantidade Unitária:", "3", topPadding = 36.dp)
            InfoItem("Tipo Medida:", "GRAMAS",topPadding = 30.dp)
            InfoItem("Valor medida:", "500", topPadding = 41.dp)
            InfoItem("Data Cadastro:", "20/05/2024", topPadding = 45.dp)
            InfoItem("Data Aviso:", "20/05/2024", topPadding = 34.dp)
        }
        Row(

            modifier = Modifier.fillMaxWidth().padding(top = 50.dp, start = 15.dp, end = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(containerColor = GI_Orange, contentColor = Color.White),
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .padding(end = 8.dp)
            ) {
                Text(text = "Editar")
            }

            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFED5656), contentColor = Color.White),
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .padding(start = 8.dp)
            ) {
                Text(text = "Excluir")
            }
        }

    }

    // Barra Inferior Fixa
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        contentAlignment = androidx.compose.ui.Alignment.BottomCenter
    ) {
        ItemDownBar()
    }

}

@Composable
fun InfoItem(label: String, value: String, topPadding: Dp = 0.dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = topPadding, start = 15.dp, end = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontWeight = FontWeight.SemiBold,fontSize = 22.sp)
        Text(text = value,fontSize = 22.sp)

    }


}

@Composable
fun ItemDownBar() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GI_AzulMarinho)
            .heightIn(80.dp),
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

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    Item()
}
