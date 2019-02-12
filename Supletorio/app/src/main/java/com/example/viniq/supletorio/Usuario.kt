package com.example.viniq.supletorio

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Usuario(var nombre: String, var apellido: String, var fechaNacimiente: String, var correo: String, var password: String


) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(apellido)
        parcel.writeString(fechaNacimiente)
        parcel.writeString(correo)
        parcel.writeString(password)


    }

    override fun describeContents(): Int {
        return 0;
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {

        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }

}

