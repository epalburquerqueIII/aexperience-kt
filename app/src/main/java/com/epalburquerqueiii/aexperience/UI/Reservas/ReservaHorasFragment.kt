package com.epalburquerqueiii.aexperience.UI.Reservas

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.epalburquerqueiii.aexperience.Data.Model.AppData
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

    private lateinit var horasCheckBox: ArrayList<CheckBox>
    private lateinit var horas: ArrayList<Int>

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



        horasCheckBox = ArrayList<CheckBox>()

        horasCheckBox.add(Hora1 as CheckBox)
        horasCheckBox.add(Hora2 as CheckBox)
        horasCheckBox.add(Hora3 as CheckBox)
        horasCheckBox.add(Hora4 as CheckBox)
        horasCheckBox.add(Hora5 as CheckBox)
        horasCheckBox.add(Hora6 as CheckBox)
        horasCheckBox.add(Hora7 as CheckBox)
        horasCheckBox.add(Hora8 as CheckBox)

        // Llamada a Horas dia

        val get = RetrofitBuilder.builder().create(HorasdiaApi::class.java)
        val callget = get.List(AppData.CsrfRef)

        callget.enqueue(object : Callback<Horasdias> {
            override fun onResponse(call: Call<Horasdias>, response: Response<Horasdias>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Horasdias
                val size = response.Records!!.size
                var datos = response.Records!!

                if (size > 0) {
                    for (item in datos.indices) {
                        horasCheckBox[item].setText(datos[item].Hora.toString())

                        if (datos[item].Reservado == 1) {
                            horasCheckBox[0].setChecked(true)
                            horasCheckBox[0].setEnabled(false)
                        }
                        if (datos[item].Reservado == 1) {
                            horasCheckBox[1].setChecked(true)
                            horasCheckBox[1].setEnabled(false)
                        }
                        if (datos[item].Reservado == 1) {
                            horasCheckBox[2].setChecked(true)
                            horasCheckBox[2].setEnabled(false)
                        }
                        if (datos[item].Reservado == 1) {
                            horasCheckBox[3].setChecked(true)
                            horasCheckBox[3].setEnabled(false)
                        }
                        if (datos[item].Reservado == 1) {
                            horasCheckBox[4].setChecked(true)
                            horasCheckBox[4].setEnabled(false)
                        }
                        if (datos[item].Reservado == 1) {
                            horasCheckBox[5].setChecked(true)
                            horasCheckBox[5].setEnabled(false)
                        }
                        if (datos[item].Reservado == 1) {
                            horasCheckBox[6].setChecked(true)
                            horasCheckBox[6].setEnabled(false)
                        }
                        if (datos[item].Reservado == 1) {
                            horasCheckBox[7].setChecked(true)
                            horasCheckBox[7].setEnabled(false)
                        }
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
// recorrer horasCheckBox y si esta marcado llama a la funcion
            var IdEspacios: Int = 1
            IdEspacios = IdEspacioh.selectedItemPosition

            val callcreate = post.Reservahora(
                AppData.CsrfRef,
                IdEspacios,
                Fecha24.text.toString(),
                if (horasCheckBox[0].isChecked) {
                    horasCheckBox[0].text.toString()
                } else {
                    "0"
                },
                if (horasCheckBox[1].isChecked) {
                    horasCheckBox[1].text.toString()
                } else {
                    "0"
                },
                if (horasCheckBox[2].isChecked) {
                    horasCheckBox[2].text.toString()
                } else {
                    "0"
                },
                if (horasCheckBox[3].isChecked) {
                    horasCheckBox[3].text.toString()
                } else {
                    "0"
                },
                if (horasCheckBox[4].isChecked) {
                    horasCheckBox[4].text.toString()
                } else {
                    "0"
                },
                if (horasCheckBox[5].isChecked) {
                    horasCheckBox[5].text.toString()
                } else {
                    "0"
                },
                if (horasCheckBox[6].isChecked) {
                    horasCheckBox[6].text.toString()
                } else {
                    "0"
                },
                if (horasCheckBox[7].isChecked) {
                    horasCheckBox[7].text.toString()
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
                fecha = selectedDate
                Fecha24.setText(selectedDate)
            })

        newFragment.show(activity!!.supportFragmentManager, "datePicker")

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



