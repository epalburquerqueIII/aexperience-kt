package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Pago : Serializable {

    constructor(
        Id: Int?,
        IdReserva: Int?,
        FechaPago: String?,
        IdTipopago: Int?,
        NumeroTarjeta: String?
    ) {
        this.Id = Id
        this.IdReserva = IdReserva
        this.FechaPago = FechaPago
        this.IdTipopago = IdTipopago
        this.NumeroTarjeta = NumeroTarjeta
    }

    var Id: Int? = null
    var IdReserva: Int? = null
    var FechaPago: String? = null
    var IdTipopago: Int? = null
    var NumeroTarjeta: String? = null
}