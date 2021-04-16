package com.example.provacontinuada2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun cadastrar(view: View) {
        val apiCachorro = ConexaoApiCachorros.criar()

        val foto:ImageView = findViewById(R.id.iv_foto)
        val frase:TextView = findViewById(R.id.tv_consulta)
        var raca:EditText = findViewById(R.id.et_raca)
        var preco:EditText = findViewById(R.id.et_precoMedio)
        var indicado:Switch = findViewById(R.id.sw_crianca)

        val novoCachorro = Cachorro(null, raca.text.toString(), preco.text.toString().toInt(), indicado.isChecked)

        apiCachorro.post(novoCachorro).enqueue(object : Callback<Cachorro> {
            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                if (response.code() == 201) {
                    val caoCriado = response.body()
                    frase.text = "Cão cadastrado com sucesso!"
                } else {
                    frase.text = "Falha ao cadastrar o cachorro."
                }
            }

            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na chamada: ${t.message!!}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun listar(view: View) {}
}