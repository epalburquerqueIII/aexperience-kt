package com.epalburquerqueiii.aexperience.UI.Reservas

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import com.epalburquerqueiii.aexperience.Data.Model.Horadia
import com.epalburquerqueiii.aexperience.Data.Model.Horasdias
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import com.epalburquerqueiii.aexperience.Data.Network.HorariosApi
import com.epalburquerqueiii.aexperience.Data.Network.HorasdiaApi
import com.epalburquerqueiii.aexperience.Data.Network.ReservasApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder

import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.UI.Dialog.DatePickerFragment
import kotlinx.android.synthetic.main.activity_horario.*
import kotlinx.android.synthetic.main.activity_reserva.*
import kotlinx.android.synthetic.main.item_horareservadas.*
import kotlinx.android.synthetic.main.item_reserva.*


import kotlinx.android.synthetic.main.reservahoras_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservaHorasFragment : Fragment() {

    private var fecha: String = ""

    private lateinit var horasSwitch : ArrayList<Switch>
    private lateinit var horas : ArrayList<Int>

    companion object {
        fun newInstance() = ReservaHorasFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reservahoras_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        horasSwitch= ArrayList<Switch>()

        horasSwitch.add(Hora1 as Switch)
        horasSwitch.add(Hora2 as Switch)
        horasSwitch.add(Hora3 as Switch)
        horasSwitch.add(Hora4 as Switch)
        horasSwitch.add(Hora5 as Switch)
        horasSwitch.add(Hora6 as Switch)
        horasSwitch.add(Hora7 as Switch)
        horasSwitch.add(Hora8 as Switch)

        // Llamada a Horas dia

        val get = RetrofitBuilder.builder().create(HorasdiaApi::class.java)
        val callget = get.Get()

        callget.enqueue(object : Callback<Horasdias> {
            override fun onResponse(call: Call<Horasdias>, response: Response<Horasdias>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Horasdias
                val size = response.Records!!.size
                var datos = response.Records!!
                if (size > 0) {
                    for (item in datos.indices) {
                        horasSwitch[item].setText(datos[item].IdHoras.toString())
                    }

                }
            }

            override fun onFailure(call: Call<Horasdias>, t: Throwable) {
                Log.i("Error en la reserva :", "" + t.message)
            }

        })


        Fecha24.setOnClickListener {
            showDatePickerDialog()
        }


        btn_guardar.setOnClickListener {
            val post = RetrofitBuilder.builder().create(ReservasApi::class.java)
// recorrer horasSwitch y si esta marcado llama a la funcion
            var IdEspacios: Int = 1
            IdEspacios = IdEspacioh.selectedItemPosition


            val callcreate = post.Reservahora(
                IdEspacios,
                Fecha24.text.toString(),
                if (horasSwitch[0].isChecked) {
                    horasSwitch[0].text.toString()
                } else {
                    "0"
                },
                if (horasSwitch[1].isChecked) {
                    horasSwitch[1].text.toString()
                } else {
                    "0"
                },
                if (horasSwitch[2].isChecked) {
                    horasSwitch[2].text.toString()
                } else {
                    "0"
                },
                if (horasSwitch[3].isChecked) {
                    horasSwitch[3].text.toString()
                } else {
                    "0"
                },
                if (horasSwitch[4].isChecked) {
                    horasSwitch[4].text.toString()
                } else {
                    "0"
                },
                if (horasSwitch[5].isChecked) {
                    horasSwitch[5].text.toString()
                } else {
                    "0"
                },
                if (horasSwitch[6].isChecked) {
                    horasSwitch[6].text.toString()
                } else {
                    "0"
                },
                if (horasSwitch[7].isChecked) {
                    horasSwitch[7].text.toString()
                } else {
                    "0"
                }
            )


            callcreate.enqueue(object : Callback<responseModel> {
                override fun onFailure(call: Call<responseModel>, t: Throwable) {
                    // Toast.makeText(this@PagosActivity,"failure",Toast.LENGTH_SHORT).show()
                    Log.i("dasboardfragment:", "" + t.message)

                }

                override fun onResponse(
                    call: Call<responseModel>,
                    response: Response<responseModel>
                ) {
                    //Toast.makeText(activity,"succes",Toast.LENGTH_SHORT).show()
                    @Suppress("NAME_SHADOWING")
                    val response = response.body() as responseModel
                    println("test : " + response.Error)

                }

            })
        }
    }

        private fun showDatePickerDialog() {
            val newFragment =
                DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    // +1 because January is zero
                    val selectedDate = "%02d-%02d-%04d".format(day, month + 1, year)
                    Fecha24.setText(selectedDate)
                    fecha = selectedDate
                })

            newFragment.show(supportFragmentManager, "datePicker")
        }
        // Fecha con datepicker
        // llamar servicio horas disponible retrofit
        // crear arraylist con controles swith ( Juan Antonio ) y rellenar los textos con horas y deshabilitar
        // las horas ocupadas
        // traer los datos de espacios para saber el precio
        // Importe de pago
        // poner boton de reservar y en el evento llamar al servicio para grabar la reserva
        // Crear


    }



