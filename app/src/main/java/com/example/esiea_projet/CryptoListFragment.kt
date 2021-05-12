package com.example.esiea_projet

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

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

        getListFromAPI()
        try {
            val cryptoList = getListFromCache()
            if (cryptoList.isEmpty()) { getListFromAPI() }
            else { theadapter.updater(getListFromCache()) }
        } catch (e: java.lang.NullPointerException) { getListFromAPI() }
    }

    private fun getListFromAPI() {
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
                    saveListInCache(coinLoreResponse.data)
                    theadapter.updater(coinLoreResponse.data)
                }
            }
        })
    }

    private fun saveListInCache(cList: List<Crypto>) {
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("shared preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(cList)
        editor.putString("crypto", json)
        editor.apply()
    }

    private fun getListFromCache(): List<Crypto> {
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("shared preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("crypto", null)
        val type: Type = object : TypeToken<ArrayList<Crypto?>?>() {}.type
        return gson.fromJson(json, type)
    }

    private fun onClickCrypto(crypto: Crypto) {
        val cSymbol = crypto.symbol
        val cName = crypto.name
        val cRank = crypto.rank.toString()
        val cUsd = crypto.price_usd
        val cChange24 = crypto.percent_change_24h
        val cChange7 = crypto.percent_change_7d
        val cMcap = crypto.market_cap_usd
        val cTsupply = crypto.tsupply
        val cMsupply = crypto.msupply
        val action = CryptoListFragmentDirections.actionCryptolistfragToCryptodetailfrag(cSymbol, cName, cRank, cUsd, cChange24, cChange7, cMcap, cTsupply, cMsupply)
        findNavController().navigate(action)
    }
}