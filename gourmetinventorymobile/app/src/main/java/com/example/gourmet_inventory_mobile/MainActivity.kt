package com.example.gourmet_inventory_mobile

import android.os.Bundle
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
import com.example.gourmet_inventory_mobile.screens.ComandaListScreen
import com.example.gourmet_inventory_mobile.screens.ListaComprasScreen
import com.example.gourmet_inventory_mobile.screens.ListaEstoqueScreen
import com.example.gourmet_inventory_mobile.screens.ListaFornecedoresScreen
import com.example.gourmet_inventory_mobile.screens.ViewPerfilScreen

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
                                    val destination = if (perfil == "Garçom") {
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
                            LoginScreen(onLoginClick = { user ->
                                navController.navigate("perfil/$user")
                            })
                        }
                        composable("cardapio") {
                            CardapioListScreen(onCardapioClickMudarPerfil = {
                                clickedAction = "Mudar Perfil"
                                navController.navigate("perfil/Teste")
                            }, onCardapioClickAcao1 = {
                                clickedAction = "Ação 1"
                                navController.navigate("comanda")
                            }, onCardapioClickAcao2 = {
                                clickedAction = "Ação 2"
                                navController.navigate("cardapio")
                            }, onCardapioClickAcao3 = {
                                clickedAction = "Ação 3"
                                navController.navigate("viewPerfil")
                            })
                        }
                        composable("estoque") {
                            ListaEstoqueScreen(onListaEstoqueClickMudarPerfil = {
                                clickedAction = "Mudar Perfil"
                                navController.navigate("perfil/Teste")
                            }, onListaEstoqueClickAcao1 = {
                                clickedAction = "Ação 1"
                                navController.navigate("listaFornecedor")
                            }, onListaEstoqueClickAcao2 = {
                                clickedAction = "Ação 2"
                                navController.navigate("listaEstoque")
                            }, onListaEstoqueClickAcao3 = {
                                clickedAction = "Ação 3"
                                navController.navigate("listaCompras")
                            }, onListaEstoqueClickAcao4 = {
                                clickedAction = "Ação 4"
                                navController.navigate("viewPerfil")
                            })
                        }
                        composable("comanda") {
                            ComandaListScreen(onComandaClickMudarPerfil = {
                                clickedAction = "Mudar Perfil"
                                navController.navigate("perfil/Teste")
                            }, onComandaClickAcao1 = {
                                clickedAction = "Ação 1"
                                navController.navigate("comanda")
                            }, onComandaClickAcao2 = {
                                clickedAction = "Ação 2"
                                navController.navigate("listaCompras")
                            }, onComandaClickAcao3 = {
                                clickedAction = "Ação 3"
                                navController.navigate("viewPerfil")
                            })
                        }
                        composable("viewPerfil") {
                            ViewPerfilScreen(
                                onViewPerfilSair = {
                                    clickedAction = "Sair"
                                    navController.navigate("login")
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
                        composable("listaFornecedor") {
                            ListaFornecedoresScreen(onListaFornecedorClickMudarPerfil = {
                                clickedAction = "Mudar Perfil"
                                navController.navigate("perfil/Teste")
                            }, onListaFornecedorClickAcao1 = {
                                clickedAction = "Ação 1"
//                                    navController.navigate("listaFornecedor")
                            }, onListaFornecedorClickAcao2 = {
                                clickedAction = "Ação 2"
                                navController.navigate("listaEstoque")
                            }, onListaFornecedorClickAcao3 = {
                                clickedAction = "Ação 3"
                                navController.navigate("listaCompras")
                            }, onListaFornecedorClickAcao4 = {
                                clickedAction = "Ação 4"
                                navController.navigate("viewPerfil")
                            })
                        }
                        composable("listaEstoque") {
                            ListaEstoqueScreen(onListaEstoqueClickMudarPerfil = {
                                clickedAction = "Mudar Perfil"
                                navController.navigate("perfil/Teste")
                            }, onListaEstoqueClickAcao1 = {
                                clickedAction = "Ação 1"
                                navController.navigate("listaFornecedor")
                            }, onListaEstoqueClickAcao2 = {
                                clickedAction = "Ação 2"
//                                    navController.navigate("listaEstoque")
                            }, onListaEstoqueClickAcao3 = {
                                clickedAction = "Ação 3"
                                navController.navigate("listaCompras")
                            }, onListaEstoqueClickAcao4 = {
                                clickedAction = "Ação 4"
                                navController.navigate("viewPerfil")
                            })
                        }
                        composable("listaCompras") {
                            ListaComprasScreen(
                                onListaComprasMudarPerfilClick = {
                                    clickedAction = "Mudar Perfil"
                                    navController.navigate("perfil/Teste")
                                },
                                onListaComprasAcao1Click = {
                                    clickedAction = "Ação 1"
                                    navController.navigate("listaFornecedor")
                                },
                                onListaComprasAcao2Click = {
                                    clickedAction = "Ação 2"
                                    navController.navigate("listaEstoque")
                                },
                                onListaComprasAcao3Click = {
                                    clickedAction = "Ação 3"
                                },
                                onListaComprasAcao4Click = {
                                    clickedAction = "Ação 4"
                                    navController.navigate("viewPerfil")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}