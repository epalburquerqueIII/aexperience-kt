package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class MenuRol : Serializable {

    constructor(
        ID: Int?,
        IDMenu: Int?,
        IDUsuarioRoles: Int?
    ) {
        this.ID = ID
        this.IDMenu = IDMenu
        this.IDUsuarioRoles = IDUsuarioRoles
    }

    var ID: Int? = null
    var IDMenu: Int? = null
    var IDUsuarioRoles: Int? = null
}