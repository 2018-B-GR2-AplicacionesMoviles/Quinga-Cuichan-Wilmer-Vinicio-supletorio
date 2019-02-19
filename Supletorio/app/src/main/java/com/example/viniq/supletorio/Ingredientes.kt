package com.example.viniq.supletorio

import android.os.Parcel
import android.os.Parcelable

class Ingredientes(
    var id: Int,
    var idIngredientes: Long,
    var nombreIngrediente: String,
    var cantidad: Int,
    var descripcionPreparacion: String,
    var opcional: Boolean,
    var tipoIngredienteUtilizados: String,
    var necesitaRefrigeracion: Boolean,
    var comidaId: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeLong(idIngredientes)
        parcel.writeString(nombreIngrediente)
        parcel.writeInt(cantidad)
        parcel.writeString(descripcionPreparacion)
        parcel.writeByte(if (opcional) 1 else 0)
        parcel.writeString(tipoIngredienteUtilizados)
        parcel.writeByte(if (necesitaRefrigeracion) 1 else 0)
        parcel.writeInt(comidaId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ingredientes> {
        override fun createFromParcel(parcel: Parcel): Ingredientes {
            return Ingredientes(parcel)
        }

        override fun newArray(size: Int): Array<Ingredientes?> {
            return arrayOfNulls(size)
        }
    }
}