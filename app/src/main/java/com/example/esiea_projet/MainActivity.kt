package com.example.esiea_projet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var therecycler: RecyclerView
    private val theadapter = CryptoAdapter(listOf())
    private val thelayoutm = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        therecycler = findViewById(R.id.rvCrypto)
        therecycler.layoutManager = this.thelayoutm
        therecycler.adapter = this.theadapter


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coinlore.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val CryptoAPI: CryptoAPI = retrofit.create(CryptoAPI::class.java)

        CryptoAPI.getCryptoList().enqueue(object: Callback<CoinLoreResponse> {
            override fun onFailure(call: Call<CoinLoreResponse>, t: Throwable) {}
            override fun onResponse(call: Call<CoinLoreResponse>, response: Response<CoinLoreResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val CoinLoreResponse = response.body()!!
                    theadapter.updater(CoinLoreResponse.data)
                }
            }
        })
    }
}

// https://api.coinlore.net/api/tickers/?limit=20
// https://docs.google.com/presentation/d/13CBQEt7lMj0IBils_y9FEZ87bIrYZLuVBzqz1VpRmaw/edit#slide=id.g44288c70b5_0_22 (32:00 TD2)