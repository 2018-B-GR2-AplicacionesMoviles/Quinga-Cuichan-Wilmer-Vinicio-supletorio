package com.example.viniq.supletorio

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.registrar_hijos.view.*

class AdaptadorRegistarIngredientes(

    private val listaIngredientes: ArrayList<Ingredientes>,
    var clickListener: ClickListener
) : RecyclerView.Adapter<AdaptadorRegistarIngredientes.MyViewHolder>() {

    var items: ArrayList<Ingredientes>? = null
    var viewHolder: MyViewHolder? = null


    init {
        this.items = listaIngredientes
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorRegistarIngredientes.MyViewHolder {
        val itemView = LayoutInflater
            .from(parent?.context)
            .inflate(R.layout.registrar_hijos, parent, false)
        viewHolder = MyViewHolder(itemView, clickListener)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return this.items?.count()!!
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items?.get(position)
        holder.nombresAP?.text = "Nombre: " + item?.nombreIngrediente

    }

    class MyViewHolder(view: View, listener: ClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var vista = view
        var nombresAP: TextView? = null
        var listener: ClickListener? = null

        init {

            nombresAP = vista.nombre_hijo
            this.listener = listener
            vista.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }
    }


}