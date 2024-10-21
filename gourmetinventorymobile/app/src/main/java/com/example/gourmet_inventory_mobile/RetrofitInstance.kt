package com.example.gourmet_inventory_mobile

import android.content.Context
import com.example.gourmet_inventory_mobile.service.UsuarioService
import com.example.gourmet_inventory_mobile.utils.DataStoreUtils
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val api by lazy {
        Retrofit
            .Builder()
            .client(client)
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun logginInterceptor() : HttpLoggingInterceptor {
//        val interceptor = HttpLoggingInterceptor ()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        return interceptor

        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    //Classe para interceptar a requisição e adicionar o token de autenticação
    class ApiInterceptor(private val context: Context) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val token = runBlocking {
                DataStoreUtils(context).obterToken().first()
            }

            val oldRequest = chain.request()

            val newRequest = oldRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()

            return chain.proceed(newRequest)
        }
    }

    //Criação do client para fazer a requisição
    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(ApiInterceptor(context = MainActivity.appContext))
            .addInterceptor(logginInterceptor())
            .build()
    }

    val serviceUsuario by lazy {
        api.create(UsuarioService::class.java)
    }
}