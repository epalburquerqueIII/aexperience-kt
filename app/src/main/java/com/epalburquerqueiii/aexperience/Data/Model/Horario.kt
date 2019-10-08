package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Horario : Serializable {

    constructor(
        ID: Int?,
        IDEspacio: Int?,
        Descripcion: String?,
        Fechainicio: String?,
        Fechafinal: String?,
        Hora : Int?

    ) {
        this.ID = ID
        this.IDEspacio = IDEspacio
        this.Descripcion = Descripcion
        this.Fechainicio = Fechainicio
        this.Fechafinal = Fechafinal
        this.Hora = Hora
    }

    var ID: Int? = null
    var IDEspacio: Int? = null
    var Descripcion: String? = null
    var Fechainicio: String? = null
    var Fechafinal: String? = null
    var Hora: Int? = null

}