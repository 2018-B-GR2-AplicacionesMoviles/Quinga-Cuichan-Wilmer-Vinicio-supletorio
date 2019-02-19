package com.example.viniq.supletorio

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registrar_ingrediente_juego.*
import kotlinx.android.synthetic.main.registrar_hijos.*
import java.util.ArrayList

class RegistrarIngredienteJuegoActivity : AppCompatActivity() {


    var comi: ArrayList<Ingredientes>? = null
    var id: Int? = 0
    var apBuscar: ArrayList<Ingredientes>? = null
    var lista: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var adaptador: AdaptadorRegistarIngredientes? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_ingrediente_juego)


        val idActualActaualActualActual = intent.getStringExtra("idActual")
        Log.i("aaaaaaaaaa", idActualActaualActualActual.toString())


        comi = BaseDatosIngredientes.getList()
        id = BaseHijoPorUsuario.getId()

        lista = findViewById(R.id.reciclerListaRegistrar)
        layoutManager = LinearLayoutManager(this)


        adaptador = AdaptadorRegistarIngredientes(comi!!, object : ClickListener {
            override fun onClick(vista: View, posicion: Int) {
                btn_aniadir_hijo.setOnClickListener {

                    Log.i("hola", "${idActualActaualActualActual}, ${comi?.get(posicion)?.id!!}")
                    agregarIngredientes(id!!, idActualActaualActualActual.toString().toInt(), comi?.get(posicion)?.id!!)
                    var app = Ingredientes(
                        comi?.get(posicion)?.id!!,
                        comi?.get(posicion)?.idIngredientes!!,
                        comi?.get(posicion)?.nombreIngrediente!!,
                        comi?.get(posicion)?.cantidad!!,
                        comi?.get(posicion)?.descripcionPreparacion!!,
                        comi?.get(posicion)?.opcional!!,
                        comi?.get(posicion)?.tipoIngredienteUtilizados!!,
                        comi?.get(posicion)?.necesitaRefrigeracion!!,
                        comi?.get(posicion)?.comidaId!!
                    )
                    BaseDatosIngredientes.editarIngrediente(app)
                    regresar()
                }
                Toast.makeText(applicationContext, comi?.get(posicion)?.nombreIngrediente, Toast.LENGTH_SHORT).show()
            }
        })
        lista?.layoutManager = layoutManager
        lista?.adapter = adaptador
        adaptador?.notifyDataSetChanged()

        btn_buscar.setOnClickListener {
            this.buscar()
        }
    }

    fun regresar(){
        val intent = Intent(this,MenuJuegoActivity::class.java)
        startActivity(intent)
    }


    fun agregarIngredientes(id: Int, idUsuario: Int, idIngrediente: Int) {
        var idd = id + 1
        Log.i("http-4", id.toString())
        var hijoPorUsuario = HijoPorUsuario(idd, idIngrediente, idUsuario, 0.0, 0, 0)
        BaseHijoPorUsuario.insertarHijoUsuario(hijoPorUsuario)

    }


    fun buscar() {

        Log.i("aaaaaaaaaaaaaaaaa", "wwwwwwwwwwwwwwwwwwwwwwwwww")

        var nombre = txt_buscar.text.toString()

        Log.i("aaaaaaaaaaaaaaaaa", nombre)

        apBuscar = BaseDatosIngredientes.buscar(nombre)

        Log.i("aaaaaaaaaaaaaaaaa", "zzzzzzzzzzzzzzzzzzzzzz")

        lista = findViewById(R.id.reciclerListaRegistrar)
        layoutManager = LinearLayoutManager(this)
        adaptador = AdaptadorRegistarIngredientes(comi!!, object : ClickListener {
            override fun onClick(vista: View, posicion: Int) {
                Toast.makeText(applicationContext, comi?.get(posicion)?.nombreIngrediente, Toast.LENGTH_SHORT).show()
            }
        })
        lista?.layoutManager = layoutManager
        lista?.adapter = adaptador
        adaptador?.notifyDataSetChanged()
    }


}
