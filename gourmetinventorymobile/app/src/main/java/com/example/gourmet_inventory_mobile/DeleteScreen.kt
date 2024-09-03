package com.example.gourmet_inventory_mobile

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_Orange
import com.example.gourmet_inventory_mobile.ui.theme.GourmetinventorymobileTheme
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.JostRegular
import com.example.gourmet_inventory_mobile.ui.theme.White

class DeleteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GourmetinventorymobileTheme {
                DeleteScreen()

            }
        }
    }
}

@Composable
fun DeleteScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
    val context = LocalContext.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
//                    .padding(top = 45.dp, start = 26.dp, end = 26.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Text(
                    text = "Cadastrar Item:",
                    modifier = Modifier,
                    color = Black,
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 35.sp
                    )
                )
            }
            inputCadastro()
        }
    }
}

@Preview
@Composable
fun DeleteScreenPreview() {
    GourmetinventorymobileTheme {
        DeleteScreen()
    }
}


@Composable
fun inputCadastro() {
    val context = LocalContext.current
    Row (
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .size(width = 80.dp, height = 30.dp)
    ){
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "Nome",
            color = Black,
            fontSize = 24.sp,
            fontFamily = JostBold
        )
    }
}

@Composable
fun DownBarDeleteScreen() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GI_AzulMarinho)
            .heightIn(75.dp),
//        horizontalArrangement = Arrangement.SpaceEvenly,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.db1),
            contentDescription = "Ação 1",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(35.dp)
                .clickable {
                    Toast
                        .makeText(context, "Ação 1", Toast.LENGTH_SHORT)
                        .show()
                }
        )


    }
}

@Preview()
@Composable
fun DownBarDeleteScreenPreview() {
    DownBar()
}