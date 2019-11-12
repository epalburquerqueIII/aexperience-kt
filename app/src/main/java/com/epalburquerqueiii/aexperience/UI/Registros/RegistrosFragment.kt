package com.epalburquerqueiii.aexperience.UI.Registros

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.Data.Network.UsuariosApi
import com.epalburquerqueiii.aexperience.Data.Util.Comun
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.UI.Dialog.DatePickerFragment
import kotlinx.android.synthetic.main.fragment_registro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest


class RegistrosFragment : Fragment() {

    fun String.SH256(): String {
        val md = MessageDigest.getInstance("SHA-256")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }

        private lateinit var viewModel: RegistrosViewModel
        override fun onCreateView(

            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

        ): View? {
            return inflater.inflate(R.layout.fragment_registro, container, false)


        }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        FechaNacimientoR.setOnClickListener {
            showDatePickerDialog()
        }


        btn_registrar.setOnClickListener {
            // Validamos los datos
            var ok: Boolean
            if (PasswordR.text.toString() != Repetirpassword.text.toString()) {
                textError.setText("Las contraseñas son distintas")
                ok = false
            } else {
                ok = Comun.validarEmail(EmailR.text.toString())
                if (!ok) {
                    textError.text = "Email no valido"
                }
            }

            if (ok) {

                val post = RetrofitBuilder.builder().create(UsuariosApi::class.java)
                val callcreate = post.register(
                    NombreRegistro.text.toString(),
                    NifR.text.toString(),
                    EmailR.text.toString(),
                    FechaNacimientoR.text.toString() ,
                    TelefonoR.text.toString(),
                    PasswordR.text.toString().SH256()
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
// Changed true


                    }

                })


            }

        }
    }
    fun Int.twoDigits() =
        if (this <= 9) "0$this" else this.toString()
    private fun showDatePickerDialog() {
        val newFragment =
            DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                // +1 because January is zero
                val dayStr = day.twoDigits()
                val monthStr = (month + 1).twoDigits() // +1 because January is zero

                val selectedDate = "$dayStr-$monthStr-$year"
                FechaNacimientoR.setText(selectedDate)
            })

        newFragment.show(activity!!.supportFragmentManager, "datePicker")
    }
}
