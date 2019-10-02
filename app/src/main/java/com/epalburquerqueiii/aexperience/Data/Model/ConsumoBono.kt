package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class ConsumoBono : Serializable {

    constructor(
        ID: Int?,
        Fecha: String?,
        Sesiones: Int?,
        Usuario: String?,
        Espacio: String?,
        Autorizado: String?

    ) {
        this.ID = ID
        this.Fecha = Fecha
        this.Sesiones = Sesiones
        this.Usuario = Usuario
        this.Espacio = Espacio
        this.Autorizado = Autorizado
    }

    var ID: Int? = null
    var Fecha: String? = null
    var Sesiones: Int? = null
    var Usuario: String? = null
    var Espacio: String? = null
    var Autorizado: String? = null
}