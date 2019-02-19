package com.example.viniq.supletorio

import android.util.Log
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

import org.json.JSONArray

class BaseDatosIngredientes() {

    companion object {

        var ip = "http://192.168.1.9:1337/Ingredientes"
        var aux = JSONArray()
        lateinit var resp: JSONArray

        fun insertarIngrediente(ingredientes: Ingredientes) {
            ip.httpPost(
                listOf(
                    "idIngredientes" to ingredientes.idIngredientes,
                    "nombreIngrediente" to ingredientes.nombreIngrediente,
                    "cantidad" to ingredientes.cantidad,
                    "descripcionPreparacion" to ingredientes.descripcionPreparacion,
                    "opcional" to ingredientes.opcional,
                    "tipoIngredienteUtilizados" to ingredientes.tipoIngredienteUtilizados,
                    "necesitaRefrigeracion" to ingredientes.necesitaRefrigeracion,
                    "comidaId" to ingredientes.comidaId

                )
            )
                .responseString { request, _, result ->
                    Log.i("http-2333333333333", result.toString())

                    Log.i("http-2333333333333", request.toString())
                }
        }


        fun getListaIdIngredientes(id: Int): ArrayList<Ingredientes> {

            val ingredientes: ArrayList<Ingredientes> = ArrayList()
            "${ip}/?comidaId=${id}".httpGet().responseJson { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-2", "Error: ${ex}")
                    }
                    is Result.Success -> {
                        val datos = result.get()
                        aux = datos.array()
                        Log.i("http-2", "DatosIngredientes: ${aux}")
                        Log.i("Tipo", "${aux::class.simpleName}")
                    }

                }
                resp = result.get().array()

            }
            for (i in 0 until aux.length()) {
                val id = resp.getJSONObject(i).getInt("id")
                Log.i("dsssssssssssssssssss",id.toString())


                val idIngredientes = resp.getJSONObject(i).getLong("idIngredientes")
                Log.i("dsssssssssssssssssss",idIngredientes.toString())

                val nombreIngrediente = resp.getJSONObject(i).getString("nombreIngrediente")
                Log.i("dsssssssssssssssssss",nombreIngrediente.toString())

                val cantidad = resp.getJSONObject(i).getInt("cantidad")
                Log.i("dsssssssssssssssssss",cantidad.toString())

                val descripcionPreparacion = resp.getJSONObject(i).getString("descripcionPreparacion")
                Log.i("dsssssssssssssssssss",descripcionPreparacion.toString())

                val opcional = resp.getJSONObject(i).getBoolean("opcional")
                Log.i("dsssssssssssssssssss",opcional.toString())

                val tipoIngredienteUtilizados = resp.getJSONObject(i).getString("tipoIngredienteUtilizados")
                Log.i("dsssssssssssssssssss",tipoIngredienteUtilizados.toString())

                val necesitaRefrigeracion = resp.getJSONObject(i).getBoolean("necesitaRefrigeracion")

                Log.i("dsssssssssssssssssss",necesitaRefrigeracion.toString())

                val comidaId = resp.getJSONObject(i).getJSONObject("comidaId").getInt("id")



                Log.i("dsssssssssssssssssss",comidaId.toString())

                val app = Ingredientes(
                    id,
                    idIngredientes,
                    nombreIngrediente,
                    cantidad,
                    descripcionPreparacion,
                    opcional,
                    tipoIngredienteUtilizados,
                    necesitaRefrigeracion,
                    comidaId
                )



                ingredientes.add(app)
             }
            Log.i("http-2", "DatosReturnIngredientes: ${ingredientes}")
            return ingredientes
        }






        fun eliminar(id: Int) {
            "${ip}/${id}".httpDelete().responseString { request, response, result ->
                Log.i("http-2", request.toString())
            }
        }

        fun editarIngrediente(ingredientes: Ingredientes) {
            "${ip}/${ingredientes.id}".httpPut(
                listOf(
                    "idIngredientes" to ingredientes.idIngredientes,
                    "nombreIngrediente" to ingredientes.nombreIngrediente,
                    "cantidad" to ingredientes.cantidad,
                    "descripcionPreparacion" to ingredientes.descripcionPreparacion,
                    "opcional" to ingredientes.opcional,
                    "tipoIngredienteUtilizados" to ingredientes.tipoIngredienteUtilizados,
                    "necesitaRefrigeracion" to ingredientes.necesitaRefrigeracion,
                    "comidaId" to ingredientes.comidaId
                )
            )
                .responseString { request, _, result ->
                    Log.i("http-2", request.toString())
                }
        }





    }

}