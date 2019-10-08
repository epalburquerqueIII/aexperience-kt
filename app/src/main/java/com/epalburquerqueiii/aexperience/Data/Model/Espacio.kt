package com.epalburquerqueiii.aexperience.Data.Model

import com.epalburquerqueiii.aexperience.Data.Util.Comun
import java.io.Serializable

class Espacio : Serializable {

    constructor(
        ID: Int?,
        Descripcion: String?,
        Estado: Int?,
        Modo: Int?,
        Precio: Int?,
        IDTipoevento: Int?,
        Fecha: String?,
        Aforo: Int?,
        NumeroReservaslimite: Int?

    ) {
        this.ID = ID
        this.Descripcion = Descripcion
        this.Estado = Estado
        this.Modo = Modo
        this.Precio = Precio
        this.IDTipoevento = IDTipoevento
        this.NumeroReservaslimite = NumeroReservaslimite
        this.Aforo = Aforo
        this.Fecha = Fecha

    }

    var ID: Int? = null
    var Descripcion: String? = null
    var Estado: Int? = null
    var Modo: Int? = null
    var Precio: Int? = null
    var IDTipoevento: Int? = null
    var NumeroReservaslimite: Int? = null
    var Aforo: Int? = null
    var Fecha: String? = null
    var ShowFecha: String = Comun.StringYMDtoDMY(Fecha)

}