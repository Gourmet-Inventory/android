package com.example.gourmet_inventory_mobile

import com.example.gourmet_inventory_mobile.service.UsuarioService
import com.example.gourmet_inventory_mobile.repository.Usuario.UsuarioRepository
import com.example.gourmet_inventory_mobile.repository.Usuario.UsuarioRepositoryLocalImpl
import com.example.gourmet_inventory_mobile.viewmodel.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<UsuarioService> {
        RetrofitInstance.serviceUsuario
    }

    single<UsuarioRepository> {
//        UsuarioRepositoryImpl(get())
        UsuarioRepositoryLocalImpl()
    }

    viewModel<LoginViewModel> {
        LoginViewModel(get())
    }
}