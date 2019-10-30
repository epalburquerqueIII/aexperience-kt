package com.epalburquerqueiii.aexperience.UI.Reservas

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.epalburquerqueiii.aexperience.BR
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Model.Option
import com.epalburquerqueiii.aexperience.Data.Model.Reserva
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import com.epalburquerqueiii.aexperience.Data.Network.*
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.databinding.ActivityReservaBinding
import kotlinx.android.synthetic.main.activity_consumo_bono.*
import kotlinx.android.synthetic.main.activity_reserva.*
import com.epalburquerqueiii.aexperience.Data.Network.UsuariosApi
import com.epalburquerqueiii.aexperience.Data.Network.EspaciosApi
import com.epalburquerqueiii.aexperience.Data.Network.AutorizadosApi
import com.epalburquerqueiii.aexperience.Data.Util.Comun
import com.epalburquerqueiii.aexperience.UI.Dialog.DatePickerFragment

import kotlinx.android.synthetic.main.editupdate_botton.*
import kotlinx.android.synthetic.main.item_reserva.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class ReservaActivity : AppCompatActivity() {

    private lateinit var viewModel: ReservasViewModel
    private var modo:Int? = 0
    private val Crear = 0
    private val Editar = 1
    private var fecha : String = ""
    private var fechapago : String = ""

    private lateinit var recordsusuarios: ArrayList<Option>
    private lateinit var recordsespacios: ArrayList<Option>
    private lateinit var recordsautorizados: ArrayList<Option>

    // TODO tomar el idusuario que se haga el login en el móvil
    //private val idusuario:Int = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModelAndObserve()


        val binding = DataBindingUtil.setContentView<ActivityReservaBinding>(
            this@ReservaActivity,
            R.layout.activity_reserva
        )

        // var addnotemodel = ViewModelProviders.of(this).get(ReservasViewModel::class.java)

        val bundle: Bundle? = intent.extras
        val registro = intent.extras.get("registro") as Reserva

//  sin databinding los campo se rellenarían manualmente
/*
        val registro = Reserva(bundle?.getInt("ID"),
                                bundle?.getString("NombreReserva"),
                                bundle?.getString("Nif"))
*/

        modo = bundle?.getInt("modo")

        if (modo == Editar) {
            btn_delete.visibility = View.VISIBLE
            registro.Fecha = Comun.StringYMDtoDMY(registro.Fecha)
            registro.FechaPago = Comun.StringYMDtoDMY(registro.FechaPago)
            binding.setVariable(BR.addreservaviewmodel, registro)
            binding.executePendingBindings()
        }

        btn_save.setOnClickListener {
            if (modo == Crear) {
                create()
            } else {
                update(registro.Id!!)
            }
        }

        btn_delete.setOnClickListener {
            delete(registro.Id!!)
        }
        // Obtiene los usuarios

        val get = RetrofitBuilder.builder().create(UsuariosApi::class.java)
        val callget = get.GetOptions()

        callget.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel: Int = 0
                recordsusuarios = response.Options!!
                if (size > 0) {
                    val usuario = ArrayList<String>()
                    var i: Int = 0
                    for (item in recordsusuarios) {
                        usuario.add(item.DisplayText.toString())
                        if (registro.IdUsuario == item.Value) {
                            sel = i
                        }
                        i++
                    }
                    val adapterUsu = ArrayAdapter(
                        this@ReservaActivity,
                        android.R.layout.simple_spinner_dropdown_item,
                        usuario
                    )

                    // Set Adapter to Spinner
                    cbusuario!!.setAdapter(adapterUsu)
                    cbusuario.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@ReservaActivity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Error en usuarios :", "" + t.message)
            }

        })

        // Obtiene los espacios

        val getespacios = RetrofitBuilder.builder().create(EspaciosApi::class.java)
        val callgetespacios = getespacios.GetOptions()

        callgetespacios.enqueue(object : Callback<Options> {

            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel: Int = 0
                recordsespacios = response.Options!!
                if (size > 0) {
                    val espacios = ArrayList<String>()
                    var i: Int = 0
                    for (item in recordsespacios) {
                        espacios.add(item.DisplayText.toString())
                        if (registro.IdEspacio == item.Value) {
                            sel = i
                        }
                        i++
                    }
                    val adapterEsp = ArrayAdapter(
                        this@ReservaActivity,
                        android.R.layout.simple_spinner_dropdown_item,
                        espacios
                    )
                    // Set Adapter to Spinner
                    cbespacio!!.setAdapter(adapterEsp)
                    cbespacio.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@ReservaActivity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Error en espacios :", "" + t.message)
            }

        })

        // Obtiene los autorizados

        val getautorizados = RetrofitBuilder.builder().create(AutorizadosApi::class.java)
        val callgetautorizados = getautorizados.GetOptions()

        callgetautorizados.enqueue(object : Callback<Options> {

            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel: Int = 0
                recordsautorizados = response.Options!!
                if (size > 0) {
                    val autorizados = ArrayList<String>()
                    var i: Int = 0
                    for (item in recordsautorizados) {
                        autorizados.add(item.DisplayText.toString())
                        if (registro.IdAutorizado == item.Value) {
                            sel = i
                        }
                        i++
                    }
                    val adapterAut = ArrayAdapter(
                        this@ReservaActivity,
                        android.R.layout.simple_spinner_dropdown_item,
                        autorizados
                    )
                    // Set Adapter to Spinner
                    cbautorizado!!.setAdapter(adapterAut)
                    cbautorizado.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@ReservaActivity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Error en autorizados :", "" + t.message)
            }

        })
        FechaAct.setOnClickListener {
            showDatePickerDialog()
        }
        FechaPagoAct.setOnClickListener {
            showDatePickerDialogFechapago()
        }

    }

    private fun setupViewModelAndObserve() {
        viewModel = ViewModelProvider(this).get(ReservasViewModel::class.java)
        // TODO: Use the ViewModel si se necesita pedir datos del principal

/*
        val shareObserver = Observer<String> {
            if ( shareViewModel.getsharedata().value == "Actualizar" ){
                viewModel.getRecords()
            }

        }
        shareViewModel.getsharedata().observe(this, shareObserver)
*/

    }
//para la fecha
    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            val selectedDate = "%02d-%02d-%04d".format(day, month+1, year)
            FechaAct.setText(selectedDate)
            fecha = selectedDate
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }
//para la fecha pago
    private fun showDatePickerDialogFechapago() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            val selectedDate = "%02d-%02d-%04d".format(day, month+1, year)
            FechaPagoAct.setText(selectedDate)
            fechapago= selectedDate
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }
    private fun create(){

        var IdUsuario : Int = 1
        if (recordsusuarios.size > 0) {
            IdUsuario = recordsusuarios[cbusuario.selectedItemPosition].Value!!.toInt()
        }
        var IdEspacio : Int = 1
        if (recordsespacios.size > 0) {
            IdEspacio = recordsespacios[cbespacio.selectedItemPosition].Value!!.toInt()
        }
        var IdAutorizado : Int = 1
        if (recordsautorizados.size > 0) {
            IdAutorizado = recordsautorizados[cbautorizado.selectedItemPosition].Value!!.toInt()
        }

        val post = RetrofitBuilder.builder().create(ReservasApi::class.java)
        val callcreate = post.Create(fecha,
            fechapago,
            HoraAct.text.toString().toInt(),
            IdUsuario,
            IdEspacio,
            IdAutorizado)
        callcreate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                // Toast.makeText(this@ReservaActivity,"failure",Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragment:",""+t.message)
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                //Toast.makeText(activity,"succes",Toast.LENGTH_SHORT).show()
                @Suppress("NAME_SHADOWING")
                val response = response.body() as responseModel
                println("test : "+response.Error)
// Changed true
                viewModel.make_Change()
                finish()


            }

        })

    }

    private fun update(Id:Int){


        var IdUsuario :Int = 1
        if (recordsusuarios.size > 0) {
            IdUsuario = recordsusuarios[cbusuario.selectedItemPosition].Value!!.toInt()
        }
        var IdEspacio : Int = 1
        if (recordsespacios.size > 0) {
            IdEspacio = recordsespacios[cbespacio.selectedItemPosition].Value!!.toInt()
        }
        var IdAutorizado : Int = 1
        if (recordsautorizados.size > 0) {
            IdAutorizado = recordsautorizados[cbautorizado.selectedItemPosition].Value!!.toInt()
        }
        val post = RetrofitBuilder.builder().create(ReservasApi::class.java)
        val callUpdate = post.Update(Id,
            fecha,
            fechapago,
            HoraAct.text.toString().toInt(),
            IdUsuario,
            IdEspacio,
            IdAutorizado)
        callUpdate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                Toast.makeText(this@ReservaActivity, "Fallo $Id", Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragmetn:",""+ t.message)
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@ReservaActivity,"succes $Id", Toast.LENGTH_SHORT).show()
                @Suppress("NAME_SHADOWING")
                val response = response.body() as responseModel
                println("test : "+response.Result)

                //val resultIntent = Intent()
                //setResult(Activity.RESULT_OK,resultIntent)
// Changed true
                viewModel.make_Change()
                finish()


            }

        })

    }

    private fun delete(ID: Int){
        val post = RetrofitBuilder.builder().create(ReservasApi::class.java)
        val calldelete = post.Delete(ID.toInt())
        calldelete.enqueue(object : Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@ReservaActivity," borrado ", Toast.LENGTH_SHORT).show()
// Changed true
                viewModel.make_Change()
                finish()
            }
        })

    }


}
