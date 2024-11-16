package com.example.gourmet_inventory_mobile.DI

import android.util.Log
import com.example.gourmet_inventory_mobile.RetrofitInstance
import com.example.gourmet_inventory_mobile.repository.Fornecedor.FornecedorRepository
import com.example.gourmet_inventory_mobile.repository.Fornecedor.FornecedorRepositoryImpl
import com.example.gourmet_inventory_mobile.repository.ListaCompras.ListaComprasRepository
import com.example.gourmet_inventory_mobile.repository.ListaCompras.ListaComprasRepositoryImpl
import com.example.gourmet_inventory_mobile.repository.Usuario.UsuarioRepository
import com.example.gourmet_inventory_mobile.repository.Usuario.UsuarioRepositoryImpl
import com.example.gourmet_inventory_mobile.repository.Usuario.UsuarioRepositoryLocalImpl
import com.example.gourmet_inventory_mobile.repository.estoque.ComandaRepository
import com.example.gourmet_inventory_mobile.repository.estoque.ComandaRepositoryImpl
import com.example.gourmet_inventory_mobile.repository.estoque.ComandaRepositoryImplLocal
import com.example.gourmet_inventory_mobile.repository.estoque.EstoqueRepository
import com.example.gourmet_inventory_mobile.repository.estoque.EstoqueRepositoryImpl
import com.example.gourmet_inventory_mobile.repository.estoque.ListaComprasRepositoryImplLocal
import com.example.gourmet_inventory_mobile.repository.estoque.PratoRepository
import com.example.gourmet_inventory_mobile.repository.estoque.PratoRepositoryImpl
import com.example.gourmet_inventory_mobile.repository.estoque.PratoRepositoryImplLocal
import com.example.gourmet_inventory_mobile.service.ComandaService
import com.example.gourmet_inventory_mobile.service.EstoqueService
import com.example.gourmet_inventory_mobile.service.FornecedorService
import com.example.gourmet_inventory_mobile.service.ListaComprasService
import com.example.gourmet_inventory_mobile.service.PratoService
import com.example.gourmet_inventory_mobile.service.UsuarioService
import com.example.gourmet_inventory_mobile.viewmodel.ComandaViewModel
import com.example.gourmet_inventory_mobile.viewmodel.EstoqueViewModel
import com.example.gourmet_inventory_mobile.viewmodel.FornViewModel
import com.example.gourmet_inventory_mobile.viewmodel.ListaComprasViewModel
import com.example.gourmet_inventory_mobile.viewmodel.LoginViewModel
import com.example.gourmet_inventory_mobile.viewmodel.PratoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    Log.d("appModule", "Iniciando appModule")

    //Usuário
    single<UsuarioService> {
        RetrofitInstance.serviceUsuario
    }

    single<UsuarioRepository> {
        UsuarioRepositoryImpl(get())
//        UsuarioRepositoryLocalImpl()
    }

    viewModel<LoginViewModel> {
        LoginViewModel(get())
    }

//    Fornecedor
    single<FornecedorService> {
        RetrofitInstance.serviceFornecedor
    }

    single<FornecedorRepository> {
        FornecedorRepositoryImpl(get())
//        FornecedorRepositoryLocalImpl()
    }

    viewModel<FornViewModel> {
        FornViewModel(get())
    }

    //Estoque
    single<EstoqueService> {
        RetrofitInstance.serviceEstoque
    }

    single<EstoqueRepository> {
        EstoqueRepositoryImpl(get())
//        EstoqueRepositoryImplLocal()
    }

    viewModel<EstoqueViewModel> {
        EstoqueViewModel(get())
    }

    //Lista de compras
    single<ListaComprasService> {
        RetrofitInstance.serviceListaCompras
    }

    single<ListaComprasRepository> {
//        ListaComprasRepositoryImpl(get())
        ListaComprasRepositoryImplLocal()
    }

    viewModel<ListaComprasViewModel> {
        ListaComprasViewModel(get())
    }

    //Pratos
    single<PratoService> {
        RetrofitInstance.servicePrato
    }

    single<PratoRepository> {
        PratoRepositoryImpl(get())
//        PratoRepositoryImplLocal()
    }

    viewModel<PratoViewModel> {
        PratoViewModel(get())
    }

    //Comanda
    single<ComandaService> {
        RetrofitInstance.serviceComanda
    }

    single<ComandaRepository> {
//        ComandaRepositoryImpl(get())
        ComandaRepositoryImplLocal()
    }

    viewModel<ComandaViewModel> {
        ComandaViewModel(get())
    }
}
