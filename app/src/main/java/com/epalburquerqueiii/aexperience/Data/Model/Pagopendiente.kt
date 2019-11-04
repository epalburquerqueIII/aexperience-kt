package com.epalburquerqueiii.aexperience.Data.Model

import java.io.Serializable

class Pagopendiente : Serializable {

    constructor(
        Id: Int?,
        IdReserva: Int?,
        ReservaNombre: String?,
        FechaPago: String?,
        IdTipopago: Int?,
        TipopagoNombre: String?,
        NumeroTarjeta: String?,
        Importe: Float?,
        Referencia: String?
    ) {
        this.Id = Id
        this.IdReserva = IdReserva
        this.ReservaNombre = ReservaNombre
        this.FechaPago = FechaPago
        this.IdTipopago = IdTipopago
        this.TipopagoNombre = TipopagoNombre
        this.NumeroTarjeta = NumeroTarjeta
        this.Importe = Importe
        this.Referencia = Referencia
    }

    var Id: Int? = null
    var IdReserva: Int? = null
    var ReservaNombre: String? = null
    var FechaPago: String? = null
    var IdTipopago: Int? = null
    var TipopagoNombre: String? = null
    var NumeroTarjeta: String? = null
    var Importe: Float? = null
    var Referencia: String? = null
}