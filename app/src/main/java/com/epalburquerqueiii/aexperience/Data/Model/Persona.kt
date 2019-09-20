package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Persona : Serializable {

    constructor(
       ID: Int?,
       Nombre: String?,
       Direccion: String?,
       Poblacion: String?,
       Provinciaid: Int?,
       Telefono: String?,
       Email: String?

    ) {
       this.ID = ID
       this.Nombre = Nombre
       this.Direccion = Direccion
       this.Poblacion = Poblacion
       this.Provinciaid = Provinciaid
       this.Telefono = Telefono
       this.Email = Email
    }

    var ID: Int? = null
    var Nombre: String? = null
    var Direccion: String? = null
    var Poblacion: String? = null
    var Provinciaid: Int? = null
    var Telefono: String? = null
    var Email: String? = null


}