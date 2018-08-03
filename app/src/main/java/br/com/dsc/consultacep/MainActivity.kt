package br.com.dsc.consultacep

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var CEP: EditText
    lateinit var PesquisarCEP: Button

    lateinit var RUA: EditText
    lateinit var CIDADE: EditText
    lateinit var UF: EditText
    lateinit var PesquisarRCE: Button

    lateinit var barraProgresso: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Vinculação

        CEP                     = findViewById(R.id.cep)
        PesquisarCEP            = findViewById(R.id.pesquisaCEP)
        RUA                     = findViewById(R.id.rua)
        CIDADE                  = findViewById(R.id.cidade)
        UF                      = findViewById(R.id.uf)
        PesquisarRCE            = findViewById(R.id.pesquisaRCE)

        barraProgresso          = findViewById(R.id.progress_bar)

        // -- Ao clicar no botão 1, será pesquisado logradouto com o numero do CEP

        PesquisarCEP.setOnClickListener{

            barraProgresso.visibility = View.VISIBLE

            val call = RetrofitFactory().retrofitService().getCEP(CEP.text.toString())
            call.enqueue(object : Callback<CEP> {

                override fun onResponse(call : Call<CEP>, response: Response<CEP>) {
                    response?.let {
                        val coletaCEP: CEP? = it.body()
                        Log.i("CEP", coletaCEP?.toString())
                        Toast.makeText(this@MainActivity, coletaCEP.toString(), Toast.LENGTH_LONG).show()
                        barraProgresso.visibility = View.INVISIBLE
                    }
                }
                override fun onFailure(call:Call<CEP>?,t: Throwable?){
                Log.e("ERRO", t?.message)
                barraProgresso.visibility = View.INVISIBLE
            }

            })
        }

        // Ao clicar no botão numero 2, será pesquisado o logradouro com os dados, RUA CIDADE E ENDEREÇO

        PesquisarRCE.setOnClickListener{
            barraProgresso.visibility = View.VISIBLE

            val call = RetrofitFactory().retrofitService().getRCE(
                    UF.text.toString(),
                    CIDADE.text.toString(),
                    RUA.text.toString())

            call.enqueue(object : Callback<List<CEP>> {

                override fun onResponse(call: Call<List<CEP>>?, response: Response<List<CEP>>?) {

                    response?.let {
                        val coletaCEP: List<CEP>? = it.body()
                        Log.i("CEP", coletaCEP.toString())
                        Toast.makeText(this@MainActivity, coletaCEP.toString(), Toast.LENGTH_LONG).show()
                        barraProgresso.visibility = View.INVISIBLE
                    }

                }

                override fun onFailure(call: Call<List<CEP>>?, t: Throwable?) {
                    Log.e("Erro", t?.message)
                    barraProgresso.visibility = View.INVISIBLE

                }

            })
        }

    }
}
