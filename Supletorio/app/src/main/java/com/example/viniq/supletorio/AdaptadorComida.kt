package com.example.viniq.supletorio

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
 import kotlinx.android.synthetic.main.lista_comida.view.*

class AdaptadorComida(private val listaComida: ArrayList<Comida>, var clickListener: ClickListener) :
    RecyclerView.Adapter<AdaptadorComida.MyViewHolder>() {

    var items: ArrayList<Comida>? = null
    var viewHolder: MyViewHolder? = null


    init {
        this.items = listaComida
    }

    private var position: Int = 0


    fun getPosition(): Int {
        return position
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorComida.MyViewHolder {

        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.lista_comida, parent, false)
        viewHolder = MyViewHolder(itemView, clickListener)
        return viewHolder!!


    }

    override fun getItemCount(): Int {
        return this.items?.count()!!
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items?.get(position)

        holder.nombrePlatoC?.text = "NOMBRE PLATO:  " + item?.nombrePlato
        holder.descripcionPlatoC?.text = "DESCRIPCIÓN:  " + item?.descripcionPlato
        holder.nacionalidadC?.text = "NACIONALIDAD: " + item?.nacionalidad
        holder.numeroPersonasC?.text = "NÚMERO PERSONAS:    " + item?.numeroPersonas.toString()
        if (item?.picante == true) {
            holder.picanteC?.text = "PICANTE:   Si"
        } else {
            holder.picanteC?.text = "PICANTE:   No"
        }
    }

    class MyViewHolder(view: View, listener: ClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var vista = view
        var nombrePlatoC: TextView? = null
        var descripcionPlatoC: TextView? = null
        var nacionalidadC: TextView? = null
        var numeroPersonasC: TextView? = null
        var picanteC: TextView? = null
        var listener: ClickListener? = null

        init {
            nombrePlatoC = vista.hijoNombreIngrediente
            descripcionPlatoC = vista.hijoCantidad
            nacionalidadC = vista.hijoDescripcionPreparacion
            numeroPersonasC = vista.hijoNecesitaRefrigeracion
            picanteC = vista.picantePlatoLista
            this.listener = listener
            vista.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }
    }


}