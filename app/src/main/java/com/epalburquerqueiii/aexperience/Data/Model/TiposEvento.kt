package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class TiposEvento : Serializable {

    constructor(
        ID: Int?,
        Nombre: String?


    ) {
        this.ID = ID
        this.Nombre = Nombre
    }

    var ID: Int? = null
    var Nombre: String? = null


}