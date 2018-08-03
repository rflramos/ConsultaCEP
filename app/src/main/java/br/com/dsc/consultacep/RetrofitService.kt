package br.com.dsc.consultacep


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    //https://viacep.com.br/ws/06160180/json/
    @GET("{CEP}/json/")
    fun getCEP(@Path("CEP") CEP:String) : Call<CEP>

    @GET("{estado}/{cidade}/{endereco}/json/")
    fun getRCE(@Path("estado") estado: String,
               @Path("cidade")  cidade: String,
               @Path("endereco") endereco: String):Call<List<CEP>>
}