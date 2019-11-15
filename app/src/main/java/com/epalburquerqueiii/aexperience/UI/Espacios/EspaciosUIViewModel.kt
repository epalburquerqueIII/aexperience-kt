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

        datos!!.add(Evento(0,"Pabellón Polideportivo Alburquerque","","Horarios\n" +
                "Invierno: Lunes a Viernes 17:00 - 23:00 h. , Sábados 11:00 - 14:00 / 17:00 - 21:00 h.\n" +
                "Primavera: Lunes a Viernes 18:00 - 23:00 h. , Sábados 11:00 - 14:00 / 18:00 - 21:00 h.\n" +
                "Verano: Lunes a Viernes 11:00 - 14:00 / 18:00 - 23:00 h. , Sábados 11:00 - 14:00 / 17:00 - 21:00 h.\n" +
                "*Domingos y Festivos Cerrado\n" +
                "Precios\n" +
                "Adultos (> 14 años): 7,21€\n" +
                "Menores (< 14 años): 3,01€",
            "",
             "images/espacios/pabellon.jpg",
            "images/espacios/pabellon.jpg",
            "",
            "",
            "",
            "",
            ""))
        datos!!.add(Evento(1,"Pista Anexa del Pabellón Polideportivo","","Horarios\n" +
                "Invierno: Lunes a Viernes 17:00 - 23:00 h. , Sábados 11:00 - 14:00 / 17:00 - 21:00 h.\n" +
                "Primavera: Lunes a Viernes 18:00 - 23:00 h. , Sábados 11:00 - 14:00 / 18:00 - 21:00 h.\n" +
                "Verano: Lunes a Viernes 11:00 - 14:00 / 18:00 - 23:00 h. , Sábados 11:00 - 14:00 / 17:00 - 21:00 h.\n" +
                "*Domingos y Festivos Cerrado\n" +
                "Precios\n" +
                "Adultos (> 14 años): 7,21€\n" +
                "Menores (< 14 años): 3,01€\n" +
                "Dirección\n" +
                "Calle Santiago, 0, 06510 Alburquerque, Badajoz\n" +
                "Teléfono\n" +
                "924 40 11 32  ","","","","","","","",""))
        setRegistros(datos)

        datos!!.add(Evento(2,"Anfiteatro Casa de la Cultura Luis Landero","","","","","","","","","",""))
        setRegistros(datos)

        datos!!.add(Evento(3,"Piscina Municipal Climatizada","","","","","","","","","",""))
        setRegistros(datos)

        datos!!.add(Evento(4,"Aulas en la Casa de la Cultura Luis Landero","","","","","","","","","",""))
        setRegistros(datos)

        datos!!.add(Evento(5,"Teatro Casa de la Cultura Luis Landero","","","","","","","","","",""))
        setRegistros(datos)

        return datos

    }

}