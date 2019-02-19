package com.example.viniq.supletorio

import android.util.Log
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import org.json.JSONArray

class BaseHijoPorUsuario {
    companion object {


        var ip =" http://192.168.1.9:1337/HijosPorUsuario"
        var aux = JSONArray()
        lateinit var resp : JSONArray

        fun insertarHijoUsuario(hijoPorUsuario: HijoPorUsuario){
            ip.httpPost(listOf(

                "idHijoPorUsuario" to hijoPorUsuario.idHijoPorUsuario,
                "idIngredientes" to hijoPorUsuario.idIngredientes,
                "idUsuario" to hijoPorUsuario.idUsuario,
                "experienciasIngredientes" to hijoPorUsuario.experienciasIngredientes,
                "numBatallas" to hijoPorUsuario.numBatallas,
                "numRecolectas" to hijoPorUsuario.numRecolectas))
                .responseString{ request, res, result ->
                    Log.i("http-4",request.toString())
                    Log.i("http-4",res.toString())
                    Log.i("http-4",result.toString())
                }
        }


        fun getId(): Int{
            var id = 0
            ip.httpGet().responseJson {
                    request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-2", "Error: ${ex}")
                    }
                    is Result.Success -> {
                        val datos = result.get()
                        aux = datos.array()

                        Log.i("http-2", "DatosAP: ${aux}")
                        Log.i("Tipo", "${aux::class.simpleName}")
                    }

                }
                resp = result.get().array()

            }
            for (i in 0 until aux.length()) {
                id = resp.getJSONObject(i).getInt("id")

            }
            return id
        }





    }
}