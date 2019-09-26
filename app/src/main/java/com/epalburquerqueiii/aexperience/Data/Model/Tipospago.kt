package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Tipospago : Serializable {

    constructor(
       Id: Int?,
       Nombre: String?

    ) {
       this.Id = Id
       this.Nombre = Nombre
    }

    var Id: Int? = null
    var Nombre: String? = null

}