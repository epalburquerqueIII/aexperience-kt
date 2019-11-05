package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Tiponoticia : Serializable {

    constructor(
        Id: Int?,
        Nombre: String?,
        Email: String?

    ) {
        this.Id = Id
        this.Nombre = Nombre
        this.Email = Email

    }

    var Id: Int? = null
    var Nombre : String? = null
    var Email: String? = null



}