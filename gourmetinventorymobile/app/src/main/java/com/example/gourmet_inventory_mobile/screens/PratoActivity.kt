@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gourmet_inventory_mobile.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.model.Prato
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_CianoClaro
import com.example.gourmet_inventory_mobile.ui.theme.GI_Orange
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.JostLight
import com.example.gourmet_inventory_mobile.ui.theme.White

class PratoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PratoScreen(
                onCardapioClickAcao1 = {},
                onCardapioClickAcao2 = {},
                onCardapioClickAcao3 = {}
            )
        }
    }
}

@Composable
fun PratoScreen(
    onCardapioClickAcao1: () -> Unit,
    onCardapioClickAcao2: () -> Unit,
    onCardapioClickAcao3: () -> Unit
) {
    var contagemPratos by remember {
        mutableStateOf("01")
    }

    Scaffold(
        topBar = { FotoTop() }
    ) { padding ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            val context = LocalContext.current
            var selectedOptionIndex by remember { mutableStateOf(-1) }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    fontSize = 27.sp,
                    modifier = Modifier
                        .width(370.dp)
                        .padding(top = 20.dp),
                    text = "X-Burguer",
                    fontFamily = JostBold
                )

                Text(
                    fontSize = 18.sp,
                    modifier = Modifier
                        .width(365.dp)
                        .padding(top = 10.dp),
                    text = "É um prato comum em lanchonetes e fast foods, apreciado pela combinação" +
                        " harmoniosa de sabores e pela facilidade de preparo."
                )

                Row (
                    modifier = Modifier
                        .width(370.dp)
                        .padding(top = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        fontSize = 22.sp,
                        modifier = Modifier
                            .width(330.dp),
                        text = "Adicionar Observação",

                    )
                    Icon(imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Voltar",
                        modifier = Modifier.size(45.dp))

                }

                Row (
                    modifier = Modifier
                        .width(300.dp)
                        .padding(top = 20.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id = R.drawable.diminuir),
                        contentDescription = "Diminuir",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)

                    )
                    Text(
                        text = contagemPratos,
                        fontSize = 20.sp
                    )
                    Image(
                        painter = painterResource(id = R.drawable.aumentar),
                        contentDescription = "Aumentar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)

                    )
                }

                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(150.dp)
                        .padding(top = 20.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(GI_Orange),
                ) {
                    Text(
                        text = "FINALIZAR",
                        color = Black,
                        fontSize = 16.sp
                    )
                }


                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(
                        text = "VOLTAR",
                        color = Black,
                        fontSize = 16.sp
                    )
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    PratoDownBar(
                        onCardapioClickAcao1 = onCardapioClickAcao1,
                        onCardapioClickAcao2 = onCardapioClickAcao2,
                        onCardapioClickAcao3 = onCardapioClickAcao3
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PratoPreview() {
    PratoScreen(
        onCardapioClickAcao1 = {},
        onCardapioClickAcao2 = {},
        onCardapioClickAcao3 = {}
    )
}



@Composable
fun PratoDownBar(
    onCardapioClickAcao1: () -> Unit,
    onCardapioClickAcao2: () -> Unit,
    onCardapioClickAcao3: () -> Unit
) {
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
            painter = painterResource(id = R.drawable.notes_icon),
            contentDescription = "Ação 1",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(30.dp)
                .clickable {
                    onCardapioClickAcao1()
                }
        )
//        Spacer(modifier = Modifier.height(60.dp))
        Image(
            painter = painterResource(id = R.drawable.book_icon),
            contentDescription = "Ação 2",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(30.dp)
                .clickable {
                    onCardapioClickAcao2()
                }
        )
        Image(
            painter = painterResource(id = R.drawable.account_icon),
            contentDescription = "Ação 3",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(35.dp)
                .clickable {
                    onCardapioClickAcao3()
                }
        )
    }
}

@Composable
fun FotoTop(modifier: Modifier = Modifier) {

        Image(
            painter = painterResource(id = R.drawable.prato),
            contentDescription = "Prato",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 35.dp)
        )
}


@Preview()
@Composable
fun PratoDownBarPreview() {
    PratoDownBar(
        onCardapioClickAcao1 = {},
        onCardapioClickAcao2 = {},
        onCardapioClickAcao3 = {}
    )
}