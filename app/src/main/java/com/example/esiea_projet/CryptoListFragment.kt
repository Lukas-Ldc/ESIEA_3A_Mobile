package com.example.esiea_projet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoListFragment : Fragment() {

    private lateinit var therecycler: RecyclerView
    private val theadapter = CryptoAdapter(listOf(), ::onClickCrypto)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.crypto_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        therecycler = view.findViewById(R.id.rvCrypto)
        therecycler.layoutManager = LinearLayoutManager(context)
        therecycler.adapter = this.theadapter


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coinlore.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val cryptoAPI: CryptoAPI = retrofit.create(CryptoAPI::class.java)

        cryptoAPI.getCryptoList("30").enqueue(object: Callback<CoinLoreResponse> {
            override fun onFailure(call: Call<CoinLoreResponse>, t: Throwable) {}
            override fun onResponse(call: Call<CoinLoreResponse>, response: Response<CoinLoreResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val coinLoreResponse = response.body()!!
                    theadapter.updater(coinLoreResponse.data)
                }
            }
        })
    }

    private fun onClickCrypto(crypto: Crypto) {
        val symbol = crypto.symbol
        val action = CryptoListFragmentDirections.actionCryptolistfragToCryptodetailfrag(symbol)
        findNavController().navigate(action)
    }
}