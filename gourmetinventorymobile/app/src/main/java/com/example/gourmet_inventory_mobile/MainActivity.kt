package com.example.gourmet_inventory_mobile

import ItemEstoqueManipuladoScreen
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
import com.example.gourmet_inventory_mobile.model.Usuario.User
import com.example.gourmet_inventory_mobile.model.estoque.EstoqueItemDiscriminator
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
import com.example.gourmet_inventory_mobile.screens.Estoque.Manipulado.CadastroItemManipulavel2Screen
import com.example.gourmet_inventory_mobile.screens.Estoque.Manipulado.CadastroItemManipulavelScreen
import com.example.gourmet_inventory_mobile.screens.Estoque.Manipulado.EdicaoItemManipulavel2Screen
import com.example.gourmet_inventory_mobile.screens.Estoque.Manipulado.EdicaoItemManipulavelScreen
import com.example.gourmet_inventory_mobile.screens.Usuario.ViewPerfilScreen
import com.example.gourmet_inventory_mobile.screens.Fornecedor.VizuFornScreen
import com.example.gourmet_inventory_mobile.ui.theme.GourmetinventorymobileTheme
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
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
                    var user: User? = null
                    var startDestination by remember { mutableStateOf<String?>(null) }

                    LaunchedEffect(Unit) {
                        val dataStore = DataStoreUtils(appContext)
                        val user = dataStore.obterUsuario()

                        startDestination = when {
                            user == null || user.toString().isBlank() -> "login"
                            user.cargo == getString(R.string.gerente) -> "perfil"
                            else -> "cardapio"
                        }

                        Log.d("MainActivity", "user: $user, startDestination: $startDestination")
                    }

                    // Exibe apenas o NavHost após a definição de startDestination
                    if (startDestination != null) {
                        NavHost(
                            navController = navController,
                            startDestination = startDestination!!
                        ) {

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
//                            Log.d("MainActivity", "login: $user")

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

                            composable("comandaList") {
                                LaunchedEffect(Unit) {
                                    viewModelComanda.data.clear()
                                    viewModelComanda.getComandas()
                                }
                                ComandaListScreen(
                                    navController = navController,
                                    onComandaClick = { route ->
                                        navController.navigate(route)
                                    },
                                )
                            }

                            composable("cardapioItem/{idPrato}") { backStackEntry ->
                                val idPrato =
                                    backStackEntry.arguments?.getString("idPrato")?.toIntOrNull()

                                idPrato?.let { id ->
                                    val prato =
                                        viewModelPrato.data.find { it.idPrato == id.toLong() }

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


                            composable("viewPerfil") {
                                ViewPerfilScreen(
                                    navController = navController,
                                    onViewPerfil = { route ->
                                        if (route == "login") {
                                            navController.navigate("login") {
                                                popUpTo("root") {
                                                    inclusive = true
                                                } // Remove as telas da pilha de navegação
                                            }
                                        } else {
                                            navController.navigate(route)
                                        }
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
                                val idFornecedor =
                                    backStackEntry.arguments?.getString("idFornecedor")
                                        ?.toIntOrNull()
                                val viewModel = koinViewModel<FornViewModel>()

                                idFornecedor?.let { id ->
                                    val fornecedor =
                                        viewModel.data.find { it.idFornecedor == id.toLong() }

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

                            composable("comandaView/{idComanda}") { backStackEntry ->
                                val idComanda =
                                    backStackEntry.arguments?.getString("idComanda")?.toIntOrNull()
                                Log.d("MainActivity", "idComanda: $idComanda")

//                            LaunchedEffect(idComanda) {
//                                viewModelComanda.getComandas()
//                            }

                                val comanda =
                                    viewModelComanda.data.find { it.id == idComanda?.toLong() }
                                Log.d("MainActivity", "comanda: $comanda")

                                ComandaViewScreen(
                                    comanda = comanda,
                                    viewModel = viewModelComanda,
                                    navController = navController,
                                    onComandaViewClick = { route ->
                                        navController.navigate(route)
                                    },
                                    onComandaViewVoltarClick = {
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

                            composable("deleteConfirmacao/{idItem}") { backStackEntry ->
                                // Obtém o idItem como String e converte para Long (ou Int, dependendo do seu modelo)
                                val idItem =
                                    backStackEntry.arguments?.getString("idItem")?.toLongOrNull()

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

                            composable("cadastrarItemEstoqueManilpulado") {
                                CadastroItemManipulavelScreen(
                                    sharedViewModel = sharedViewModel,
                                    onCadastroItemManipuladoProximoClick = {
                                        Log.d(
                                            "MainActivity - onCadastroItemProximoClick()",
                                            "estoque: ${sharedViewModel.estoque.value}"
                                        )
                                        navController.navigate("cadastrarItemEstoqueManipulado2")
                                    },
                                    onCadastroItemManipuladoVoltarClick = {
                                        navController.popBackStack()
                                    }
                                )
                            }

                            composable("cadastrarItemEstoqueManipulado2") {
                                CadastroItemManipulavel2Screen(
                                    estoqueViewModel = viewModelEstoque,
                                    sharedViewModel = sharedViewModel,
                                    onCadastroItemManipulavel2AnteriorClick = {
                                        navController.popBackStack()
                                    },
                                    onCadastroItemManipulavelCadastrarClick = { estoqueConsulta ->
                                        navController.navigate("listaEstoque")
//                                    { popUpTo("cadastrarItemEstoque2") { inclusive = true } }
                                    },
                                )
                            }

                            composable("editarItemEstoque/{idItem}") { backStackEntry ->
                                val idItem =
                                    backStackEntry.arguments?.getString("idItem")?.toIntOrNull()

                                idItem?.let { id ->
//                                val estoque = (viewModelEstoque.estoqueConsultaState.value as? EstoqueConsultaState.Success)?.estoqueConsulta?.find { it.idItem == id.toLong() }
                                    val listaEstoque =
                                        (viewModelEstoque.estoqueConsultaState.value as? EstoqueConsultaState.Success)?.estoqueConsulta
                                            ?: emptyList()

                                    val itemEstoque =
                                        (viewModelEstoque.estoqueConsultaState.value as? EstoqueConsultaState.Success)
                                            ?.estoqueConsulta
                                            ?.mapNotNull { it as? EstoqueItemDiscriminator.Industrializado }
                                            ?.find { it.idItem == id.toLong() }
                                    Log.d(
                                        "MainActivity",
                                        "editarItemEstoque - estoque: $itemEstoque"
                                    )

                                    itemEstoque?.let { estoque ->
                                        EditarScreen(
                                            estoque = estoque,
                                            sharedViewModel = sharedViewModel,
                                            onEditarItemVoltarClick = {
                                                clickedAction = "Voltar"
                                                navController.popBackStack()
                                            },
                                            onEditarItemProximoClick = {
                                                navController.navigate("editarItemEstoque2/$idItem")
                                            }
                                        )
                                    }
                                }
                            }


                            composable("editarItemEstoque2/{idItem}") { backStackEntry ->
                                val idItem =
                                    backStackEntry.arguments?.getString("idItem")?.toLongOrNull()
                                if (idItem != null) {
                                    Editar2Screen(
                                        estoqueViewModel = viewModelEstoque,
                                        onEditarItem2SalvarClick = {
                                            clickedAction = "Salvar"
                                            navController.navigate("listaEstoque")
                                        },
                                        onEditarItem2AnteriorClick = {
                                            clickedAction = "Anterior"
                                            navController.popBackStack()
                                        },
                                        sharedViewModel = sharedViewModel,
                                        idItem = idItem
                                    )
                                }
                            }

                            composable("itemEstoque/{estoqueItem.idItem}") { backStackEntry ->
                                val idItem =
                                    backStackEntry.arguments?.getString("estoqueItem.idItem")
                                        ?.toIntOrNull()

                                idItem?.let { id ->
//                                val itemEstoque =
//                                    viewModelEstoque.data.find { it.idItem == id.toLong() }
                                    val itemEstoque =
                                        (viewModelEstoque.estoqueConsultaState.value as? EstoqueConsultaState.Success)
                                            ?.estoqueConsulta
                                            ?.mapNotNull { it as? EstoqueItemDiscriminator.Industrializado }
                                            ?.find { it.idItem == id.toLong() }

                                    Log.d("MainActivity", "itemEstoque - estoque: $itemEstoque")
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

                            composable("itemEstoqueManipulado/{estoqueItem.idItem}") { backStackEntry ->
                                val idItem =
                                    backStackEntry.arguments?.getString("estoqueItem.idItem")
                                        ?.toLongOrNull()

                                idItem?.let { id ->
                                    val itemEstoque =
                                        (viewModelEstoque.estoqueConsultaState.value as? EstoqueConsultaState.Success)
                                            ?.estoqueConsulta
                                            ?.mapNotNull { it as? EstoqueItemDiscriminator.Manipulado }
                                            ?.find { it.idItem == id }

                                    Log.d("MainActivity", "estoque: $itemEstoque")
                                    Log.d("MainActivity", "idItem: $idItem")

                                    itemEstoque?.let { estoqueConsulta ->
                                        ItemEstoqueManipuladoScreen(
                                            estoqueConsulta = estoqueConsulta,
                                            onItemManipuladoEstoqueClick = {
                                                clickedAction = "Voltar"
                                                navController.navigate("listaEstoque")
                                            },
                                            onItemEstoqueManipuladoViewEditarClick = {
                                                clickedAction = "Editar"
                                                navController.navigate("editarItemEstoqueManipulado/$idItem")
                                            },
                                            onItemEstoqueManipuladoViewExcluirClick = {
                                                clickedAction = "Excluir"
                                                navController.navigate("deleteConfirmacao/$idItem")
                                            }
                                        )
                                    }
                                }
                            }
                            composable("editarItemEstoqueManipulado/{idItem}") { backStackEntry ->
                                val idItem =
                                    backStackEntry.arguments?.getString("idItem")?.toIntOrNull()

                                idItem?.let { id ->
//                                val estoque = (viewModelEstoque.estoqueConsultaState.value as? EstoqueConsultaState.Success)?.estoqueConsulta?.find { it.idItem == id.toLong() }
                                    val listaEstoque =
                                        (viewModelEstoque.estoqueConsultaState.value as? EstoqueConsultaState.Success)?.estoqueConsulta
                                            ?: emptyList()

                                    val itemEstoque =
                                        (viewModelEstoque.estoqueConsultaState.value as? EstoqueConsultaState.Success)
                                            ?.estoqueConsulta
                                            ?.mapNotNull { it as? EstoqueItemDiscriminator.Manipulado }
                                            ?.find { it.idItem == id.toLong() }
                                    Log.d(
                                        "MainActivity",
                                        "editarItemEstoqueManipulado - estoque: $itemEstoque"
                                    )

                                    itemEstoque?.let { estoque ->
                                        EdicaoItemManipulavelScreen(
                                            estoque = estoque,
                                            sharedViewModel = sharedViewModel,
                                            onEdicaoItemManipuladoVoltarClick = {
                                                clickedAction = "Voltar"
                                                navController.popBackStack()
                                            },
                                            onEdicaoItemManipuladoProximoClick = {
                                                navController.navigate("editarItemEstoqueManipulado2/$idItem")
                                            },
                                            estoqueViewModel = viewModelEstoque
                                        )
                                    }
                                }
                            }

                            composable("editarItemEstoqueManipulado2/{idItem}") { backStackEntry ->
                                val idItem =
                                    backStackEntry.arguments?.getString("idItem")?.toIntOrNull()

                                idItem?.let { id ->
//                                val estoque = (viewModelEstoque.estoqueConsultaState.value as? EstoqueConsultaState.Success)?.estoqueConsulta?.find { it.idItem == id.toLong() }
                                    val listaEstoque =
                                        (viewModelEstoque.estoqueConsultaState.value as? EstoqueConsultaState.Success)?.estoqueConsulta
                                            ?: emptyList()

                                    val itemEstoque =
                                        (viewModelEstoque.estoqueConsultaState.value as? EstoqueConsultaState.Success)
                                            ?.estoqueConsulta
                                            ?.mapNotNull { it as? EstoqueItemDiscriminator.Manipulado }
                                            ?.find { it.idItem == id.toLong() }
                                    Log.d(
                                        "MainActivity",
                                        "editarItemEstoqueManipulado2 - estoque: $itemEstoque"
                                    )

                                    itemEstoque?.let { estoque ->
                                        EdicaoItemManipulavel2Screen(
                                            estoqueViewModel = viewModelEstoque,
                                            sharedViewModel = sharedViewModel,
                                            onCadastroItemManipulavel2AnteriorClick = {
                                                clickedAction = "Voltar"
                                                navController.popBackStack()
                                            },
                                            onCadastroItemManipulavelCadastrarClick = {
                                                clickedAction = "Salvar"
                                                navController.navigate("listaEstoque")
                                            },
                                            idItem = id.toLong()
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}