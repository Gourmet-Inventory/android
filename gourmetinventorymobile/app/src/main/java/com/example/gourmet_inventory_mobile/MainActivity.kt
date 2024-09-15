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
import com.example.gourmet_inventory_mobile.ui.theme.GourmetinventorymobileTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.gourmet_inventory_mobile.screens.CadastroItem2Screen
import com.example.gourmet_inventory_mobile.screens.CadastroItemScreen
import com.example.gourmet_inventory_mobile.screens.ComandaListScreen
import com.example.gourmet_inventory_mobile.screens.ComandaViewScreen
import com.example.gourmet_inventory_mobile.screens.DeleteCnfirmacaoScreen
import com.example.gourmet_inventory_mobile.screens.ItemEstoqueScreen
import com.example.gourmet_inventory_mobile.screens.ListaComprasScreen
import com.example.gourmet_inventory_mobile.screens.ListaEstoqueScreen
import com.example.gourmet_inventory_mobile.screens.ListaFornecedoresScreen
import com.example.gourmet_inventory_mobile.screens.ViewPerfilScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GourmetinventorymobileTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    var clickedAction by remember { mutableStateOf("") }

                    NavHost(navController = navController, startDestination = "login") {

                        composable("perfil/{user}") { entry ->
                            entry.arguments?.getString("user")?.let { user ->
                                EscolhaPerfilScreen(user, onPerfilClick = { perfil ->
                                    val destination = if (perfil == "Garçom") {
                                        "cardapio"
                                    } else {
                                        "listaEstoque"
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
                                navController.navigate("comandaList")
                            }, onCardapioClickAcao2 = {
                                clickedAction = "Ação 2"
                                navController.navigate("cardapio")
                            }, onCardapioClickAcao3 = {
                                clickedAction = "Ação 3"
                                navController.navigate("viewPerfil")
                            })
                        }

                        composable("comandaList") {
                            ComandaListScreen(
                                onComandaListClickMudarPerfil = {
                                    clickedAction = "Mudar Perfil"
                                    navController.navigate("perfil/Teste")
                                },
                                onComandaListClickAcao1 = {
                                    clickedAction = "Ação 1"
                                    navController.navigate("comandaList")
                                },
                                onComandaListClickAcao2 = {
                                    clickedAction = "Ação 2"
                                    navController.navigate("cardapio")
                                },
                                onComandaListClickAcao3 = {
                                    clickedAction = "Ação 3"
                                    navController.navigate("viewPerfil")
                                },
                                onComandaListComandaView = {
                                    clickedAction = "Ação 4"
                                    navController.navigate("comandaView")
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
                            ListaFornecedoresScreen(
                                onListaFornecedorClickMudarPerfil = {
                                    clickedAction = "Mudar Perfil"
                                    navController.navigate("perfil/Teste")
                                },
                                onListaFornecedorClickAcao1 = {
                                    clickedAction = "Ação 1"
//                                    navController.navigate("listaFornecedor")
                                },
                                onListaFornecedorClickAcao2 = {
                                    clickedAction = "Ação 2"
                                    navController.navigate("listaEstoque")
                                },
                                onListaFornecedorClickAcao3 = {
                                    clickedAction = "Ação 3"
                                    navController.navigate("listaCompras")
                                },
                                onListaFornecedorClickAcao4 = {
                                    clickedAction = "Ação 4"
                                    navController.navigate("viewPerfil")
                                })
                        }

                        composable("listaEstoque") {
                            ListaEstoqueScreen(
                                onListaEstoqueClickMudarPerfil = {
                                    clickedAction = "Mudar Perfil"
                                    navController.navigate("perfil/Teste")
                                },
                                onListaEstoqueClickAcao1 = {
                                    clickedAction = "Ação 1"
                                    navController.navigate("listaFornecedor")
                                },
                                onListaEstoqueClickAcao2 = {
                                    clickedAction = "Ação 2"
//                                    navController.navigate("listaEstoque")
                                },
                                onListaEstoqueClickAcao3 = {
                                    clickedAction = "Ação 3"
                                    navController.navigate("listaCompras")
                                },
                                onListaEstoqueClickAcao4 = {
                                    clickedAction = "Ação 4"
                                    navController.navigate("viewPerfil")
                                },
                                onListaEstoqueClickItemEstoque = {
                                    clickedAction = "Ação 5"
                                    navController.navigate("itemEstoque")
                                },
                                onListaEstoqueCadastrarEstoqueClick = {
                                    clickedAction = "Ação 6"
                                    navController.navigate("cadastrarItemEstoque")
                                }
                            )
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

                        composable("comandaView") {
                            ComandaViewScreen(
                                onComandaViewCancelarClick = {
                                    clickedAction = "Cancelar"
                                },
                                onComandaViewEnviarClick = {
                                    clickedAction = "Enviar"
                                },
                                onComandaViewAcao1Click = {
                                    clickedAction = "Ação 1"
                                    navController.navigate("comandaList")
                                },
                                onComandaViewAcao2Click = {
                                    clickedAction = "Ação 2"
                                    navController.navigate("cardapio")
                                },
                                onComandaViewAcao3Click = {
                                    clickedAction = "Ação 3"
                                    navController.navigate("viewPerfil")
                                },
                                onComandaViewVoltarClick = {
                                    clickedAction = "Voltar"
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable("itemEstoque") {
                            ItemEstoqueScreen(
                                onItemEstoqueViewVoltarClick = {
                                    clickedAction = "Voltar"
                                    navController.popBackStack()
                                },
                                onItemEstoqueViewEditarClick = {
//                                    clickedAction = "Editar"
                                },
                                onItemEstoqueViewExcluirClick = {
                                    clickedAction = "Excluir"
                                    navController.navigate("deleteConfirmação")
                                },
                                onItemEstoqueViewAcao1Click = {
                                    clickedAction = "Ação 1"
                                    navController.navigate("listaFornecedor")
                                },
                                onItemEstoqueViewAcao2Click = {
                                    clickedAction = "Ação 2"
                                    navController.navigate("listaEstoque")
                                },
                                onItemEstoqueViewAcao3Click = {
                                    clickedAction = "Ação 3"
                                    navController.navigate("listaCompras")
                                },
                                onItemEstoqueViewAcao4Click = {
                                    clickedAction = "Ação 4"
                                    navController.navigate("viewPerfil")
                                }
                            )
                        }

                        composable("deleteConfirmação") {
                            DeleteCnfirmacaoScreen(
                                onDeleteConfirmacaoConfirmarClick = {
                                    clickedAction = "Deletar"
                                    navController.navigate("listaEstoque")
                                },
                                onDeleteConfirmacaoCancelarClick = {
                                    clickedAction = "Cancelar"
                                    navController.popBackStack()
                                },
                                onDeleteConfirmacaoAcao1Click = {
                                    clickedAction = "Ação 1"
                                    navController.navigate("listaFornecedor")
                                },
                                onIDeleteConfirmacaoAcao2Click = {
                                    clickedAction = "Ação 2"
                                    navController.navigate("listaEstoque")
                                },
                                onDeleteConfirmacaoAcao3Click = {
                                    clickedAction = "Ação 3"
                                    navController.navigate("listaCompras")
                                },
                                onDeleteConfirmacaoAcao4Click = {
                                    clickedAction = "Ação 4"
                                    navController.navigate("viewPerfil")
                                }
                            )
                        }

                        composable("cadastrarItemEstoque") {
                            CadastroItemScreen(
                                onCadastroItemVoltarClick = {
                                    clickedAction = "Voltar"
                                    navController.popBackStack()
                                },
                                onCadastroItemProximoClick = {
                                    clickedAction = "Próximo"
                                    navController.navigate("cadastrarItemEstoque2")
                                }
                            )
                        }

                        composable("cadastrarItemEstoque2") {
                            CadastroItem2Screen(
                                onCadastroItem2AnteriorClick = {
                                    clickedAction = "Voltar"
                                    navController.popBackStack()
                                },
                                onCadastroItemCadastrarClick ={
                                    clickedAction = "Cadastrar"
                                    navController.navigate("listaEstoque")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}