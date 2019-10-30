package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Bono : Serializable {

    constructor(
        ID: Int?,
        Precio: Float?,
        Sesiones: Int?

    ) {
        this.ID = ID
        this.Precio = Precio
        this.Sesiones = Sesiones
    }

    var ID: Int? = null
    var Precio: Float? = null
    var Sesiones: Int? = null

}