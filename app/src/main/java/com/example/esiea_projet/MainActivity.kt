package com.example.esiea_projet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

        val cryptoList = arrayListOf<String>().apply {
            add("Test0")
            add("Test1")
            add("Test2")
            add("Test3")
            add("Test4")
            add("Test5")
            add("Test6")
            add("Test7")
            add("Test8")
            add("Test9")
        }
        theadapter.updater(cryptoList)
    }
}

//https://docs.google.com/presentation/d/13CBQEt7lMj0IBils_y9FEZ87bIrYZLuVBzqz1VpRmaw/edit#slide=id.g44288c70b5_0_22 (32:00 TD2)