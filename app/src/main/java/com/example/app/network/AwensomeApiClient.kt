package com.example.app.network

import com.example.app.model.CurrencyResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface AwensomeService {
    //https://economia.awesomeapi.com.br/last/USD-BRL
    @GET("last/{coin}")
    suspend fun getAwensomeApi(@Path("coin") coin: String) : CurrencyResponse
}

object RetrofitInstance{
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://economia.awesomeapi.com.br/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: AwensomeService by lazy {
        retrofit.create(AwensomeService::class.java)
    }
}
