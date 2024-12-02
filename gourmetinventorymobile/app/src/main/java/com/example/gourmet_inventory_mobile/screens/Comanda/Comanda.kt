package com.example.gourmet_inventory_mobile.screens.Comanda

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.model.Comanda
import com.example.gourmet_inventory_mobile.model.Prato
import com.example.gourmet_inventory_mobile.repository.estoque.ComandaRepositoryImplLocal
import com.example.gourmet_inventory_mobile.ui.theme.Black
import com.example.gourmet_inventory_mobile.ui.theme.GI_AzulMarinho
import com.example.gourmet_inventory_mobile.ui.theme.GI_BrancoFundo
import com.example.gourmet_inventory_mobile.ui.theme.GI_Laranja
import com.example.gourmet_inventory_mobile.ui.theme.GI_Verde
import com.example.gourmet_inventory_mobile.ui.theme.JostBold
import com.example.gourmet_inventory_mobile.ui.theme.White
import com.example.gourmet_inventory_mobile.utils.DrawScrollableView
import com.example.gourmet_inventory_mobile.viewmodel.ComandaAtualizacaoState
import com.example.gourmet_inventory_mobile.viewmodel.ComandaCriacaoSate
import com.example.gourmet_inventory_mobile.viewmodel.ComandaDelecaoState
import com.example.gourmet_inventory_mobile.viewmodel.ComandaViewModel

@Composable
fun ComandaViewScreen(
    comanda: Comanda?,
    viewModel: ComandaViewModel,
    navController: NavController,
    onComandaViewClick: (String) -> Unit,
    onComandaViewVoltarClick: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
//    val pedidos = viewModel.listaPratosComanda
    val pedidos by viewModel.listaPratosComanda.collectAsState()

    val comandaCriacaoSate by viewModel.comandaCriacaoState.collectAsState()
    val comandaCriacao = viewModel.comandaAtual.collectAsState()
    val comandaDelecaoState by viewModel.deletarComandaState.collectAsState()
    val comandaAtualizacaoState by viewModel.atualizarComandaState.collectAsState()

    val resouce = context.resources

    val textoStatus =
        if (comanda != null)
            when (comanda.status) {
                resouce.getString(R.string.comandaStatus0) -> resouce.getString(R.string.comandaStatus0_5Texto)
                resouce.getString(R.string.comandaStatus1) -> resouce.getString(R.string.comandaStatus1Texto)
                resouce.getString(R.string.comandaStatus2) -> resouce.getString(R.string.comandaStatus2Texto)
                resouce.getString(R.string.comandaStatus3) -> resouce.getString(R.string.comandaStatus3Texto)
                else -> {
                    resouce.getString(R.string.comandaStatus0Texto)
                }
            }
        else resouce.getString(R.string.comandaStatus0Texto)


    val total =
        if (comanda == null) pedidos.sumOf { it.preco }
        else comanda.itens.sumOf { it.preco }
    Log.d("ComandaViewScreen", "total: $total")

//    var isSent by remember { mutableStateOf(false) } // Estado do status
//    val titulo by remember { mutableStateOf("") }
//    val mesa by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { onComandaViewVoltarClick() }, modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Voltar",
                    )
                }
            }
        }) { padding ->
        Surface(
            color = Color.White,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp, start = 16.dp, bottom = 10.dp, top = 20.dp)
                ) {
                    Text(
                        text = if (comanda != null) "Comanda: ${comanda!!.id.toString()}" else "Comanda ...",
                        fontSize = 34.sp
                    )
                    Text(
                        text = if (comanda != null) "Mesa: ${comanda!!.mesa}" else "Mesa ...",
                        fontSize = 22.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(500.dp)
                        .background(GI_BrancoFundo, RoundedCornerShape(8.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (comanda == null) {
                                Button(
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = GI_AzulMarinho, contentColor = Color.White
                                    ),
                                    onClick = {
                                        onComandaViewClick("cardapio")
                                    }
                                ) {
                                    Text(text = "Adicionar pedido")
                                }
                            }
                        }

                        if (comanda == null) {
                            Divider(
                                color = Black,
                                thickness = 1.dp,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        } else {
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        ItensComanda(
                            comanda = comanda,
                            if (comanda == null) pedidos else comanda.itens,
                            viewModel,
                            onComandaViewClick
                        )

                        Divider(
                            color = Black,
                            thickness = 1.dp,
                            modifier = Modifier.padding(
                                bottom = 8.dp,
                                top = if (comanda == null) 8.dp else 50.dp
                            )
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = "Total:",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "R$$total",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                val statusAtt =
                    when (comanda?.status) {
                        resouce.getString(R.string.comandaStatus0) -> resouce.getString(
                            R.string.comandaStatus1
                        )

                        resouce.getString(R.string.comandaStatus1) -> resouce.getString(
                            R.string.comandaStatus2
                        )

                        resouce.getString(R.string.comandaStatus2) -> resouce.getString(
                            R.string.comandaStatus3
                        )

                        resouce.getString(R.string.comandaStatus3) -> resouce.getString(
                            R.string.comandaStatus3
                        )

                        else -> {
                            resouce.getString(R.string.comandaStatus0)
                        }
                    }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = {
                            if (comanda != null) {
                                if (comanda.status == "Finalizada") {
                                    Toast.makeText(
                                        context,
                                        "Comanda já finalizada",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    viewModel.updateComandaStatus(comanda!!.id!!, comanda.status, context)
                                    onComandaViewClick("comandaList")
                                    Toast.makeText(
                                        context,
                                        "Comanda atualizada",
                                        Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                showDialog = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GI_Verde, contentColor = Color.White
                        ),
                        modifier = Modifier
                            .weight(1f)
                            //        .padding(end = 8.dp)
                            .height(45.dp),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        if (comandaAtualizacaoState is ComandaAtualizacaoState.Loading) {
                            CircularProgressIndicator(color = GI_AzulMarinho)
                        }
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Enviar"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (comanda != null) "Atualizar p/ ${statusAtt}" else "Enviar",
                            Modifier.horizontalScroll(
                                enabled = true,
                                reverseScrolling = true,
                                state = rememberScrollState()
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Botão de cancelar comanda ou deletar comanda se já enviada
                    if (comanda?.status == "Enviada" || comanda == null) {
                        Button(
                            onClick = {
                                if (comanda != null) {
                                    comanda!!.id?.let {
                                        viewModel.deleteComanda(it)
                                        onComandaViewClick("comandaList")
                                        Toast.makeText(
                                            context,
                                            "Comanda deletada",
                                            Toast.LENGTH_SHORT).show()
                                    }
                                } else {
                                    viewModel.limparComanda()
                                    onComandaViewClick("cardapio")
                                    Toast.makeText(context, "Comanda cancelada", Toast.LENGTH_SHORT).show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red, contentColor = Color.White
                            ),
                            modifier = Modifier
//                                .weight(1f)
//                                .padding(start = 8.dp)
                                .width(140.dp)
                                .height(45.dp),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Deletar"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (comanda != null) "Deletar" else "Cancelar",
                                Modifier.horizontalScroll(
                                    enabled = true,
                                    reverseScrolling = true,
                                    state = rememberScrollState()
                                )
                            )
//                            when (comandaDelecaoState) {
//                                is ComandaDelecaoState.Idle -> {
//                                    Icon(
//                                        imageVector = Icons.Default.Delete,
//                                        contentDescription = "Deletar"
//                                    )
//                                    Spacer(modifier = Modifier.width(8.dp))
//                                    Text(
//                                        text = if (comanda != null) "Deletar comanda" else "Cancelar",
//                                        Modifier.horizontalScroll(
//                                            enabled = true,
//                                            reverseScrolling = true,
//                                            state = rememberScrollState()
//                                        )
//                                    )
//                                }
//                                is ComandaDelecaoState.Success -> {
//                                    onComandaViewClick("comandaList")
//                                    Toast.makeText(
//                                        context,
//                                        "Comanda deletada com sucesso",
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//                                }
//                                is ComandaDelecaoState.Error -> {
//                                    Toast.makeText(
//                                        context,
//                                        "Erro ao deletar comanda",
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//                                }
//                                ComandaDelecaoState.Loading -> {
//                                    CircularProgressIndicator(color = GI_AzulMarinho)
//                                }
                        }
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
//                        .padding(start = 20.dp, end = 20.dp)
                ) {
//                    Box(modifier = Modifier
//                        .size(24.dp)
////                        .background(
////                            if (isSent) Color.Red else Color.Gray,
////                            shape = RoundedCornerShape(12.dp)
////                        )
//                        .clickable {
////                            isSent = !isSent
//                        })
//                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Status: ${textoStatus}",
                        fontSize = 15.sp,
                        color = Color.Gray
                    )
                }
            }

            LaunchedEffect(comandaAtualizacaoState) {
                if (comandaAtualizacaoState is ComandaAtualizacaoState.Success) {
                    Toast.makeText(
                        context,
                        "Comanda atualizada com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    onComandaViewClick("comandaList")
                }
                if (comandaAtualizacaoState is ComandaAtualizacaoState.Error) {
                    Toast.makeText(
                        context,
                        "Erro ao atualizar comanda",
                        Toast.LENGTH_SHORT).show()
                }
            }

            if (showDialog) {
                DadosPostComanda(
                    viewModel = viewModel,
                    showDialog = showDialog,
                    onDismiss = { showDialog = false },
                    comandaCriacaoState = comandaCriacaoSate,
                    context = context,
                    onComandaViewClick = onComandaViewClick,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun ItensComanda(
    comanda: Comanda?,
    pedidos: List<Prato>,
    viewModel: ComandaViewModel,
    onComandaViewClick: (String) -> Unit
) {
    // Agrupa os itens e conta as ocorrências
    val groupedPedidos = pedidos.groupingBy { it.nome }.eachCount()

    Box(
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth()
    ) {
        DrawScrollableView(
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 3.dp, start = 8.dp, end = 8.dp, bottom = 3.dp)
                ) {
                    groupedPedidos.forEach { (nome, quantidade) ->
                        // Busca o prato correspondente para renderizar
                        val prato = pedidos.first { it.nome == nome }
                        ItemComanda(
                            comanda = comanda,
                            pedido = prato,
                            quantidade = quantidade,
                            onComandaViewClick = onComandaViewClick,
                            viewModel = viewModel
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}


@Composable
fun ItemComanda(
    comanda: Comanda?,
    pedido: Prato,
    quantidade: Int,
    onComandaViewClick: (String) -> Unit,
    viewModel: ComandaViewModel
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Nome do prato com a quantidade
        Text(
            text = "${quantidade}x ${pedido.nome}",
            modifier = Modifier
                .clickable {
                    onComandaViewClick("cardapioItem/${pedido.idPrato}")
                },
            textDecoration = TextDecoration.Underline
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 8.dp)
        ) {
            // Preço total (quantidade * preço unitário)
            Text(text = "R$${pedido.preco * quantidade}")
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Deletar",
                tint = Black,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(30.dp)
                    .clickable {
                        if (comanda != null) {
                            Toast
                                .makeText(
                                    context,
                                    "Ação não permitida",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        } else {
                            viewModel.removerPrato(pedido)
                        }
                    }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DadosPostComanda(
    context: Context,
    navController: NavController,
    viewModel: ComandaViewModel,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    comandaCriacaoState: ComandaCriacaoSate,
    onComandaViewClick: (String) -> Unit
) {
    var showDialogIn by remember { mutableStateOf(showDialog) }

    if (showDialogIn) {
        var titulo by remember { mutableStateOf("") }
        var mesa by remember { mutableStateOf("") }

        AlertDialog(
            shape = RoundedCornerShape(10.dp),
            containerColor = Color.White,
            onDismissRequest = { onDismiss() },

            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Comanda:",
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .align(Alignment.CenterVertically),
                    )
                }
            },

            text = {
                Column {
//                    OutlinedTextField(
//                        value = titulo,
//                        onValueChange = {
//                            titulo = it
//                            viewModel.updateComandaAtualTitulo(it)
//                        },
//                        label = { Text("Título") },
//                        modifier = Modifier.fillMaxWidth(),
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = mesa,
                        onValueChange = {
                            mesa = it
                            viewModel.updateComandaAtualMesa(it)
                            viewModel.updateComandaAtualTitulo("Comanda: ")
                        },
                        label = { Text("Mesa") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },

            confirmButton = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedButton(
                        onClick = {
                            viewModel.createComanda(context, navController)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GI_Laranja,
                            contentColor = Black
                        ),
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(bottom = 30.dp)
                    ) {
                        if (comandaCriacaoState is ComandaCriacaoSate.Loading) {
                            CircularProgressIndicator(color = GI_AzulMarinho)
                        } else {
                            Text(
                                text = "CONFIRMAR",
                                style = TextStyle(
                                    fontSize = 17.sp,
                                    fontFamily = JostBold
                                )
                            )
                        }
                    }
                }
            }
        )
    }
    //Validação de estado de requisição
    LaunchedEffect(comandaCriacaoState) {
        if (comandaCriacaoState is ComandaCriacaoSate.Success) {
            Toast.makeText(
                context,
                "Comanda criada com sucesso",
                Toast.LENGTH_SHORT
            ).show()
            showDialogIn = false
            onComandaViewClick("comandaList")
        }
    }
    LaunchedEffect(comandaCriacaoState) {
        if (comandaCriacaoState is ComandaCriacaoSate.Error) {
            Toast.makeText(
                context,
                "Erro ao criar comanda",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DadosPostComandaPreview() {
    DadosPostComanda(
        context = LocalContext.current,
        viewModel = ComandaViewModel(
            ComandaRepositoryImplLocal()
        ),
        showDialog = true,
        onDismiss = {},
        comandaCriacaoState = ComandaCriacaoSate.Loading,
        onComandaViewClick = {},
        navController = NavController(LocalContext.current)
    )
}