package com.example.gourmet_inventory_mobile

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gourmet_inventory_mobile.screens.CardapioListScreen
import com.example.gourmet_inventory_mobile.screens.EscolhaPerfilScreen
import com.example.gourmet_inventory_mobile.screens.LoginScreen
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GourmetinventorymobileTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.gourmet_inventory_mobile.screens.ListaEstoqueScreen

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            GourmetinventorymobileTheme {
//                Surface(modifier = Modifier.fillMaxSize(), color = GI_AzulMarinho) {
//                    val navController = rememberNavController()
//
//                    NavHost(navController = navController, startDestination = "perfil/{user}") {
//                        composable("perfil/{user}") { entry ->
//                            entry.arguments?.getString("user")?.let { user ->
//                                //Serializando o objeto User para JSON
////                                val userJson = Json.encodeToString(user)
//                                EscolhaPerfilScreen(user, onPerfilClick = { _, _ ->})
//
//                            } ?: LaunchedEffect(null) {
//                                navController.navigate("login")
//                            }
//                        }
//                        composable("login") {
//                            LoginScreen(
//                                onLoginClick = { user ->
//                                    navController.navigate("perfil/$user")
//                                }
//                            )
//                        }
//                        composable("cardapio") {
////                            CardapioScreen(
////                                onCardapioClick = { user ->
////                                    navController.navigate("perfil/$user")
////                                }
////                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GourmetinventorymobileTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = GI_AzulMarinho) {
                    val navController = rememberNavController()
                    var clickedAction by remember { mutableStateOf("") }

                    NavHost(navController = navController, startDestination = "login") {
                        composable("perfil/{user}") { entry ->
                            entry.arguments?.getString("user")?.let { user ->
                                EscolhaPerfilScreen(user, onPerfilClick = { perfil ->
                                    val destination =
                                        if (perfil == "Garçom") {
                                            "cardapio"
                                        } else {
                                            "estoque"
                                        }
                                    navController.navigate(destination)
                                    destination
                                })
                            } ?: LaunchedEffect(null) {
                                navController.navigate("login")
                            }
                        }
                        composable("login") {
                            LoginScreen(
                                onLoginClick = { user ->
                                    navController.navigate("perfil/$user")
                                }
                            )
                        }
                        composable("cardapio") {
                            CardapioListScreen(
                                onCardapioClickMudarPerfil = {
                                    clickedAction = "Mudar Perfil"
                                    navController.navigate("perfil/Teste")
                                },
                                onCardapioClickAcao1 = {
                                    clickedAction = "Ação 1"
                                    navController.navigate("comanda")

                                },
                                onCardapioClickAcao2 = {
                                    clickedAction = "Ação 2"
                                },
                                onCardapioClickAcao3 = {
                                    clickedAction = "Ação 3"
                                }
                            )
                        }
                        composable("estoque") {
                            ListaEstoqueScreen(
                                onCardapioClickMudarPerfil = {
                                    clickedAction = "Mudar Perfil"
                                    navController.navigate("perfil/Teste")
                                },
//                                onCardapioClickAcao1 = {
//                                    clickedAction = "Ação 1"
//                                },
//                                onCardapioClickAcao2 = {
//                                    clickedAction = "Ação 2"
//                                },
//                                onCardapioClickAcao3 = {
//                                    clickedAction = "Ação 3"
//                                },
//                                onCardapioClickAcao4 =  {
//                                    clickedAction = "Ação 3"
//                                }
                            )
                        }
                    }
                }
            }
        }
    }
}