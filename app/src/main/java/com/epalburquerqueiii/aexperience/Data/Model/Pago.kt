package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Pago : Serializable {

    constructor(
        Id: Int?,
        IdReserva: Int?,
       // FechaReserva: String?,
        FechaPago: String?,
        IdTipopago: Int?,
       // TipoPago: String?,
        Importe: Float?,
        NumeroTarjeta: String?
    ) {
        this.Id = Id
        this.IdReserva = IdReserva
        this.FechaReserva = FechaReserva
        this.FechaPago = FechaPago
        this.IdTipopago = IdTipopago
        this.TipoPago = TipoPago
        this.Importe = Importe
        this.NumeroTarjeta = NumeroTarjeta
    }

    var Id: Int? = null
    var IdReserva: Int? = null
    var FechaReserva: String? = null
    var FechaPago: String? = null
    var IdTipopago: Int? = null
    var TipoPago: String? = null
    var Importe: Float? = null
    var NumeroTarjeta: String? = null
}