package com.natiqhaciyef.voyagers.network.config

class RetrofitConfig {
    companion object {
        const val BASE_URL = ""
        fun getConnection(): NetworkApi = RetrofitClient
            .getClient(BASE_URL)
            .create(NetworkApi::class.java)
    }
}