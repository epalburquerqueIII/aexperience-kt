package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class ConsumoBono : Serializable {

    constructor(
        ID: Int?,
        Fecha: String?,
        Sesiones: Int?,
        IDUsuario: Int?,
        NombreUsuario: String?,
        IDEspacio: Int?,
        IDAutorizado: Int?

    ) {
        this.ID = ID
        this.Fecha = Fecha
        this.Sesiones = Sesiones
        this.IDUsuario = IDUsuario
        this.NombreUsuario = NombreUsuario
        this.IDEspacio = IDEspacio
        this.IDAutorizado = IDAutorizado
    }

    var ID: Int? = null
    var Fecha: String? = null
    var Sesiones: Int? = null
    var IDUsuario: Int? = null
    var NombreUsuario: String? = null
    var IDEspacio: Int? = null
    var IDAutorizado: Int? = null


}