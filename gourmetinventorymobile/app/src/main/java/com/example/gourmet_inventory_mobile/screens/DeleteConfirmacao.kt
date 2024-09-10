package com.example.gourmet_inventory_mobile.screens

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_Verde
import com.example.gourmet_inventory_mobile.ui.theme.GI_Vermelho
import com.example.gourmet_inventory_mobile.ui.theme.GourmetinventorymobileTheme
import com.example.gourmet_inventory_mobile.ui.theme.White

//class DeleteActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            GourmetinventorymobileTheme {
//                DeleteScreen()
//
//            }
//        }
//    }
//}

@Composable
fun DeleteScreen() {


    Surface(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Tem certeza que deseja excluir este item?",
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 20.dp)
            )


            Row(
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Button(
                    onClick = {},
                    modifier = Modifier
                        .height(45.dp)
                        .width(120.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GI_Verde,
                        contentColor = colorResource(id = R.color.white)
                    )
                ) {
                    Text(
                        text = "Sim",
                        color = White,
                        fontSize = 18.sp
                    )
                }
                Button(
                    onClick = {},
                    modifier = Modifier
                        .height(45.dp)
                        .width(120.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GI_Vermelho,
                        contentColor = colorResource(id = R.color.white)
                    )
                ) {
                    Text(
                        text = "Não",
                        color = White,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        contentAlignment = androidx.compose.ui.Alignment.BottomCenter
    ) {
        DownBarDeleteScreen()
    }

}

@Preview
@Composable
fun DeletePreview() {
    DeleteScreen()
}


@Composable
fun DownBarDelete2Screen() {
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
fun DownBarDelete2ScreenPreview() {
    DownBarScreen()
}

