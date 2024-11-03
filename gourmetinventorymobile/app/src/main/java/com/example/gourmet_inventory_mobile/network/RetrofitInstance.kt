package com.example.gourmet_inventory_mobile.network

import com.example.gourmet_inventory_mobile.service.UsuarioService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val client by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

//    fun gson() : Gson = GsonBuilder().create()

    val apiUser: UsuarioService by lazy {
        Retrofit.Builder()
            .baseUrl("")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsuarioService::class.java)
    }
}