package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Autorizado : Serializable {

    constructor(
        ID: Int?,
        IdUsuario: Int?,
        NombreAutorizado: String?,
        Nif: String?

    ) {
        this.ID = ID
        this.IdUsuario = IdUsuario
        this.NombreAutorizado = NombreAutorizado
        this.Nif = Nif
    }

    var ID: Int? = null
    var IdUsuario: Int? = null
    var NombreAutorizado: String? = null
    var Nif: String? = null
}