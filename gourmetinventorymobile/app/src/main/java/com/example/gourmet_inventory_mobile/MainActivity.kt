package com.example.gourmet_inventory_mobile

import com.example.gourmet_inventory_mobile.screens.Estoque.Industrializado.CadastroItemScreen
import com.example.gourmet_inventory_mobile.screens.Estoque.Industrializado.EditarScreen
import SharedViewModel
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gourmet_inventory_mobile.DI.appModule
import com.example.gourmet_inventory_mobile.screens.Estoque.Industrializado.CadastroItem2Screen
import com.example.gourmet_inventory_mobile.screens.Cardapio.CardapioListScreen
import com.example.gourmet_inventory_mobile.screens.Comanda.ComandaListScreen
import com.example.gourmet_inventory_mobile.screens.Comanda.ComandaViewScreen
import com.example.gourmet_inventory_mobile.screens.DeleteCnfirmacaoScreen
import com.example.gourmet_inventory_mobile.screens.Estoque.Industrializado.Editar2Screen
import com.example.gourmet_inventory_mobile.screens.Usuario.EscolhaPerfilScreen
import com.example.gourmet_inventory_mobile.screens.Estoque.Industrializado.ItemEstoqueScreen
import com.example.gourmet_inventory_mobile.screens.ListaComprasScreen
import com.example.gourmet_inventory_mobile.screens.Estoque.ListaEstoqueScreen
import com.example.gourmet_inventory_mobile.screens.Fornecedor.ListaFornecedoresScreen
import com.example.gourmet_inventory_mobile.screens.Usuario.LoginScreen
import com.example.gourmet_inventory_mobile.screens.Cardapio.PratoScreen
import com.example.gourmet_inventory_mobile.screens.Usuario.ViewPerfilScreen
import com.example.gourmet_inventory_mobile.screens.Fornecedor.VizuFornScreen
import com.example.gourmet_inventory_mobile.ui.theme.GourmetinventorymobileTheme
import com.example.gourmet_inventory_mobile.viewmodel.ComandaViewModel
import com.example.gourmet_inventory_mobile.viewmodel.EstoqueConsultaState
import com.example.gourmet_inventory_mobile.viewmodel.EstoqueViewModel
import com.example.gourmet_inventory_mobile.viewmodel.FornViewModel
import com.example.gourmet_inventory_mobile.viewmodel.ListaComprasViewModel
import com.example.gourmet_inventory_mobile.viewmodel.PratoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var appContext: Context
            private set
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin() {
            androidContext(this@MainActivity)
            modules(appModule)
        }
        appContext = applicationContext
        setContent {
            GourmetinventorymobileTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    var clickedAction by remember { mutableStateOf("") }
                    val sharedViewModel: SharedViewModel =
                        androidx.lifecycle.viewmodel.compose.viewModel()
                    val estoque by sharedViewModel.estoque.collectAsState()
                    val viewModelEstoque = koinViewModel<EstoqueViewModel>()
                    val viewModelListaCompras = koinViewModel<ListaComprasViewModel>()
                    val viewModelPrato = koinViewModel<PratoViewModel>()
                    val viewModelComanda = koinViewModel<ComandaViewModel>()

                    NavHost(navController = navController, startDestination = "login") {

                        composable("perfil") {
                            EscolhaPerfilScreen(onPerfilClick = { perfil ->
                                val destination =
                                    if (perfil == resources.getString(R.string.garcom)) {
                                        "cardapio"
                                    } else {
                                        "listaEstoque"
                                    }
                                navController.navigate(destination)
                            })
                        }

                        composable("login") {
                            LoginScreen(onLoginClick = { user ->
//                                navController.currentBackStackEntry?.savedStateHandle?.set(
//                                    "user", user
//                                )
                                if (user.cargo == resources.getString(R.string.garcom)) {
                                    navController.navigate("cardapio")
                                } else {
                                    navController.navigate("perfil")
                                }
                            })
                        }

                        composable("cardapio") {
                            val context = LocalContext.current
                            LaunchedEffect(Unit) {
                                viewModelPrato.getPratos(context)
                            }

                            CardapioListScreen(
                                pratoViewModel = viewModelPrato,
                                navController = navController,
                                onCardapioClick = { route ->
                                    navController.navigate(route)
                                },
                                comandaViewModel = viewModelComanda
                            )
                        }

                        composable("cardapioItem/{idPrato}") {backStackEntry ->
                            val idPrato = backStackEntry.arguments?.getString("idPrato")?.toIntOrNull()

                            idPrato?.let { id ->
                                val prato = viewModelPrato.data.find { it.idPrato == id.toLong() }

                                prato?.let { prato ->
                                    PratoScreen(
                                        prato = prato,
                                        navController = navController,
                                        onClickPratoItem = { route ->
                                            navController.navigate(route)
                                        },
                                        onPratoItemVoltarClick = {
                                            clickedAction = "Voltar"
                                            navController.popBackStack()
                                        },
                                        viewModel = viewModelComanda
                                    )
                                }
                            }


                        }

                        composable("comandaList") {
                            ComandaListScreen(
                                navController = navController,
                                onComandaClick = { route ->
                                    navController.navigate(route)
                                },
                            )
                        }

                        composable("viewPerfil") {
                            ViewPerfilScreen(
                                navController = navController,
                                onViewPerfil = { route ->
                                    navController.navigate(route)
                                }
                            )
                        }

                        composable("listaFornecedor") {
                            ListaFornecedoresScreen(
                                navController = navController,
                                fornecedorId = null,
                                onListaFornecedoresClick = { route ->
                                    navController.navigate(route)
                                }
                            )
                        }

                        composable("fornecedorView/{idFornecedor}") { backStackEntry ->
                            val idFornecedor = backStackEntry.arguments?.getString("idFornecedor")?.toIntOrNull()
                            val viewModel = koinViewModel<FornViewModel>()

                            idFornecedor?.let { id ->
                                val fornecedor = viewModel.data.find { it.idFornecedor == id.toLong() }

                                fornecedor?.let { forn ->
                                    VizuFornScreen(
                                        fornecedor = forn,
                                        onVizuFornVoltarClick = {
                                            navController.popBackStack()
                                        }
                                    )
                                }
                            }
                        }

                        composable("listaEstoque") {
                            val context = LocalContext.current
                            LaunchedEffect(Unit) {
                                viewModelEstoque.obterListaEstoque(context)
                            }
                            ListaEstoqueScreen(
                                navController = navController,
                                viewModel = viewModelEstoque,
                                onListaEstoqueClick = { route ->
                                    Log.d("MainActivity", "route: $route")
                                    navController.navigate(route)
                                }
                            )
                        }

                        composable("listaCompras") {
                            LaunchedEffect(Unit) {
                                viewModelListaCompras.getListaCompras(appContext)
                            }
                            ListaComprasScreen(
                                navController = navController,
                                viewModel = viewModelListaCompras,
                                onListaComprasClick = { route ->
                                    navController.navigate(route)
                                }
                            )

                        }

                        composable("comandaView") {
                            ComandaViewScreen(
                                viewModel = viewModelComanda,
                                navController = navController,
                                onComandaViewClick = { route ->
                                    navController.navigate(route)
                                },
                                onComandaViewVoltarClick = {
                                    clickedAction = "Voltar"
                                    navController.popBackStack()
                                }
                            )
                        }

//                        composable("itemEstoque") {
//                            ItemEstoqueScreen(
//                                onItemEstoqueClick = {
//                                    clickedAction = "Voltar"
//                                    navController.navigate("listaEstoque")
//                                },
//                                onItemEstoqueViewEditarClick = {
//                                    clickedAction = "Editar"
//                                    navController.navigate("editarItemEstoque")
//                                },
//                                onItemEstoqueViewExcluirClick = {
//                                    clickedAction = "Excluir"
//                                    navController.navigate("deleteConfirmação")
//                                }
//                            )
//                        }

//                        composable("fornecedorView/{idFornecedor}") { backStackEntry ->
//                            val idFornecedor = backStackEntry.arguments?.getString("idFornecedor")?.toIntOrNull()
//                            val viewModel = koinViewModel<FornViewModel>()
//
//                            idFornecedor?.let { id ->
//                                val fornecedor = viewModel.data.find { it.idFornecedor == id.toLong() }
//
//                                fornecedor?.let { forn ->
//                                    VizuFornScreen(
//                                        fornecedor = forn,
//                                        onVizuFornVoltarClick = {
//                                            navController.popBackStack()
//                                        }
//                                    )
//                                }

                        composable("itemEstoque/{idItem}") { backStackEntry ->
                            val idItem = backStackEntry.arguments?.getString("idItem")?.toIntOrNull()

                            idItem?.let { id ->
                                val itemEstoque = viewModelEstoque.data.find { it.idItem == id.toLong() }
                                Log.d("MainActivity", "estoque: $itemEstoque")
                                Log.d("MainActivity", "idItem: $idItem")

                                itemEstoque?.let { estoqueConsulta ->
                                    ItemEstoqueScreen(
                                        estoqueConsulta = estoqueConsulta,
                                        onItemEstoqueClick = {
                                            clickedAction = "Voltar"
                                            navController.navigate("listaEstoque")
                                        },
                                        onItemEstoqueViewEditarClick = {
                                            clickedAction = "Editar"
                                            navController.navigate("editarItemEstoque/$idItem")
                                        },
                                        onItemEstoqueViewExcluirClick = {
                                            clickedAction = "Excluir"
                                            navController.navigate("deleteConfirmacao/${idItem}")
                                        }
                                    )
                                }

                            }


                        }

                        composable("deleteConfirmacao/{idItem}") { backStackEntry ->
                            // Obtém o idItem como String e converte para Long (ou Int, dependendo do seu modelo)
                            val idItem = backStackEntry.arguments?.getString("idItem")?.toLongOrNull()

                            if (idItem != null) {
                                DeleteCnfirmacaoScreen(
                                    viewModel = viewModelEstoque, // Certifique-se de passar a instância correta da ViewModel
                                    idItem = idItem, // Passa o ID do item para a tela de confirmação
                                    onDeleteConfirmacaoConfirmarClick = {
                                        // Chame a função de deletar do ViewModel
                                        viewModelEstoque.deletarEstoque(idItem)

                                        // Navegar para a lista de estoque após a exclusão
                                        navController.navigate("listaEstoque")
                                    },
                                    onDeleteConfirmacaoCancelarClick = {
                                        navController.popBackStack()
                                    }
                                )
                            } else {
                                Log.d("MainActivity", "ID do item inválido")
                                navController.popBackStack() // Volta para a tela anterior se o ID for inválido
                            }
                        }


                        composable("cadastrarItemEstoque") {
                            CadastroItemScreen(
                                sharedViewModel = sharedViewModel,
                                onCadastroItemProximoClick = {
                                    Log.d(
                                        "MainActivity - onCadastroItemProximoClick()",
                                        "estoque: ${sharedViewModel.estoque.value}"
                                    )
                                    navController.navigate("cadastrarItemEstoque2")
                                },
                                onCadastroItemVoltarClick = {
                                    navController.popBackStack()
                                }
                            )
                        }

                        composable("cadastrarItemEstoque2") {
                            CadastroItem2Screen(
                                estoque = estoque,
                                onCadastroItem2AnteriorClick = {
                                    navController.popBackStack()
                                },
                                onCadastroItemCadastrarClick = { estoqueConsulta ->
                                    navController.navigate("listaEstoque")
//                                    { popUpTo("cadastrarItemEstoque2") { inclusive = true } }
                                },
                                sharedViewModel = sharedViewModel
                            )
                        }

                        composable("editarItemEstoque/{idItem}") { backStackEntry ->
                            val idItem = backStackEntry.arguments?.getString("idItem")?.toIntOrNull()
//                            val viewModel = koinViewModel<EstoqueViewModel>()

                            idItem?.let { id ->
                                val estoque = (viewModelEstoque.estoqueConsultaState.value as? EstoqueConsultaState.Success)?.estoqueConsulta?.find { it.idItem == id.toLong() }

                                estoque?.let { estoque ->
                                    EditarScreen(
                                        estoque = estoque,
                                        sharedViewModel = sharedViewModel,
                                        onEditarItemVoltarClick = {
                                            clickedAction = "Voltar"
                                            navController.popBackStack()
                                        },
                                        onEditarItemProximoClick = {
                                            navController.navigate("editarItemEstoque2")
                                        }
                                    )
                                }
                            }
                        }

                        composable("editarItemEstoque2") {
                            Editar2Screen(
                                onEditarItem2SalvarClick = {
                                    clickedAction = "Salvar"
                                    navController.navigate("listaEstoque")
                                },
                                onEditarItem2AnteriorClick = {
                                    clickedAction = "Anterior"
                                    navController.popBackStack()
                                },
                                sharedViewModel = sharedViewModel
                            )
                        }


                    }
                }
            }
        }
    }
}