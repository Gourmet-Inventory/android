@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gourmet_inventory_mobile

import android.graphics.Color.WHITE
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_BrancoSujo
import com.example.gourmet_inventory_mobile.ui.theme.GI_Orange
import com.example.gourmet_inventory_mobile.ui.theme.JostBold

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegisterScreen()
            Surface(modifier = Modifier.fillMaxSize(), color = GI_AzulMarinho) {
                RegisterScreen()
            }
        }
    }
}

@Composable
fun RegisterScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

//    Row(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(bottom = 170.dp, start = 50.dp, end = 25.dp),
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 340.dp),
////            verticalArrangement = Arrangement.Center,
//        ) {
//            Text(
//                text = "BEM VINDO AO SEU ESTOQUE!",
//                style = TextStyle(
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 20.sp,
//                    color = Color(WHITE)
//                ),
//                fontWeight = FontWeight.Bold,
//                fontSize = 20.sp,
//                color = Color(WHITE)
//            )
//        }
//    }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 340.dp),
    ) {
        Text(
            text = "BEM VINDO AO SEU ESTOQUE!",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
//                fontStyle = JostBold,
                fontSize = 20.sp,
                color = Color(WHITE)
            ),
            fontSize = 20.sp,
            color = Color(WHITE)
        )
    }

    Column(
        modifier = Modifier
//            .heightIn(100.dp)
//            .fillMaxSize()
//            .fillMaxWidth()
            .padding(bottom = 120.dp, start = 25.dp, end = 25.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Email", color = GI_AzulMarinho) },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = GI_BrancoSujo, shape = RoundedCornerShape(30.dp))
                .border(
                    width = 20.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(25.dp)
                ),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = GI_AzulMarinho,
                unfocusedBorderColor = GI_AzulMarinho,
                cursorColor = GI_AzulMarinho,
            )
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Senha", color = GI_AzulMarinho) },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = GI_BrancoSujo, shape = RoundedCornerShape(30.dp))
                .border(
                    width = 20.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(25.dp)
                ),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = GI_AzulMarinho,
                unfocusedBorderColor = GI_AzulMarinho,
                cursorColor = GI_AzulMarinho,
            )
        )
        Spacer(modifier = Modifier.height(60.dp))

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(55.dp)
                .padding(start = 70.dp, end = 70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = GI_Orange,
                contentColor = colorResource(id = R.color.white)
            )
        ) {
            Text("Cadastrar", color = GI_AzulMarinho)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    Surface(modifier = Modifier.fillMaxSize(), color = GI_AzulMarinho) {
        RegisterScreen()
    }
}