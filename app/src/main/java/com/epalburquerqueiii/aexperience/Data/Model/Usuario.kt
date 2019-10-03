package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Usuario : Serializable {

    constructor(
        ID: Int?,
        Nombre: String?,
        Nif: String?,
        Email: String?,
        Tipo: Int?,
        Telefono: String?,
        SesionesBonos: Int?,
        Newsletter: Int?,
        FechaBaja: String?

    ) {
        this.ID = ID
        this.Nombre = Nombre
        this.Nif = Nif
        this.Email = Email
        this.Tipo = Tipo
        this.Telefono = Telefono
        this.SesionesBonos = SesionesBonos
        this.Newsletter = Newsletter
        this.FechaBaja = FechaBaja
    }

    var ID: Int? = null
    var Nombre: String? = null
    var Nif: String? = null
    var Email: String? = null
    var Tipo: Int? = null
    var Telefono: String? = null
    var SesionesBonos: Int? = null
    var Newsletter: Int? = null
    var FechaBaja: String? = null

}
