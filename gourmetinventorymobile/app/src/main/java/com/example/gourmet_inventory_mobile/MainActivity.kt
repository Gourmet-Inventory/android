package com.example.gourmet_inventory_mobile

import CadastroItemScreen
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
                            CardapioListScreen(
                                navController = navController,
                                onCardapioClick = { route ->
                                    navController.navigate(route)
                                }
                            )
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
                            ListaComprasScreen(
                                navController = navController,
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
//                            val viewModel = koinViewModel<EstoqueViewModel>()

                            idItem?.let { id ->
                                val itemEstoque = viewModelEstoque.data.find { it.idItem == id.toLong() }
                                Log.d("MainActivity", "estoque: $itemEstoque")

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
                                            navController.navigate("deleteConfirmação")
                                        }
                                    )
                                }

                            }


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
                                }
                            )
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

                        composable("cardapioItem") {
                            PratoScreen(
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
            }
        }
    }
}