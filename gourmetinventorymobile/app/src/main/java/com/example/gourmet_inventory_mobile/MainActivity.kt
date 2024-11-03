package com.example.gourmet_inventory_mobile

import CadastroItemScreen
import SharedViewModel
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import com.example.gourmet_inventory_mobile.model.Fornecedor
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueConsulta
import com.example.gourmet_inventory_mobile.screens.CadastroItem2Screen
import com.example.gourmet_inventory_mobile.screens.CardapioListScreen
import com.example.gourmet_inventory_mobile.screens.ComandaListScreen
import com.example.gourmet_inventory_mobile.screens.ComandaViewScreen
import com.example.gourmet_inventory_mobile.screens.DeleteCnfirmacaoScreen
import com.example.gourmet_inventory_mobile.screens.Editar2Screen
import com.example.gourmet_inventory_mobile.screens.EditarScreen
import com.example.gourmet_inventory_mobile.screens.EscolhaPerfilScreen
import com.example.gourmet_inventory_mobile.screens.ItemEstoqueScreen
import com.example.gourmet_inventory_mobile.screens.ListaComprasScreen
import com.example.gourmet_inventory_mobile.screens.ListaEstoqueScreen
import com.example.gourmet_inventory_mobile.screens.ListaFornecedoresScreen
import com.example.gourmet_inventory_mobile.screens.LoginScreen
import com.example.gourmet_inventory_mobile.screens.PratoScreen
import com.example.gourmet_inventory_mobile.screens.ViewPerfilScreen
import com.example.gourmet_inventory_mobile.screens.VizuFornScreen
import com.example.gourmet_inventory_mobile.ui.theme.GourmetinventorymobileTheme
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
import com.example.gourmet_inventory_mobile.viewmodel.EstoqueViewModel
import com.example.gourmet_inventory_mobile.viewmodel.FornViewModel
import com.example.gourmet_inventory_mobile.viewmodel.ListaComprasViewModel
import com.example.gourmet_inventory_mobile.viewmodel.PratoViewModel
import kotlinx.coroutines.flow.first
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
                                viewModel = viewModelPrato,
                                navController = navController,
                                onCardapioClick = { route ->
                                    navController.navigate(route)
                                }
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
                                            navController.navigate("editarItemEstoque")
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

                        composable("editarItemEstoque") {
                            EditarScreen(
                                onEditarItem1ProximoClick = {
                                    clickedAction = "Próximo"
                                    navController.navigate("editarItemEstoque2")
                                },
                                onEditarItem1AnteriorClick = {
                                    clickedAction = "Anterior"
                                    navController.popBackStack()
                                }
                            )
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
                                }
                            )
                        }


                    }
                }
            }
        }
    }
}