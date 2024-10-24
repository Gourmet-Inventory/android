package com.example.gourmet_inventory_mobile

import android.content.Context
import android.util.Log
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
        Log.d("RetrofitInstance - api", "Iniciando RetrofitInstance")
        Retrofit
            .Builder()
            .client(client)
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun logginInterceptor(): HttpLoggingInterceptor {
        Log.d("logginInterceptor", "Iniciando logginInterceptor")
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
            Log.d("ApiInterceptor", "Iniciando ApiInterceptor")
            val token = runBlocking {
                DataStoreUtils(context).obterToken().first()
            }

            val oldRequest = chain.request()
            Log.d("ApiInterceptor", "oldRequest: $oldRequest")

            val newRequestBuilder = oldRequest.newBuilder()

            if (oldRequest.url.encodedPath.contains("/usuarios/login")) {
                newRequestBuilder.header("Authorization", "")
                Log.d("ApiInterceptor", "newRequestBuilder: $newRequestBuilder")
            } else{
                newRequestBuilder.header("Authorization", "Barer $token")
            }
            val newRequest = newRequestBuilder.build()

            return chain.proceed(newRequest)
        }
    }

    //Criação do client para fazer a requisição
    private val client by lazy {
        Log.d("client", "Iniciando client")
        OkHttpClient.Builder()
//            .cache(null)
            .addInterceptor(ApiInterceptor(context = MainActivity.appContext))
            .addInterceptor(logginInterceptor())
            .build()
    }

    val serviceUsuario by lazy {
        Log.d("serviceUsuario", "Iniciando serviceUsuario")
        api.create(UsuarioService::class.java)
    }
}