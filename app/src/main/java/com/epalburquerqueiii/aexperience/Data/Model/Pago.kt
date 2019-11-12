package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Pago : Serializable {

    constructor(
        Id: Int?,
        IdReserva: Int?,
        FechaPago: String?,
        IdTipopago: Int?,
        NumeroTarjeta: String?,
        Importe: Float?,
        Referencia: String?
    ) {
        this.Id = Id
        this.IdReserva = IdReserva
        this.FechaReserva = FechaReserva
        this.FechaPago = FechaPago
        this.IdTipopago = IdTipopago
        this.TipoPago = TipoPago
        this.NumeroTarjeta = NumeroTarjeta
        this.Importe = Importe
        this.Referencia = Referencia
    }

    var Id: Int? = null
    var IdReserva: Int? = null
    var FechaReserva: String? = null
    var FechaPago: String? = null
    var IdTipopago: Int? = null
    var TipoPago: String? = null
    var NumeroTarjeta: String? = null
    var Importe: Float? = null
    var Referencia: String? = null
}