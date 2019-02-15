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
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class ListarComidaActivity : AppCompatActivity() {

    var comi: ArrayList<Comida>? = null
    var lista: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var adaptador: AdaptadorComida? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_comida)


        val listaComida = this

        comi = BaseDatosComida.getList()
        Log.i("bddd", "${comi}")
        lista = findViewById(R.id.reciclerListaComida)
        layoutManager = LinearLayoutManager(this)

        adaptador = AdaptadorComida(comi!!, object : ClickListener {

            override fun onClick(vista: View, posicion: Int) {
                val seleccion = findViewById<ConstraintLayout>(R.id.lista_comida)
                seleccion.setOnClickListener {
                    val popupMenu: PopupMenu = PopupMenu(listaComida, seleccion)
                    popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
                    popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.menu_eliminar -> {
                                BaseDatosComida.eliminar(comi?.get(posicion)?.idComida!!)
                                Toast.makeText(this@ListarComidaActivity, "Su seleccion:" + item.title, Toast.LENGTH_SHORT).show()
                                true
                            }
                            R.id.menu_editar -> {
                                val intent = Intent(this@ListarComidaActivity, CrearComidaActivity::class.java)
                                intent.putExtra("Comida", comi?.get(posicion) as Comida)
                                intent.putExtra("tipo", "Edit")
                                startActivity(intent)
                                Toast.makeText(this@ListarComidaActivity, "Su seleccion:" + item.title, Toast.LENGTH_SHORT).show()
                                true

                            }

                            R.id.menu_compartir -> {
                                val contenid = comi?.get(posicion)?.nombrePlato + "\n" + comi?.get(posicion)?.descripcionPlato
                                val sendIntent: Intent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, contenid)
                                    type = "text/plain"
                                }
                                startActivity(sendIntent)
                                Toast.makeText(this@ListarComidaActivity, "Su seleccion:" + item.title, Toast.LENGTH_SHORT).show()
                                true
                            }

                            R.id.menu_lista_aplicaciones -> {
                                val intent = Intent(this@ListarComidaActivity, ListaIngredientesActivity::class.java)
                                startActivity(intent)
                                Toast.makeText(this@ListarComidaActivity, "Su seleccion:" + item.title, Toast.LENGTH_SHORT).show()
                                true
                            }
                            else -> false
                        }
                    })
                    popupMenu.show()
                }
                Toast.makeText(applicationContext, comi?.get(posicion)?.nombrePlato, Toast.LENGTH_SHORT).show()
            }
        })

        lista?.layoutManager = layoutManager
        lista?.adapter = adaptador
        adaptador?.notifyDataSetChanged()

    }






}
