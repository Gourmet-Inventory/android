@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gourmet_inventory_mobile.screens.Usuario

import android.graphics.Color.WHITE
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.model.Usuario.User
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_BrancoSujo
import com.example.gourmet_inventory_mobile.ui.theme.GI_Laranja
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.viewmodel.LoginState
import com.example.gourmet_inventory_mobile.viewmodel.LoginViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginClick: (User) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = GI_AzulMarinho) {
        val viewModel = koinViewModel<LoginViewModel>()
        val loginState by viewModel.loginState.collectAsState()

        var email by remember { mutableStateOf("") }
        var senha by remember { mutableStateOf("") }
        var emailError by remember { mutableStateOf<String?>(null) }
        var passwordError by remember { mutableStateOf<String?>(null) }
        var user: User? by remember { mutableStateOf(null) }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 110.dp, start = 25.dp, end = 25.dp, top = 50.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
        ) {

            MinhaImagemVetorial()
            Row(
                modifier = Modifier
                    .padding(bottom = 90.dp, start = 0.dp),
            ) {
                Text(
                    text = "BEM-VINDO AO SEU ESTOQUE!",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color(WHITE),
                        fontFamily = JostBold,
                    ),
                )
            }

            OutlinedTextField(
                value = email.trim(),
                onValueChange = {
                    email = it.trim()
                    emailError = validateEmail(it.trim())
                },
                placeholder = {
                    Text(
                        text = "Email",
                        color = GI_AzulMarinho,
                        fontSize = 18.sp,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = GI_BrancoSujo, shape = RoundedCornerShape(5.dp))
                    .border(
                        width = 20.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(25.dp)
                    ),
                shape = RoundedCornerShape(5.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = GI_AzulMarinho,
                    unfocusedBorderColor = GI_AzulMarinho,
                    cursorColor = GI_AzulMarinho,
                ),
                singleLine = true,
                isError = emailError != null
            )
            if (emailError != null) {
                Text(
                    text = emailError!!,
                    color = MaterialTheme.colorScheme.error,
                    style = TextStyle(fontSize = 18.sp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                value = senha.trim(),
                onValueChange = {
                    senha = it.trim()
                    passwordError = validatePassword(it.trim())
                },
                placeholder = {
                    Text(
                        text = "Senha",
                        color = GI_AzulMarinho,
                        fontSize = 18.sp
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = GI_BrancoSujo, shape = RoundedCornerShape(5.dp))
                    .border(
                        width = 20.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(25.dp)
                    ),
                shape = RoundedCornerShape(5.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = GI_AzulMarinho,
                    unfocusedBorderColor = GI_AzulMarinho,
                    cursorColor = GI_AzulMarinho,
                ),
                singleLine = true,
                isError = passwordError != null
            )
            if (passwordError != null) {
                Text(
                    text = passwordError!!,
                    color = MaterialTheme.colorScheme.error,
                    style = TextStyle(fontSize = 18.sp)
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            val context = LocalContext.current

            Button(
                onClick = {
                    val emailValidation = validateEmail(email)
                    val passwordValidation = validatePassword(senha)

                    if (emailValidation == null && passwordValidation == null) {
                        Log.d("LoginScreen", "Email: $email, Password: $senha")
                        viewModel.login(context, email, senha)
                    } else {
                        emailError = emailValidation
                        passwordError = passwordValidation
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(55.dp)
                    .padding(start = 85.dp, end = 85.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = GI_Laranja,
                    contentColor = colorResource(id = R.color.white)
                ),
            ) {
                if (loginState is LoginState.Loading) {
                    CircularProgressIndicator(color = GI_AzulMarinho)
                } else {
                    Text(
                        text = "Entrar",
                        color = GI_AzulMarinho,
                        fontSize = 18.sp,
                    )
                }
            }

            LaunchedEffect(loginState) {
                if (loginState is LoginState.Success) {
                    val user = (loginState as LoginState.Success).user
                    Toast.makeText(context, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                    onLoginClick(user)
                }
            }

            LaunchedEffect(loginState) {
                if (loginState is LoginState.Error) {
                    Log.e("LoginScreen", "ERRO: " + (loginState as LoginState.Error).message)
                    Toast.makeText(context, "Erro ao fazer login", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onLoginClick = { }
    )
}

@Composable
fun MinhaImagemVetorial() {
    Image(
        painter = painterResource(id = R.drawable.login_image),
        contentDescription = "Logo",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(bottom = 65.dp)
    )
}

fun validateEmail(email: String): String? {
    return if (email.isBlank()) {
        "Email não pode ser vazio"
    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        "Email inválido"
    } else {
        null
    }
}

fun validatePassword(password: String): String? {
    return if (password.isBlank()) {
        "Senha não pode ser vazia"
    } else if (password.length < 6) {
        "Senha deve ter pelo menos 6 caracteres"
    } else {
        null
    }
}

