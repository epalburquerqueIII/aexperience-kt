package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Horadia : Serializable {

    constructor(
        ID: Int?,
        IdEspacio: Int?,
        Fecha: String?,
        IdHoras : Int?


    ) {
        this.ID = ID
        this.IdEspacio = IdEspacio
        this.Fecha = Fecha
        this.IdHoras = IdHoras

    }

    var ID: Int? = null
    var IdEspacio: Int? = null
    var Fecha: String? = null
    var IdHoras: Int?=null

}