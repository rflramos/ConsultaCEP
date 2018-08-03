package br.com.dsc.consultacep

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    val URL: String  = "http://viacep.com.br/ws/"

    val retrofitFactory = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun retrofitService():RetrofitService {
        return retrofitFactory.create(RetrofitService::class.java)
    }

}