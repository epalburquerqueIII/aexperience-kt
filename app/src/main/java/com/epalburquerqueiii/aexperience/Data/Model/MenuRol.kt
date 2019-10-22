package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class MenuRol : Serializable {

    constructor(
        Id: Int?,
        idMenu: Int?,
        IdUsuarioRoles: Int?
    ) {
        this.Id = Id
        this.idMenu = idMenu
        this.IdUsuarioRoles = IdUsuarioRoles
    }

    var Id: Int? = null
    var idMenu: Int? = null
    var IdUsuarioRoles: Int? = null
}