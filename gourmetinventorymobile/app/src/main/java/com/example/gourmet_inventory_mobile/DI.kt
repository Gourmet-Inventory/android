package com.example.gourmet_inventory_mobile

import android.util.Log
import com.example.gourmet_inventory_mobile.service.UsuarioService
import com.example.gourmet_inventory_mobile.repository.Usuario.UsuarioRepository
import com.example.gourmet_inventory_mobile.repository.Usuario.UsuarioRepositoryImpl
import com.example.gourmet_inventory_mobile.repository.Usuario.UsuarioRepositoryLocalImpl
import com.example.gourmet_inventory_mobile.viewmodel.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import kotlin.math.log

val appModule = module {
    Log.d("appModule", "Iniciando appModule")

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


}