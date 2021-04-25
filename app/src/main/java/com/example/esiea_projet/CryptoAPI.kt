package com.example.esiea_projet

import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {
    @GET("tickers")
    fun getCryptoList(): Call<CoinLoreResponse>
}