package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Menu : Serializable {

    constructor(
        Id: Int?,
        ParentId: Int?,
        Orden: Int?,
        Titulo: String?,
        Icono: String?,
        Url : String?,
        HandleFunc: String?

    ) {
        this.Id = Id
        this.ParentId = ParentId
        this.MenuParent = MenuParent
        this.Orden = Orden
        this.Titulo = Titulo
        this.Icono = Icono
        this.Url = Url
        this.HandleFunc = HandleFunc
    }

    var Id: Int? = null
    var ParentId: Int? = null
    var MenuParent: String? = null
    var Orden: Int? = null
    var Titulo: String? = null
    var Icono: String? = null
    var Url: String? = null
    var HandleFunc: String? = null



}