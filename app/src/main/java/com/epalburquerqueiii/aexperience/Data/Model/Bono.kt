package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Bono : Serializable {

    constructor(
        Precio: Int?,
        Sesiones: Int?

    ) {
        this.Precio = Precio
        this.Sesiones = Sesiones
    }

    var Precio: Int? = null
    var Sesiones: Int? = null

}