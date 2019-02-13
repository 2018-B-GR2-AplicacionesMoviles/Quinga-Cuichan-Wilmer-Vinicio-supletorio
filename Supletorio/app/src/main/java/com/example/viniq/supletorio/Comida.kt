package com.example.viniq.supletorio

import android.os.Parcel
import android.os.Parcelable

class Comida(
        val idComida: Int,
        val nombrePlato:String,
        val descripcionPlato: String,
        val nacionalidad: String,
        val numeroPersonas: Int,
        val picante: Boolean) : Parcelable {

    constructor(parcel: Parcel) : this
    (
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeInt(idComida)
        parcel.writeString(nombrePlato)
        parcel.writeString(descripcionPlato)
        parcel.writeString(nacionalidad)
        parcel.writeInt(numeroPersonas)
        parcel.writeByte(if (picante) 1 else 0)

     }

    override fun describeContents(): Int {
        return 0;
     }

    companion object CREATOR : Parcelable.Creator<Comida> {
        override fun createFromParcel(parcel: Parcel): Comida {
            return Comida(parcel)
        }

        override fun newArray(size: Int): Array<Comida?> {
            return arrayOfNulls(size)
        }
    }


}