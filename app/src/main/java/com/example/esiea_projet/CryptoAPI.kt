package com.example.esiea_projet

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoAPI {
    @GET("tickers")
    fun getCryptoList(@Query("limit") limit: String): Call<CoinLoreResponse>
}