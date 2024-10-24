package com.example.gourmet_inventory_mobile.repository.Usuario

import com.example.gourmet_inventory_mobile.MainActivity
import com.example.gourmet_inventory_mobile.R
import com.example.gourmet_inventory_mobile.model.Empresa
import com.example.gourmet_inventory_mobile.service.LoginResponse
import retrofit2.Response

class UsuarioRepositoryLocalImpl() : UsuarioRepository {
    override suspend fun login(email: String, password: String): Response<LoginResponse> {
        val resourses = MainActivity.appContext.resources
        val mockLoginResponse = LoginResponse(
            token = "mockToken",
            userId = "mockUserId",
            idUsuario = 1L,
            nome = "Mock User",
            email = email,
            cargo = resourses.getString(R.string.gerente),
            empresa = Empresa(idEmpresa = 1L, nomeFantasia = "Mock Empresa"),
            celular = "123456789"
        )
        return Response.success(mockLoginResponse)
    }
}