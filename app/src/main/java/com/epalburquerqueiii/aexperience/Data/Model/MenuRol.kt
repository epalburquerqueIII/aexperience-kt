package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class MenuRol : Serializable {

    constructor(
        Id: Int?,
        idMenu: Int?,
        IDUsuarioRoles: Int?
    ) {
        this.Id = Id
        this.idMenu = idMenu
        this.IDUsuarioRoles = IDUsuarioRoles
    }

    var Id: Int? = null
    var idMenu: Int? = null
    var IDUsuarioRoles: Int? = null
}