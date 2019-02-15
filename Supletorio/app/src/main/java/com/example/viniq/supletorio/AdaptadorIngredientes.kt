package com.example.viniq.supletorio

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.listar_ingredientes.view.*

class AdaptadorIngredientes(private val listaIngredientes: ArrayList<Ingredientes>, var clickListener: ClickListener) : RecyclerView.Adapter<AdaptadorIngredientes.MyViewHolder>() {

    var items: ArrayList<Ingredientes>? = null
    var viewHolder: MyViewHolder? = null

    init {
        this.items = listaIngredientes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorIngredientes.MyViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.listar_ingredientes, parent, false)
        viewHolder = MyViewHolder(itemView, clickListener)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return this.items?.count()!!
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items?.get(position)

        holder.nombreIngrediente?.text = "Nombre Ingredientes: " + item?.nombreIngrediente
        holder.cantidad?.text = "Cantidad: " + item?.cantidad
        holder.descripcionPreparacion?.text = "Descripcion Preparacion : " + item?.descripcionPreparacion
        holder.opcional?.text = "Opcional: " + item?.opcional
        holder.tipoIngrediente?.text = "Tipo Ingredientes: " + item?.tipoIngrediente
        holder.necesitaRefrigeracion?.text = "Necesita Refrigeracion: " + item?.nombreIngrediente
    }


    class MyViewHolder(view: View, listener: ClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {


        var vista = view
        var nombreIngrediente: TextView? = null
        var cantidad: TextView? = null
        var descripcionPreparacion: TextView? = null
        var opcional: TextView? = null
        var tipoIngrediente: TextView? = null
        var necesitaRefrigeracion: TextView? = null
        var listener: ClickListener? = null

        init {
            nombreIngrediente = vista.hijoNombreIngrediente
            cantidad = vista.hijoCantidad
            descripcionPreparacion = vista.hijoDescripcionPreparacion
            opcional = vista.hijoOpcional
            tipoIngrediente = vista.hijoTipoIngrediente
            necesitaRefrigeracion = vista.hijoNecesitaRefrigeracion
            this.listener = listener
            vista.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }
    }


}


