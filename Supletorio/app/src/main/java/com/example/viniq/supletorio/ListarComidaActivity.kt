package com.example.viniq.supletorio

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast

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


    }
}
