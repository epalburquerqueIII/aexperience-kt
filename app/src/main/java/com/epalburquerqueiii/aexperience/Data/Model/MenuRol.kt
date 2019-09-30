package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class MenuRol : Serializable {

    constructor(
        Id: Int?,
        idMenu: Int?,
        idUsuario: Int?
    ) {
        this.Id = Id
        this.idMenu = idMenu
        this.idUsuario = idUsuario
    }

    var Id: Int? = null
    var idMenu: Int? = null
    var idUsuario: Int? = null
}