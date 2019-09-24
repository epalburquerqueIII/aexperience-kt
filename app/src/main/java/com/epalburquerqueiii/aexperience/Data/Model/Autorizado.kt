package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Autorizado : Serializable {

    constructor(
        ID: Int?,
        IDUsuario: Int?,
        NombreAutorizado: String?,
        Nif: String?

    ) {
        this.ID = ID
        this.IDUsuario = IDUsuario
        this.NombreAutorizado = NombreAutorizado
        this.Nif = Nif
    }

    var ID: Int? = null
    var IDUsuario: Int? = null
    var NombreAutorizado: String? = null
    var Nif: String? = null

}