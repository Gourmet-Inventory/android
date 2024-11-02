package com.example.gourmet_inventory_mobile.service

import com.example.gourmet_inventory_mobile.model.Fornecedor
import retrofit2.Response
import retrofit2.http.GET


//val retrofit = Retrofit.Builder().baseUrl("")
//    .addConverterFactory(GsonConverterFactory.create()).build()


interface FornecedorService {
    @GET("/api/fornecedores")
    suspend fun getFornecedores(): Response<List<Fornecedor>>
}
