package com.epalburquerqueiii.aexperience.UI.Pagos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.epalburquerqueiii.aexperience.Data.Model.Pago
import com.epalburquerqueiii.aexperience.Data.Model.Pagopendiente
import com.epalburquerqueiii.aexperience.Data.Util.Comun
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.activity_pagopendiente.*
import kotlinx.android.synthetic.main.activity_pagopendiente.view.*
import kotlinx.android.synthetic.main.confirmar_botton.*
import kotlinx.android.synthetic.main.editupdate_botton.*

class PagopendienteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagopendiente)

        val bundle:Bundle? = intent.extras
        val registro = intent.extras.get("registro") as Pagopendiente
// Rellenar lo campos
        // pedir la referencia
        // Crear api en golang para confirmar el pago de pagopendiente a pago
        //Cuando ya se confirme en pago borrar de la tabla de paga pendient
        // sesiones a usuarios si sesiones <> 0º
        // crear llamada en Android

        var MostrarFecha : String? = registro.ReservaNombre
        ppreserva.setText(Comun.StringYMDtoDMY(MostrarFecha))
        var MostrarFechaPago : String? = registro.FechaPago
        ppfecha.setText(Comun.StringYMDtoDMY(MostrarFechaPago))
        pptipopago.setText(registro.TipopagoNombre)
        ppimporte.setText(registro.Importe.toString())
        ppreferencia.setText(registro.Referencia)

        btn_aceptar.setOnClickListener {
            confirmarpago()
        }

        btn_cancelar.setOnClickListener{
            finish()
        }
    }

    private fun confirmarpago (){

        //Obtener las sesiones de los usuarios de las reservas


    }
}
