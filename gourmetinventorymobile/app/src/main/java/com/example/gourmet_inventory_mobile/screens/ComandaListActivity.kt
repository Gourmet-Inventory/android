@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gourmet_inventory_mobile.screens

import android.graphics.Color.WHITE
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_BrancoSujo
import com.example.gourmet_inventory_mobile.ui.theme.GI_CianoClaro
import com.example.gourmet_inventory_mobile.ui.theme.GI_Orange
import com.example.gourmet_inventory_mobile.ui.theme.GourmetinventorymobileTheme
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.JostLight
import com.example.gourmet_inventory_mobile.ui.theme.White

class ComandaListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GourmetinventorymobileTheme {
                ComandaList()
            }
        }
    }
}

@Composable
fun ComandaList() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current
        var searchText by remember { mutableStateOf("") }
        var selectedOptionIndex by remember { mutableStateOf(-1) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 45.dp, start = 26.dp, end = 26.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = androidx.compose.ui.Alignment.Top
        ) {
            OutlinedButton(
                onClick = {
                    Toast.makeText(context, "Ação", Toast.LENGTH_SHORT)
                        .show()
                },
                modifier = Modifier
                    .width(160.dp)
                    .height(45.dp)
                    .align(androidx.compose.ui.Alignment.Top),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GI_Orange,
                    contentColor = White
                )
            ) {
                Text(
                    text = "Mudar Perfil",
                    color = Black,
                    fontSize = 18.sp
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 130.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = androidx.compose.ui.Alignment.Start
        ) {
            Text(
                text = "Comandas: ",
                modifier = Modifier
                    .padding(start = 26.dp),
                style = TextStyle(
                    fontSize = 30.sp,
                    color = Black
                )
            )

            // Campo de Pesquisa
            SearchBox(
                searchText = searchText,
                mudaValorCampo = { novoValorCampo: String ->
                    searchText = novoValorCampo
                }
            )
            //Bot]pes de filtro
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(4.dp)
            ) {
                RadioButton(
                    selected = selectedOptionIndex == 1,
                    onClick = { selectedOptionIndex = 1 }
                )
                Text(text = "Todas", fontSize = 16.sp, modifier = Modifier.padding(end = 4.dp))

                RadioButton(
                    selected = selectedOptionIndex == 0,
                    onClick = { selectedOptionIndex = 0 }
                )
                Text(text = "Mnhas", fontSize = 16.sp)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            contentAlignment = androidx.compose.ui.Alignment.BottomCenter
        ) {
            DownBar()
        }
    }
}

@Preview
@Composable
fun ComandaListPreview() {
    GourmetinventorymobileTheme {
        ComandaList()
    }
}

@Composable
fun SearchBox(searchText: String, mudaValorCampo: (String) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = { novoValorCampo: String ->
            mudaValorCampo(novoValorCampo)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = GI_CianoClaro, shape = RoundedCornerShape(5.dp)),
        placeholder = {
            Text(
                text = "Pesquisar",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Black,
//                    fontFamily =  JostLight
                ),
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Ícone de pesquisa"
            )
        },
        shape = RoundedCornerShape(8.dp), // Define o formato arredondado
//        colors = TextFieldDefaults.outlinedTextFieldColors(
//            focusedBorderColor = Color.Blue, // Cor da borda quando em foco
//            unfocusedBorderColor = Color.Gray, // Cor da borda quando não está em foco
//            cursorColor = Color.Black // Cor do cursor
//        )
    )
}

@Preview
@Composable
fun SearchBoxPreview() {
    SearchBox(searchText = "", mudaValorCampo = {})
}

@Composable
fun DownBar() {
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
            painter = painterResource(id = R.drawable.notes_icon),
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
            painter = painterResource(id = R.drawable.book_icon),
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
            painter = painterResource(id = R.drawable.account_icon),
            contentDescription = "Ação 3",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(35.dp)
                .clickable {
                    Toast
                        .makeText(context, "Ação 3", Toast.LENGTH_SHORT)
                        .show()
                }
        )
    }
}

@Preview()
@Composable
fun DownBarPreview() {
    DownBar()
}