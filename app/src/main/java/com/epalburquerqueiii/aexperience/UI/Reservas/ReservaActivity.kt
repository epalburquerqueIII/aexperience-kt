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
import com.epalburquerqueiii.aexperience.Data.Model.Option
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Model.Reserva
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import com.epalburquerqueiii.aexperience.Data.Network.*
import com.epalburquerqueiii.aexperience.Data.Util.Comun
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.UI.Dialog.DatePickerFragment
import com.epalburquerqueiii.aexperience.databinding.ActivityReservaBinding
import kotlinx.android.synthetic.main.activity_consumo_bono.*
import kotlinx.android.synthetic.main.activity_reserva.*
import kotlinx.android.synthetic.main.editupdate_botton.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservaActivity : AppCompatActivity() {

    private lateinit var viewModel: ReservasViewModel

    private var modo:Int? = 0
    private val Crear = 0
    private val Editar = 1
    private var fecha : String = ""
    private var fechapago : String = ""

    private lateinit var recordsusu: ArrayList<Option>
    private lateinit var recordsespa: ArrayList<Option>
    private lateinit var recordsauto: ArrayList<Option>

    // TODO tomar el idusuario que se haga el login en el móvil
    //private val idusuario:Int = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModelAndObserve()


        val binding = DataBindingUtil.setContentView<ActivityReservaBinding>(this@ReservaActivity,R.layout.activity_reserva)

        // var addnotemodel = ViewModelProviders.of(this).get(AutorizadosViewModel::class.java)

        val bundle:Bundle? = intent.extras
        val registro = intent.extras.get("registro") as Reserva

//  sin databinding los campo se rellenarían manualmente
/*
        val registro = Autorizado(bundle?.getInt("ID"),
                                bundle?.getString("NombreAutorizado"),
                                bundle?.getString("Nif"))
*/

        modo = bundle?.getInt("modo")


        if (modo == Editar){
            btn_delete.visibility = View.VISIBLE
            registro.Fecha = Comun.StringYMDtoDMY(registro.FechaPago)
            registro.FechaPago = Comun.StringYMDtoDMY(registro.FechaPago)
            binding.setVariable(BR.addreservaviewmodel,registro)
            binding.executePendingBindings()
        }



        btn_save.setOnClickListener {
            if (modo == Crear) {
                create()
            }else{
                update(registro.Id!!)
            }
        }

        btn_delete.setOnClickListener{
            delete(registro.Id!!)
        }

        // Obtiene los Usuarios
        val get = RetrofitBuilder.builder().create(UsuariosApi::class.java)
        val callget = get.GetOptions()

        callget.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel :Int = 0
                recordsusu = response.Options!!
                if (size > 0) {
                    val Usuarios = ArrayList<String>()
                    var i:Int = 0
                    for ( item in recordsusu){
                        Usuarios.add(item.DisplayText.toString())
                        if (registro.IdUsuario == item.Value) {
                            sel = i }
                        i++
                    }
                    val adapterusuario = ArrayAdapter(this@ReservaActivity,
                        android.R.layout.simple_spinner_dropdown_item,
                        Usuarios
                    )
                    // Set Adapter to Spinner
                    cbUsuario!!.setAdapter(adapterusuario)
                    cbUsuario.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@ReservaActivity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Error en Usuarios :", "" + t.message)
            }

        })

        // Obtiene los Espacios
        val getEspacios = RetrofitBuilder.builder().create(EspaciosApi::class.java)
        val callgetEspacios = getEspacios.GetOptions()

        callgetEspacios.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel :Int = 0
                recordsespa = response.Options!!
                if (size > 0) {
                    val Espacios = ArrayList<String>()
                    var i:Int = 0
                    for ( item in recordsespa){
                        Espacios.add(item.DisplayText.toString())
                        if (registro.IdEspacio == item.Value) {
                            sel = i }
                        i++
                    }
                    val adapterespacio = ArrayAdapter(this@ReservaActivity, android.R.layout.simple_spinner_dropdown_item, Espacios)
                    // Set Adapter to Spinner
                    cbEspacio!!.setAdapter(adapterespacio)
                    cbEspacio.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@ReservaActivity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Error en Espacios :", "" + t.message)
            }

        })

        // Obtiene los Autorizados
        val getAutorizados = RetrofitBuilder.builder().create(AutorizadosApi::class.java)
        val callgetAutorizados = getAutorizados.GetOptions()

        callgetAutorizados.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel :Int = 0
                recordsauto = response.Options!!
                if (size > 0) {
                    val Autorizados = ArrayList<String>()
                    var i:Int = 0
                    for ( item in recordsauto){
                        Autorizados.add(item.DisplayText.toString())
                        if (registro.IdAutorizado == item.Value) {
                            sel = i }
                        i++
                    }
                    val adapterautorizado = ArrayAdapter(this@ReservaActivity, android.R.layout.simple_spinner_dropdown_item, Autorizados)
                    // Set Adapter to Spinner
                    cbAutorizado!!.setAdapter(adapterautorizado)
                    cbAutorizado.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@ReservaActivity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Error en Autorizados :", "" + t.message)
            }

        })

        FechaR.setOnClickListener {
            showDatePickerDialogFecha()
        }
        FechaPago.setOnClickListener {
            showDatePickerDialogFechaPago()
        }
        FechaPago.setOnClickListener {
            showDatePickerDialog1()
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

    private fun showDatePickerDialogFecha() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            val selectedDate = "%02d-%02d-%04d".format(day,month+1,year)
            FechaR.setText(selectedDate)
            fecha = selectedDate

        })

        newFragment.show(supportFragmentManager, "datePicker")
    }

    private fun showDatePickerDialogFechaPago() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            val selectedDate = "%02d-%02d-%04d".format(day,month+1,year)
            FechaPago.setText(selectedDate)
            fechapago = selectedDate
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }

    private fun showDatePickerDialog1() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
            FechaPago.setText(selectedDate)
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }

    private fun create(){

        val post = RetrofitBuilder.builder().create(ReservasApi::class.java)

        var IdUsuario :Int = 0
        if (recordsusu.size > 0) {
            IdUsuario = recordsusu[cbUsuario.selectedItemPosition].Value!!.toInt()
        }
        var IdEspacio :Int = 0
        if (recordsespa.size > 0) {
            IdEspacio = recordsespa[cbEspacio.selectedItemPosition].Value!!.toInt()
        }
        var IdAutorizado :Int = 0
        if (recordsauto.size > 0) {
            IdAutorizado = recordsauto[cbAutorizado.selectedItemPosition].Value!!.toInt()
        }

        val callcreate = post.Create(fecha,
            fechapago,
            Hora.text.toString().toInt(),
            IdUsuario,
            IdEspacio,
            IdAutorizado)
        callcreate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                // Toast.makeText(this@AutorizadoActivity,"failure",Toast.LENGTH_SHORT).show()
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

        val post = RetrofitBuilder.builder().create(ReservasApi::class.java)

        var IdUsuario :Int = 0
        if (recordsusu.size > 0) {
            IdUsuario = recordsusu[cbUsuario.selectedItemPosition].Value!!.toInt()
        }
        var IdEspacio :Int = 0
        if (recordsespa.size > 0) {
            IdEspacio = recordsespa[cbEspacio.selectedItemPosition].Value!!.toInt()
        }
        var IdAutorizado :Int = 0
        if (recordsauto.size > 0) {
            IdAutorizado = recordsauto[cbAutorizado.selectedItemPosition].Value!!.toInt()
        }

        val callUpdate = post.Update(Id,
            fecha,
            fechapago,
            Hora.text.toString().toInt(),
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
