package com.epalburquerqueiii.aexperience.UI.Pagos

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
import com.epalburquerqueiii.aexperience.Data.Model.Pago
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import com.epalburquerqueiii.aexperience.Data.Network.PagosApi
import com.epalburquerqueiii.aexperience.Data.Network.ReservasApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.Data.Network.TipospagosApi
import com.epalburquerqueiii.aexperience.Data.Util.Comun
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.UI.Dialog.DatePickerFragment
import com.epalburquerqueiii.aexperience.databinding.ActivityPagoBinding
import kotlinx.android.synthetic.main.activity_pago.*
import kotlinx.android.synthetic.main.editupdate_botton.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PagoActivity : AppCompatActivity() {

    private lateinit var viewModel: PagosViewModel

    private var modo:Int? = 0
    private val Crear = 0
    private val Editar = 1
    private var fecha : String = ""
    private lateinit var recordsReser: ArrayList<Option>
    private lateinit var records: ArrayList<Option>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModelAndObserve()

        val binding = DataBindingUtil.setContentView<ActivityPagoBinding>(this@PagoActivity,R.layout.activity_pago)


        val bundle:Bundle? = intent.extras
        val registro = intent.extras.get("registro") as Pago


        modo = bundle?.getInt("modo")


        if (modo == Editar){
            btn_delete.visibility = View.VISIBLE
            registro.FechaPago = Comun.StringYMDtoDMY(registro.FechaPago)
            binding.setVariable(BR.addpagoviewmodel,registro)
            binding.executePendingBindings()
        }


        btn_save.setOnClickListener {

            if (fecha == "" || fecha == "" ){
                Toast.makeText(this@PagoActivity, "Error en la fecha", Toast.LENGTH_SHORT).show()
            }
            else
            if (modo == Crear) {

                create()
            }else{
                update(registro.Id!!)
            }
        }

        btn_delete.setOnClickListener{
            delete(registro.Id!!)
        }

        // Obtiene las reservas

        val get = RetrofitBuilder.builder().create(ReservasApi::class.java)
        val callget = get.GetOptions()

        callget.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel :Int = 0
                recordsReser = response.Options!!
                if (size > 0) {
                    val IdReserva = ArrayList<String>()
                    var i:Int = 0
                    for ( item in recordsReser){
                        IdReserva.add(item.DisplayText.toString())
                        if (registro.IdReserva == item.Value) {
                            sel = i }
                        i++
                    }
                    val adapter = ArrayAdapter(this@PagoActivity, android.R.layout.simple_spinner_dropdown_item, IdReserva)
                    // Set Adapter to Spinner
                    cbreserva!!.adapter = adapter
                    cbreserva.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@PagoActivity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Error en Tipo de pago :", "" + t.message)
            }

        })

        // Obtiene el Tipo de pago

        val gettipopago = RetrofitBuilder.builder().create(TipospagosApi::class.java)
        val callgettipopago = gettipopago.GetOptions()

        callgettipopago.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel :Int = 0
                records = response.Options!!
                if (size > 0) {
                    val IdTipopago = ArrayList<String>()
                    var i:Int = 0
                    for ( item in records){
                        IdTipopago.add(item.DisplayText.toString())
                        if (registro.IdTipopago == item.Value) {
                            sel = i }
                        i++
                    }
                    val adapterTipopago = ArrayAdapter(this@PagoActivity, android.R.layout.simple_spinner_dropdown_item, IdTipopago)
                    // Set Adapter to Spinner
                    cbtipopago!!.adapter = adapterTipopago
                    cbtipopago.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@PagoActivity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Error en Tipo de pago :", "" + t.message)
            }

        })

        fechapagoCB.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun setupViewModelAndObserve() {
        viewModel = ViewModelProvider(this).get(PagosViewModel::class.java)
        // TODO: Use the ViewModel si se necesita pedir datos del principal

    }


    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            val selectedDate = String.format("%02d-%02d-%04d", day, month+1, year)
            fechapagoCB.setText(selectedDate)

            fecha = selectedDate
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }

    private fun create(){

        val post = RetrofitBuilder.builder().create(PagosApi::class.java)

        var Reserva :Int = 0
        if (recordsReser.size > 0) {
            Reserva = recordsReser[cbreserva.selectedItemPosition].Value!!.toInt()
        }

        var Tipopago :Int = 0
        if (records.size > 0) {
            Tipopago = records[cbtipopago.selectedItemPosition].Value!!.toInt()
        }

        val callcreate = post.create(
            Reserva,
           // fechapagoCB.text.toString(),
            Tipopago,
            Importepago.text.toString().toFloat(),
            numerotarjeta.text.toString())
        callcreate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                // Toast.makeText(this@PagosActivity,"failure",Toast.LENGTH_SHORT).show()
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

    private fun update(Id:Int) {

        val post = RetrofitBuilder.builder().create(PagosApi::class.java)

        var IdReserva :Int = 0
        if (recordsReser.size > 0) {
            IdReserva = recordsReser[cbreserva.selectedItemPosition].Value!!.toInt()
        }

        var IdTipopago: Int = 0
        if (records.size > 0) {
            IdTipopago = records[cbtipopago.selectedItemPosition].Value!!.toInt()
        }


        val callUpdate = post.update(
            Id,
            IdReserva,
            fecha,
            IdTipopago,
            Importepago.text.toString().toFloat(),
            numerotarjeta.text.toString())
        callUpdate.enqueue(object : Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                Toast.makeText(this@PagoActivity, "Fallo $Id", Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragmetn:", "" + t.message)
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@PagoActivity, "modificado $Id", Toast.LENGTH_SHORT).show()
                @Suppress("NAME_SHADOWING")
                val response = response.body() as responseModel
                println("test : " + response.Result)

                //val resultIntent = Intent()
                //setResult(Activity.RESULT_OK,resultIntent)
// Changed true
                viewModel.make_Change()
                finish()


            }

        })
    }

    private fun delete(Id: Int){
        val post = RetrofitBuilder.builder().create(PagosApi::class.java)
        val calldelete = post.delete(Id.toInt())
        calldelete.enqueue(object : Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@PagoActivity," borrado ", Toast.LENGTH_SHORT).show()
// Changed true
                viewModel.make_Change()
                finish()
            }
        })

    }
}
