package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Reserva : Serializable {

    constructor(
        Id: Int?,
        Fecha: String?,
        FechaPago: String?,
        Hora: Int?,
        IDUsuario: Int?,
        IDEspacio: Int?,
        IDAutorizado: Int?

    ) {
        this.Id = Id
        this.Fecha = Fecha
        this.FechaPago = FechaPago
        this.Hora = Hora
        this.IDUsuario = IDUsuario
        this.IDEspacio = IDEspacio
        this.IDAutorizado = IDAutorizado
    }

    var Id: Int? = null
    var Fecha: String? = null
    var FechaPago: String? = null
    var Hora: Int? = null
    var IDUsuario: Int? = null
    var IDEspacio: Int? = null
    var IDAutorizado: Int? = null


}