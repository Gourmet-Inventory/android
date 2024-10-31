package com.example.gourmet_inventory_mobile

import android.util.Log
import com.example.gourmet_inventory_mobile.repository.Fornecedor.FornecedorRepository
import com.example.gourmet_inventory_mobile.repository.Fornecedor.FornecedorRepositoryImpl
import com.example.gourmet_inventory_mobile.repository.Usuario.UsuarioRepository
import com.example.gourmet_inventory_mobile.repository.Usuario.UsuarioRepositoryImpl
import com.example.gourmet_inventory_mobile.repository.estoque.EstoqueRepository
import com.example.gourmet_inventory_mobile.repository.estoque.EstoqueRepositoryImpl
import com.example.gourmet_inventory_mobile.repository.estoque.EstoqueRepositoryImplLocal
import com.example.gourmet_inventory_mobile.service.EstoqueService
import com.example.gourmet_inventory_mobile.service.FornecedorService
import com.example.gourmet_inventory_mobile.service.UsuarioService
import com.example.gourmet_inventory_mobile.viewmodel.EstoqueViewModel
import com.example.gourmet_inventory_mobile.viewmodel.FornViewModel
import com.example.gourmet_inventory_mobile.viewmodel.LoginViewModel
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
}