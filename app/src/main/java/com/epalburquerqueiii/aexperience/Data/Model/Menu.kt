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
        HanledFunc: String?

    ) {
        this.Id = Id
        this.ParentId = ParentId
        this.Orden = Orden
        this.Titulo = Titulo
        this.Icono = Icono
        this.Url = Url
        this.HanledFunc = HanledFunc
    }

    var Id: Int? = null
    var ParentId: Int? = null
    var Orden: Int? = null
    var Titulo: String? = null
    var Icono: String? = null
    var Url: String? = null
    var HanledFunc: String? = null



}