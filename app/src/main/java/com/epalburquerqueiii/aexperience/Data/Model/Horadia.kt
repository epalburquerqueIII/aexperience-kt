package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Horadia : Serializable {

    constructor(
        ID: Int?,
        IDEspacio: Int?,
        Fecha: String?,
        Hora : Int?,
        Horareservada: Int?,
        Reservado: Int?,
        IDUsuario: Int?,
    IDAutorizado: Int?


    ) {
        this.ID = ID
        this.IDEspacio = IDEspacio
        this.Fecha = Fecha
        this.Hora = Hora
        this.Horareservada=Horareservada
        this.Reservado=Reservado
        this.IDUsuario= IDUsuario
        this.IDAutorizado= IDAutorizado

    }

    var ID: Int? = null
    var IDEspacio: Int? = null
    var Fecha: String? = null
    var Hora: Int?=null
    var Horareservada: Int?=null
    var Reservado: Int?=null
    var IDUsuario: Int?=null
    var IDAutorizado: Int?=null

}