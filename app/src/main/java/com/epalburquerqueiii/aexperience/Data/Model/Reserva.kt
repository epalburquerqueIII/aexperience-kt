package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Reserva : Serializable {

    constructor(
        Id: Int?,
        Fecha: String?,
        Sesiones: Int?,
        Hora: Int?,
        IdUsuario: Int?,
        UsuarioNombre: String?,
        IdEspacio: Int?,
        EspacioNombre: String?,
        IdAutorizado: Int?,
        AutorizadoNombre: String?



    ) {
        this.Id = Id
        this.Fecha = Fecha
        this.Sesiones = Sesiones
        this.Hora = Hora
        this.IdUsuario = IdUsuario
        this.UsuarioNombre = UsuarioNombre
        this.IdEspacio = IdEspacio
        this.EspacioNombre = EspacioNombre
        this.IdAutorizado = IdAutorizado
        this.AutorizadoNombre = AutorizadoNombre
    }

    var Id: Int? = null
    var Fecha: String? = null
    var Sesiones: Int? = null
    var Hora: Int? = null
    var IdUsuario: Int? = null
    var UsuarioNombre: String? = null
    var IdEspacio: Int? = null
    var EspacioNombre: String? = null
    var IdAutorizado: Int? = null
    var AutorizadoNombre: String? = null


}