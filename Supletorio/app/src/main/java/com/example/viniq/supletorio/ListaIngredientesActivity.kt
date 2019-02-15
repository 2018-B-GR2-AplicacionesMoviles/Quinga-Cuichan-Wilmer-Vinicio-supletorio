package com.example.viniq.supletorio

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
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

        val ingredientesRecivido = intent.getParcelableExtra<Comida>("Comida")
        hijonombrePlato.text = "Nombre: "+ingredientesRecivido.nombrePlato.toString()



    }


}
