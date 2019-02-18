package com.example.viniq.supletorio

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lista_ingredientes.*


class ListaIngredientesActivity : AppCompatActivity() {


    var comi: ArrayList<Ingredientes>? = null
    var lista: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var adaptador: AdaptadorIngredientes? = null
    var idComida: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_ingredientes)

        val comidaRecivido = intent.getParcelableExtra<Comida>("Comida")
        idComidaRecivido.text = "ID COMIDA:   " + comidaRecivido.idComida.toString()
        hijonombrePlato.text = "NOMBRE PLATO:   " + comidaRecivido.nombrePlato.toString()
        hijoDescripcionPlato.text = "DESCRIPCIÓN:   " + comidaRecivido.descripcionPlato.toString()
        hijoNacionalidad.text = "NACIONALIDAD:  " + comidaRecivido.nacionalidad.toString()
        hijoNumPersonas.text = "NÚMERO PERSONAS:    " + comidaRecivido.numeroPersonas.toInt()
        idComida = comidaRecivido.idComida.toString().toInt()


        if (comidaRecivido.picante === true) {
            hijoPicante.text = "PICANTE:    Si"


        } else {
            hijoPicante.text = "PICANTE:    No "

        }


        val listaIngredientes = this
        comi = BaseDatosIngredientes.getListaIdIngredientes(idComida)
        Log.i("bddd", "${comi}")
        lista = findViewById(R.id.reciclerListaIngredientes)
        Log.i("dddddddddddddddddddddd", "dscsdcsdcsdcsdcdsc")
        layoutManager = LinearLayoutManager(this)
        Log.i("dddddddddddddddddddddd", "llllllllllllllllllllllllllllllll")

        adaptador = AdaptadorIngredientes(comi!!, object : ClickListener {

            override fun onClick(vista: View, posicion: Int) {
                Log.i("dddddddddddddddddddddd", "sssssssssssssssssssssssss")

                val seleccion = findViewById<ConstraintLayout>(R.id.ingredientesListaLista)
                seleccion.setOnClickListener {
                    val popupMenu: PopupMenu = PopupMenu(listaIngredientes, seleccion)
                    Log.i("dddddddddddddddddddddd", "dscsdcsdcsdcsdcdsdddddddddddddddddddddddddc")

                    popupMenu.menuInflater.inflate(R.menu.popup_menu2, popupMenu.menu)
                    Log.i("dddddddddddddddddddddd", "sssssssssssssssssssssss")

                    popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                        when (item.itemId) {

                            R.id.menu_editar -> {
                                val intent =
                                    Intent(this@ListaIngredientesActivity, CrearIngredientesActivity::class.java)
                                intent.putExtra("Ingredientes", comi?.get(posicion) as Ingredientes)
                                intent.putExtra("tipo", "Edit")
                                intent.putExtra("idPadre", idComida.toString());

                                startActivity(intent)
                                Toast.makeText(
                                    this@ListaIngredientesActivity,
                                    "Su seleccion:" + item.title,
                                    Toast.LENGTH_SHORT
                                ).show()
                                true
                            }
                            R.id.menu_eliminar -> {
                                BaseDatosIngredientes.eliminar(comi?.get(posicion)?.id!!)
                                Log.i("cdscccccccsdc", comi?.get(posicion)?.id.toString())
                                Toast.makeText(
                                    this@ListaIngredientesActivity,
                                    "Su seleccion:" + item.title,
                                    Toast.LENGTH_SHORT
                                ).show()
                                BaseDatosIngredientes.getListaIdIngredientes(comi?.get(posicion)?.id!!)

                                true
                            }

                            R.id.menu_compartir -> {
                                val contenid = comi?.get(posicion)?.nombreIngrediente
                                val sendIntent: Intent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, contenid)
                                    type = "text/plain"
                                }
                                startActivity(sendIntent)
                                Toast.makeText(
                                    this@ListaIngredientesActivity,
                                    "Su seleccion:" + item.title,
                                    Toast.LENGTH_SHORT
                                ).show()
                                true
                            }


                            else -> false
                        }

                    })
                    popupMenu.show()
                }
                Toast.makeText(applicationContext, comi?.get(posicion)?.nombreIngrediente, Toast.LENGTH_SHORT).show()

            }
        })



        lista?.layoutManager = layoutManager
        lista?.adapter = adaptador

        adaptador?.notifyDataSetChanged()


        btn_nuevo_ingredientes.setOnClickListener {

            this.irCrearIngredientes()
        }
    }

    fun irCrearIngredientes() {
        val intent = Intent(this, CrearIngredientesActivity::class.java)
        intent.putExtra("idPadre", idComida.toString());
        startActivity(intent)
    }
}
