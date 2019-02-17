package com.example.viniq.supletorio

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
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

        Log.i("ddddd", "dldlldldldld")
        val comidaRecivido = intent.getParcelableExtra<Comida>("Comida")
        hijonombrePlato.text = "NOMBRE PLATO:   " + comidaRecivido.nombrePlato.toString()
        hijoDescripcionPlato.text = "DESCRIPCIÓN:   " + comidaRecivido.descripcionPlato.toString()
        hijoNacionalidad.text = "NACIONALIDAD:  " + comidaRecivido.nacionalidad.toString()
        hijoNumPersonas.text = "NÚMERO PERSONAS:    " + comidaRecivido.numeroPersonas.toInt()

        Log.i("ddddd", comidaRecivido.picante.toString())


        if (comidaRecivido.picante === true) {
            hijoPicante.text = "PICANTE:    Si"


        } else {
            hijoPicante.text = "PICANTE:    No "

        }
        idComida = comidaRecivido.idComida.toInt()


        val listaIngredientes = this
        comi = BaseDatosIngredientes.getListaIdIngredientes(idComida)
        Log.i("bddd", "${comi}")
        lista = findViewById(R.id.reciclerListaIngredientes)
        layoutManager = LinearLayoutManager(this)


        adaptador = AdaptadorIngredientes(comi!!, object : ClickListener {
            override fun onClick(vista: View, posicion: Int) {
                val seleccion = findViewById<ConstraintLayout>(R.id.lista_ingredientesLista)
                seleccion.setOnClickListener {
                    val popupMenu: PopupMenu = PopupMenu(listaIngredientes, seleccion)
                    popupMenu.menuInflater.inflate(R.menu.popup_menu2, popupMenu.menu)
                    popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.menu_editar -> {
                                val intent =
                                    Intent(this@ListaIngredientesActivity, CrearIngredientesActivity::class.java)
                                intent.putExtra("Ingredientes", comi?.get(posicion) as Ingredientes)
                                intent.putExtra("tipo", "Edit")
                                startActivity(intent)
                                Toast.makeText(
                                    this@ListaIngredientesActivity,
                                    "Su seleccion:" + item.title,
                                    Toast.LENGTH_SHORT
                                ).show()
                                true

                            }
                            R.id.menu_eliminar -> {
                                BaseDatosComida.eliminar(comi?.get(posicion)?.idIngredientes!!)
                                Toast.makeText(
                                    this@ListaIngredientesActivity,
                                    "Su seleccion:" + item.title,
                                    Toast.LENGTH_SHORT
                                ).show()
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

          //   this.irCrearIngredientes(comi?.get(0) as Ingredientes)
            this.irCrearIngredientes()
        }


    }

    fun irCrearIngredientes( ) {
        val intent = Intent(this, CrearIngredientesActivity::class.java)
        //  intent.putExtra("Ingredientes", ingredientes)
        intent.putExtra("tipo", "Crear")
        startActivity(intent)
    }
}
