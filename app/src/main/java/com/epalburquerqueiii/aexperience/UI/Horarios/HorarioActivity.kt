package com.epalburquerqueiii.aexperience.UI.Horarios

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
import com.epalburquerqueiii.aexperience.Data.Model.Horario
import com.epalburquerqueiii.aexperience.Data.Model.Option
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import com.epalburquerqueiii.aexperience.Data.Network.EspaciosApi
import com.epalburquerqueiii.aexperience.Data.Network.HorariosApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.Data.Util.Comun
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.UI.Dialog.DatePickerFragment
import com.epalburquerqueiii.aexperience.databinding.ActivityHorarioBinding

import kotlinx.android.synthetic.main.activity_horario.*

import kotlinx.android.synthetic.main.editupdate_botton.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlin.collections.ArrayList


class HorarioActivity : AppCompatActivity() {

    private lateinit var viewModel: HorariosViewModel

    private var modo:Int? = 0
    private val Crear = 0
    private val Editar = 1
    private lateinit var records: ArrayList<Option>
    private var fecini:String = ""
    private var fecfin:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModelAndObserve()


        val binding = DataBindingUtil.setContentView<ActivityHorarioBinding>(this@HorarioActivity,R.layout.activity_horario)

        // var addnotemodel = ViewModelProviders.of(this).get(HorariosViewModel::class.java)

        val bundle:Bundle? = intent.extras
        val registro = intent.extras.get("registro") as Horario

//  sin databinding los campo se rellenarían manualmente
/*
        val registro = Horario(bundle?.getInt("ID"),
                                bundle?.getString("NombreHorario"),
                                bundle?.getString("Nif"))
*/

        modo = bundle?.getInt("modo")




        if (modo == Editar){
            btn_delete.visibility = View.VISIBLE
            registro.Fechainicio = Comun.StringYMDtoDMY(registro.Fechainicio)
            registro.Fechafinal = Comun.StringYMDtoDMY(registro.Fechafinal)
            binding.setVariable(BR.addhorarioviewmodel,registro)
            binding.executePendingBindings()
        }

        btn_save.setOnClickListener {
            if (fecini == "" || fecfin == "" ){
                Toast.makeText(this@HorarioActivity, "Error en las fechas", Toast.LENGTH_SHORT).show()
            }
            else
            if (Hora.text.toString() == "") {
                Toast.makeText(this@HorarioActivity, "Error en la hora", Toast.LENGTH_SHORT).show()
            }
            else
            if (modo == Crear) {
                create()
            } else {
                update(registro.ID!!)
            }
        }

        btn_delete.setOnClickListener{
            delete(registro.ID!!)
        }

        //Obtener los espacios
        val get = RetrofitBuilder.builder().create(EspaciosApi::class.java)
        val callget = get.GetOptions()

        callget.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel: Int = 0
                records = response.Options!!
                if (size > 0) {
                    val espacios = ArrayList<String>()
                    var i: Int = 0
                    for (item in records) {
                        espacios.add(item.DisplayText.toString())
                        if (registro.IDEspacio == item.Value) {
                            sel = i
                        }
                        i++
                    }
                    val adapter = ArrayAdapter(
                        this@HorarioActivity,
                        android.R.layout.simple_spinner_dropdown_item,
                        espacios
                    )
                    // Set Adapter to Spinner
                    IdEspacio!!.adapter = adapter
                    IdEspacio.setSelection(sel)
                }
            }
            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@HorarioActivity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Error en Horarios :", "" + t.message)
            }
        })

        Fechainicio.setOnClickListener {
            showDatePickerDialogfecini()
        }

        Fechafinal.setOnClickListener {
            showDatePickerDialogfecfin()
        }
    }

    private fun setupViewModelAndObserve() {
        viewModel = ViewModelProvider(this).get(HorariosViewModel::class.java)
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

    //para la fecha de inicio
    private fun showDatePickerDialogfecini() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            val selectedDate = "%02d-%02d-%04d".format(day,month+1,year)
            Fechainicio.setText(selectedDate)
            fecini = selectedDate
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }

    //para la fecha final
    private fun showDatePickerDialogfecfin() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            val selectedDate = "%02d-%02d-%04d".format(day,month+1,year)
            fecfin = selectedDate
            Fechafinal.setText(selectedDate)
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }



    private fun create() {

        val post = RetrofitBuilder.builder().create(HorariosApi::class.java)
        var Espacioid: Int = 0
        if (records.size > 0) {
            Espacioid = records[IdEspacio.selectedItemPosition].Value!!.toInt()
        }

        val callcreate = post.Create(
            Espacioid,
            Descripcion.text.toString(),
            fecini,
            fecfin,
            Hora.text.toString().toInt())


        callcreate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                // Toast.makeText(this@HorarioActivity,"failure",Toast.LENGTH_SHORT).show()
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

    private fun update(ID:Int){

        val post = RetrofitBuilder.builder().create(HorariosApi::class.java)
        var Espacioid :Int=0
        if (records.size > 0) {
            Espacioid = records[IdEspacio.selectedItemPosition].Value!!.toInt()
        }

        val callUpdate = post.Update(
            ID,
            Espacioid,
            Descripcion.text.toString(),
            fecini,
            fecfin,
            Hora.text.toString().toInt())

        callUpdate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                Toast.makeText(this@HorarioActivity, "Fallo $ID", Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragmetn:",""+ t.message)
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@HorarioActivity,"succes $ID", Toast.LENGTH_SHORT).show()
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
        val post = RetrofitBuilder.builder().create(HorariosApi::class.java)
        val calldelete = post.Delete(ID.toInt())
        calldelete.enqueue(object : Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@HorarioActivity," borrado ", Toast.LENGTH_SHORT).show()
// Changed true
                viewModel.make_Change()
                finish()
            }
        })

    }


}
