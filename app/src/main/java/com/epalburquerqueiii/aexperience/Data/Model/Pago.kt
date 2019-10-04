package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Pago : Serializable {

    constructor(
        Id: Int?,
        IdReserva: Int?,
        FechaPago: String?,
        IdTipospago: Int?,
        NumeroTarjeta: String?
    ) {
        this.Id = Id
        this.IdReserva = IdReserva
        this.FechaPago = FechaPago
        this.IdTipospago = IdTipospago
        this.NumeroTarjeta = NumeroTarjeta
    }

    var Id: Int? = null
    var IdReserva: Int? = null
    var FechaPago: String? = null
    var IdTipospago: Int? = null
    var NumeroTarjeta: String? = null
}