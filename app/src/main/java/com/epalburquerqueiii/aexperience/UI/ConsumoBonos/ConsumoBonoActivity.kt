package com.epalburquerqueiii.aexperience.UI.ConsumoBonos

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
import com.epalburquerqueiii.aexperience.Data.Model.ConsumoBono
import com.epalburquerqueiii.aexperience.Data.Model.Option
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import com.epalburquerqueiii.aexperience.Data.Network.*
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.UI.Dialog.DatePickerFragment
import com.epalburquerqueiii.aexperience.databinding.ActivityConsumoBonoBinding
import kotlinx.android.synthetic.main.activity_consumo_bono.*
import kotlinx.android.synthetic.main.editupdate_botton.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConsumoBonoActivity : AppCompatActivity() {

    private lateinit var viewModel: ConsumoBonosViewModel

    private var modo:Int? = 0
    private val Crear = 0
    private val Editar = 1

    private val idusuario:Int = 8

    private lateinit var records: ArrayList<Option>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModelAndObserve()


        val binding = DataBindingUtil.setContentView<ActivityConsumoBonoBinding>(this@ConsumoBonoActivity,R.layout.activity_consumo_bono)

        // var addnotemodel = ViewModelProviders.of(this).get(AutorizadosViewModel::class.java)

        val bundle:Bundle? = intent.extras
        val registro = intent.extras.get("registro") as ConsumoBono

//  sin databinding los campo se rellenarían manualmente
/*
        val registro = Autorizado(bundle?.getInt("ID"),
                                bundle?.getString("NombreAutorizado"),
                                bundle?.getString("Nif"))
*/

        modo = bundle?.getInt("modo")


        if (modo == Editar){
            btn_delete.visibility = View.VISIBLE
            binding.setVariable(BR.addconsumobonoviewmodel,registro)
            binding.executePendingBindings()
        }

        btn_save.setOnClickListener {
            if (modo == Crear) {
                create()
            }else{
                update(registro.ID!!)
            }
        }

        btn_delete.setOnClickListener{
            delete(registro.ID!!)
        }

        // Obtiene los Usuarios
        val get = RetrofitBuilder.builder().create(UsuariosApi::class.java)
        val callget = get.GetOptions()

        callget.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel: Int = 0
                records = response.Options!!
                if (size > 0) {
                    val usuarios = ArrayList<String>()
                    var i: Int = 0
                    for (item in records) {
                        usuarios.add(item.DisplayText.toString())
                        if (registro.IDUsuario == item.Value) {
                            sel = i
                        }
                        i++
                    }
                    val adapter = ArrayAdapter(
                        this@ConsumoBonoActivity,
                        android.R.layout.simple_spinner_dropdown_item,
                        usuarios
                    )
                    // Set Adapter to Spinner
                    cbUsuario!!.setAdapter(adapter)
                    cbUsuario.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@ConsumoBonoActivity, "failure", Toast.LENGTH_SHORT).show()
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
                        this@ConsumoBonoActivity,
                        android.R.layout.simple_spinner_dropdown_item,
                        espacios
                    )
                    // Set Adapter to Spinner
                    cbEspacio!!.setAdapter(adapter)
                    cbEspacio.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@ConsumoBonoActivity, "failure", Toast.LENGTH_SHORT).show()
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
                var sel: Int = 0
                records = response.Options!!
                if (size > 0) {
                    val autorizados = ArrayList<String>()
                    var i: Int = 0
                    for (item in records) {
                        autorizados.add(item.DisplayText.toString())
                        if (registro.IDAutorizado == item.Value) {
                            sel = i
                        }
                        i++
                    }
                    val adapter = ArrayAdapter(
                        this@ConsumoBonoActivity,
                        android.R.layout.simple_spinner_dropdown_item,
                        autorizados
                    )
                    // Set Adapter to Spinner
                    cbAutorizado!!.setAdapter(adapter)
                    cbAutorizado.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@ConsumoBonoActivity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Error en Autorizados :", "" + t.message)
            }

        })

        FechaCB.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun setupViewModelAndObserve() {
        viewModel = ViewModelProvider(this).get(ConsumoBonosViewModel::class.java)
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

    fun Int.twoDigits() =
        if (this <= 9) "0$this" else this.toString()

    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            val dayStr = day.twoDigits()
            val monthStr = (month + 1).twoDigits() // +1 because January is zero

            val selectedDate = "$dayStr-$monthStr-$year"
            FechaCB.setText(selectedDate)
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }

    private fun create(){

        val post = RetrofitBuilder.builder().create(ConsumoBonosApi::class.java)
        var Usuarioid :Int = 0
        var Espacioid :Int = 0
        var Autorizadoid :Int = 0
        if (records.size > 0) {
            Usuarioid = records[cbUsuario.selectedItemPosition].Value!!.toInt()
            Espacioid = records[cbEspacio.selectedItemPosition].Value!!.toInt()
            Autorizadoid = records[cbAutorizado.selectedItemPosition].Value!!.toInt()
        }
        val callcreate = post.Create(FechaCB.text.toString(), Sesiones.text as Int, Usuarioid, Espacioid, Autorizadoid)
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

    private fun update(ID:Int){

        val post = RetrofitBuilder.builder().create(ConsumoBonosApi::class.java)
        var Usuarioid :Int = 0
        var Espacioid :Int = 0
        var Autorizadoid :Int = 0
        if (records.size > 0) {
            Usuarioid = records[cbUsuario.selectedItemPosition].Value!!.toInt()
            Espacioid = records[cbEspacio.selectedItemPosition].Value!!.toInt()
            Autorizadoid = records[cbAutorizado.selectedItemPosition].Value!!.toInt()
        }
        val callUpdate = post.Update(ID,FechaCB.text.toString(),Sesiones.text as Int,Usuarioid,Espacioid,Autorizadoid)
        callUpdate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                Toast.makeText(this@ConsumoBonoActivity, "Fallo $ID", Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragmetn:",""+ t.message)
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@ConsumoBonoActivity,"succes $ID", Toast.LENGTH_SHORT).show()
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
        val post = RetrofitBuilder.builder().create(AutorizadosApi::class.java)
        val calldelete = post.Delete(ID.toInt())
        calldelete.enqueue(object : Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@ConsumoBonoActivity," borrado ", Toast.LENGTH_SHORT).show()
                // Changed true
                viewModel.make_Change()
                finish()
            }
        })
    }
}
