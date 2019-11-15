package com.epalburquerqueiii.aexperience.UI.Espacios

import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.Evento
import com.epalburquerqueiii.aexperience.Data.Model.Eventos
import com.epalburquerqueiii.aexperience.Data.Network.EventosApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EspaciosUIViewModel : ViewModel() {

    private val registros = MutableLiveData<ArrayList<Evento>>()

    private fun setRegistros(listregistros:ArrayList<Evento>) {
        registros.value = listregistros
    }

    fun getRegistros(){
        setRegistros(getRecords())
    }

    fun getregistrosLiveData(): LiveData<ArrayList<Evento>> {
        return registros
    }

    fun getRecords():ArrayList<Evento> {

        var datos = ArrayList<Evento>()

        datos!!.add(Evento(0,"Pabellón Polideportivo Alburquerque","","",
            "",
             "images/espacios/pabellon.jpg",
            "images/espacios/pabellon.jpg",
            "",
            "",
            "",
            "",
            "Horarios\\n\" +\n" +
                    "                \"Invierno: Lunes a Viernes 17:00 - 23:00 h. , Sábados 11:00 - 14:00 / 17:00 - 21:00 h.\\n\" +\n" +
                    "                \"Primavera: Lunes a Viernes 18:00 - 23:00 h. , Sábados 11:00 - 14:00 / 18:00 - 21:00 h.\\n\" +\n" +
                    "                \"Verano: Lunes a Viernes 11:00 - 14:00 / 18:00 - 23:00 h. , Sábados 11:00 - 14:00 / 17:00 - 21:00 h.\\n\" +\n" +
                    "                \"*Domingos y Festivos Cerrado\\n\" +\n" +
                    "                \"Precios\\n\" +\n" +
                    "                \"Adultos (> 14 años): 7,21€\\n\" +\n" +
                    "                \"Menores (< 14 años): 3,01€"))
        datos!!.add(Evento(1,"Pista Anexa del Pabellón Polideportivo","","","","images/espacios/pista.jpg","images/espacios/pista.jpg","","","","","Horarios\\n\" +\n" +
                "                \"Invierno: Lunes a Viernes 17:00 - 23:00 h. , Sábados 11:00 - 14:00 / 17:00 - 21:00 h.\\n\" +\n" +
                "                \"Primavera: Lunes a Viernes 18:00 - 23:00 h. , Sábados 11:00 - 14:00 / 18:00 - 21:00 h.\\n\" +\n" +
                "                \"Verano: Lunes a Viernes 11:00 - 14:00 / 18:00 - 23:00 h. , Sábados 11:00 - 14:00 / 17:00 - 21:00 h.\\n\" +\n" +
                "                \"*Domingos y Festivos Cerrado\\n\" +\n" +
                "                \"Precios\\n\" +\n" +
                "                \"Adultos (> 14 años): 7,21€\\n\" +\n" +
                "                \"Menores (< 14 años): 3,01€\\n\" +\n" +
                "                \"Dirección\\n\" +\n" +
                "                \"Calle Santiago, 0, 06510 Alburquerque, Badajoz\\n\" +\n" +
                "                \"Teléfono\\n\" +\n" +
                "                \"924 40 11 32  "))
        setRegistros(datos)

        datos!!.add(Evento(2,"Anfiteatro Casa de la Cultura Luis Landero","","","","images/espacios/anfiteatro.jpg","images/espacios/anfiteatro.jpg","","","","","Horarios\\n\" +\n" +
                "                \"Lunes a Viernes 10:00 - 14:00 / 17:00 - 21:00 h.\\n\" +\n" +
                "                \"*Sábados y Domingos Cerrado\\n\" +\n" +
                "                \"Dirección\\n\" +\n" +
                "                \"Avda. de Extremadura s/n 06510 Alburquerque, Badajoz\\n\" +\n" +
                "                \"Teléfono\\n\" +\n" +
                "                \"924 40 04 68"))
        setRegistros(datos)

        datos!!.add(Evento(3,"Piscina Municipal Climatizada","","","","images/espacios/piscina.jpg","images/espacios/piscina.jpg","","","","","Horarios\\n\" +\n" +
                "                \"Verano: Lunes a Domingo 12:00 - 21:00 h.\\n\" +\n" +
                "                \"*En Invierno Cerrada\\n\" +\n" +
                "                \"Precios\\n\" +\n" +
                "                \"70 entradas: 50,00 €\\n\" +\n" +
                "                \"25 entradas: 25,00 €\\n\" +\n" +
                "                \"10 entradas: 12,00 €\\n\" +\n" +
                "                \"Diaria Adulto: 3,00 €\\n\" +\n" +
                "                \"Diaria Menores y Pensionistas: 1,50 €\\n\" +\n" +
                "                \"Dirección\\n\" +\n" +
                "                \"Ctra. Valencia de Alcántara - Badajoz, Km 31, 06510 Alburquerque, Badajoz\\n\" +\n" +
                "                \"Teléfono\\n\" +\n" +
                "                \"924 40 05 12 "))
        setRegistros(datos)

        datos!!.add(Evento(4,"Aulas en la Casa de la Cultura Luis Landero","","","","images/espacios/cultura.jpg","images/espacios/cultura.jpg","","","","","Horarios\\n\" +\n" +
                "                \"Lunes a Viernes 10:00 - 14:00 / 17:00 - 21:00 h.\\n\" +\n" +
                "                \"*Sábados y Domingos Cerrada\\n\" +\n" +
                "                \"Dirección\\n\" +\n" +
                "                \"Avda. de Extremadura s/n 06510 Alburquerque, Badajoz\\n\" +\n" +
                "                \"Teléfono\\n\" +\n" +
                "                \"924 40 04 68 "))
        setRegistros(datos)

        datos!!.add(Evento(5,"Teatro Casa de la Cultura Luis Landero","","","","images/espacios/teatro.jpg","images/espacios/teatro.jpg","","","","","Horarios\\n\" +\n" +
                "                \"Lunes a Viernes 10:00 - 14:00 / 17:00 - 21:00 h.\\n\" +\n" +
                "                \"*Sábados y Domingos Cerrada\\n\" +\n" +
                "                \"Dirección\\n\" +\n" +
                "                \"Avda. de Extremadura s/n 06510 Alburquerque, Badajoz\\n\" +\n" +
                "                \"Teléfono\\n\" +\n" +
                "                \"924 40 04 68 "))
        setRegistros(datos)

        return datos
    }

}