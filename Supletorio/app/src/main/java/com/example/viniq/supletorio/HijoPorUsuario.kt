package com.example.viniq.supletorio

import android.os.Parcel
import android.os.Parcelable

class HijoPorUsuario (
    var idHijoPorUsuario : Int,
    var idIngredientes: Int,
    var idUsuario: Int,
    var experienciasIngredientes: Double,
    var numBatallas: Int,
    var numRecolectas: Int


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idHijoPorUsuario)
        parcel.writeInt(idIngredientes)
        parcel.writeInt(idUsuario)
        parcel.writeDouble(experienciasIngredientes)
        parcel.writeInt(numBatallas)
        parcel.writeInt(numRecolectas)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HijoPorUsuario> {
        override fun createFromParcel(parcel: Parcel): HijoPorUsuario {
            return HijoPorUsuario(parcel)
        }

        override fun newArray(size: Int): Array<HijoPorUsuario?> {
            return arrayOfNulls(size)
        }
    }
}