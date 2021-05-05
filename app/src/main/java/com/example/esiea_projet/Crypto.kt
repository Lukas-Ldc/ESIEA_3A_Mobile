package com.example.esiea_projet

data class Crypto(
        val symbol: String,
        val name: String,
        val rank: Int,
        val price_usd: String,
        val percent_change_24h: String,
        val percent_change_7d: String,
        val market_cap_usd: String,
        val tsupply: String,
        val msupply: String
)