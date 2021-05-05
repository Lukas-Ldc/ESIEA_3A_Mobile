package com.example.esiea_projet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CryptoAdapter(private var dataSet: List<Crypto>, var listener: ((Crypto) -> Unit)? = null) : RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCRank: TextView
        val tvCName: TextView
        val tvCPrice: TextView
        init {
            tvCRank = view.findViewById(R.id.tv_Crypto_Rank)
            tvCName = view.findViewById(R.id.tv_Crypto_Name)
            tvCPrice = view.findViewById(R.id.tv_Crypto_Price)
        }
    }

    fun updater(list: List<Crypto>){
        dataSet = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.crypto_list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val thecrypto = dataSet[position]
        viewHolder.tvCRank.text = thecrypto.rank.toString()
        viewHolder.tvCName.text = thecrypto.name
        viewHolder.tvCPrice.text = thecrypto.price_usd.plus("$")
        viewHolder.itemView.setOnClickListener{ listener?.invoke(thecrypto) }
    }

    override fun getItemCount() = dataSet.size
}
