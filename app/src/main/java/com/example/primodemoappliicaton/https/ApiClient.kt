package com.example.primodemoappliicaton.https

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


object ApiClient {
    private const val BASE_URL = "https://medium.com/"
    private var retrofit: Retrofit? = null

    val client: Retrofit
        get() {
            if (retrofit == null) {

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
                    .build()
            }
            return retrofit!!
        }
}