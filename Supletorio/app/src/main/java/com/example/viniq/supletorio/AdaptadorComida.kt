package com.example.viniq.supletorio

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.lista_comida.view.*

class AdaptadorComida(private val listaComida: ArrayList<Comida>, var clickListener: ClickListener) : RecyclerView.Adapter<AdaptadorComida.MyViewHolder>() {

    var items: ArrayList<Comida>? = null
    var viewHolder: MyViewHolder? = null


    init {
        this.items = listaComida
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

        holder.nombrePlatoC?.text = "Nombre Plato: " + item?.nombrePlato
        holder.descripcionPlatoC?.text = "Descripcion Plato: " + item?.descripcionPlato
        holder.nacionalidadC?.text = "Nacionalidad: " + item?.nacionalidad
        holder.numeroPersonasC?.text = "Numero Personas: " + item?.numeroPersonas.toString()
        if (item?.picante == true) {
            holder.picanteC?.text = "Picante: Si"
        } else {
            holder.picanteC?.text = "Picante: No"
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
            nombrePlatoC = vista.nombre_platoLista
            descripcionPlatoC = vista.descripconPlatoLista
            nacionalidadC = vista.nacionalidadPlatoLista
            numeroPersonasC = vista.numeroPlatoLista
            picanteC = vista.picantePlatoLista
            this.listener = listener
            vista.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }
    }


}