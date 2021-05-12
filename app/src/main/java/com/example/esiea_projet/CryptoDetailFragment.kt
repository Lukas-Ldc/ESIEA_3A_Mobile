package com.example.esiea_projet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import java.util.*

class CryptoDetailFragment : Fragment () {

    private val args: CryptoDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.crypto_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvkTop: TextView = view.findViewById(R.id.tv_UCrypto_Top)
        val tvkPrice: TextView = view.findViewById(R.id.tv_UCrypto_Price)
        val tvkChange: TextView = view.findViewById(R.id.tv_UCrypto_Change)
        val tvkSupply: TextView = view.findViewById(R.id.tv_UCrypto_Supply)
        tvkTop.text = args.cName.plus("(").plus(args.cSymbol).plus(")")
        tvkPrice.text = args.cUsd.plus("$\n").plus(args.cMcap).plus("$ MCap")
        tvkChange.text = args.cChange24.plus("% sur 24h\n").plus(args.cChange7).plus("% sur 7 jours")
        tvkSupply.text = args.cTsupply.plus(" unités")

        val tvkButton: Button = view.findViewById(R.id.bt_UCrypto_Retour)
        tvkButton.setOnClickListener {
            findNavController().navigate(R.id.action_cryptodetailfrag_to_cryptolistfrag)
        }

        val imgV: ImageView = view.findViewById(R.id.img_UCrypto)
        Glide
            .with(view)
            .load("https://cryptoicons.org/api/icon/${args.cSymbol.toLowerCase(Locale.ROOT)}/200")
            .centerCrop()
            .into(imgV)
    }
}