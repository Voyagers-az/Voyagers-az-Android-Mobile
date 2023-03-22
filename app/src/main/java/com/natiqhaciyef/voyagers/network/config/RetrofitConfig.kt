package com.natiqhaciyef.voyagers.network.config

import com.natiqhaciyef.voyagers.network.remote.RetrofitClient

class RetrofitConfig {
    companion object {
        const val BASE_URL = ""
        fun getConnection(): NetworkApi = RetrofitClient
            .getClient(BASE_URL)
            .create(NetworkApi::class.java)
    }
}