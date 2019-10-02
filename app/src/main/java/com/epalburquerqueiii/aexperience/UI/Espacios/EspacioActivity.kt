package com.epalburquerqueiii.aexperience.UI.Espacios

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
//import androidx.databinding.library.baseAdapters.BR
import com.epalburquerqueiii.aexperience.BR
import androidx.lifecycle.ViewModelProvider
import com.epalburquerqueiii.aexperience.Data.Model.Espacio
import com.epalburquerqueiii.aexperience.Data.Model.Option
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import com.epalburquerqueiii.aexperience.Data.Network.EspaciosApi
import com.epalburquerqueiii.aexperience.Data.Network.EventosApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.databinding.ActivityEspaciosBinding

import kotlinx.android.synthetic.main.activity_espacios.*


import kotlinx.android.synthetic.main.editupdate_botton.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EspacioActivity : AppCompatActivity() {

    private lateinit var viewModel: EspaciosViewModel

    private var modo: Int? = 0
    private val Crear = 0
    private val Editar = 1
    private lateinit var records: ArrayList<Option>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModelAndObserve()


        val binding = DataBindingUtil.setContentView<ActivityEspaciosBinding>(
            this@EspacioActivity,
            R.layout.activity_espacios
        )

        // var addnotemodel = ViewModelProviders.of(this).get(EspaciosViewModel::class.java)

        val bundle: Bundle? = intent.extras
        val registro = intent.extras.get("registro") as Espacio

//  sin databinding los campo se rellenarían manualmente
/*
        val registro = Espacio(bundle?.getInt("ID"),
                                bundle?.getString("NombreEspacio"),
                                bundle?.getString("Nif"))
*/

        modo = bundle?.getInt("modo")


        if (modo == Editar) {
            btn_delete.visibility = View.VISIBLE
            binding.setVariable(BR.addespacioviewmodel,registro)
            binding.executePendingBindings()

        }



        btn_save.setOnClickListener {
            if (modo == Crear) {
                create()
            } else {
                update(registro.ID!!)
            }
        }

        btn_delete.setOnClickListener {
            delete(registro.ID!!)
        }

        val get = RetrofitBuilder.builder().create(EventosApi::class.java)
        val callget = get.GetOptions()

        callget.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel :Int = 0
                records = response.Options!!
                if (size > 0) {
                    val Eventosarray = ArrayList<String>()
                    var i:Int = 0
                    for ( item in records){
                        Eventosarray.add(item.DisplayText.toString())
                        if (registro.IDTiposevento == item.Value) {
                            sel = i }
                        i++
                    }
                    val adapter = ArrayAdapter(this@EspacioActivity, android.R.layout.simple_spinner_dropdown_item, Eventosarray)
                    // Set Adapter to Spinner
                    IDTipoevento!!.setAdapter(adapter)
                    IDTipoevento.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@EspacioActivity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Error en Espacios :", "" + t.message)
            }

        })

    }

    private fun setupViewModelAndObserve() {
        viewModel = ViewModelProvider(this).get(EspaciosViewModel::class.java)
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

    private fun create() {

        val post = RetrofitBuilder.builder().create(EspaciosApi::class.java)
        var Evento:Int = 0
        if (records.size > 0) {
            Evento = records[IDTipoevento.selectedItemPosition].Value!!.toInt()
        }
        val callcreate = post.Create(
            Descripcion.text.toString(),
            Estado.id,
            Modo.id,
            Precio.text.toString().toInt(),
            Evento,
            Fecha.text.toString(),
            Aforo.text.toString().toInt(),
            NumeroReservaslimite.text.toString().toInt()

        )
        callcreate.enqueue(object : Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                // Toast.makeText(this@EspacioActivity,"failure",Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragment:", "" + t.message)
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                //Toast.makeText(activity,"succes",Toast.LENGTH_SHORT).show()
                @Suppress("NAME_SHADOWING")
                val response = response.body() as responseModel
                println("test : " + response.Error)
// Changed true
                viewModel.make_Change()
                finish()


            }

        })

    }

    private fun update(ID: Int) {

        val post = RetrofitBuilder.builder().create(EspaciosApi::class.java)
        var Evento:Int = 0
        if (records.size > 0) {
            Evento = records[IDTipoevento.selectedItemPosition].Value!!.toInt()
        }
        val callUpdate = post.Update(
            ID,
            Descripcion.text.toString(),
            Estado.id,
            Modo.id,
            Precio.toString().toInt(),
            Evento,
            Fecha.text.toString(),
            Aforo.text.toString().toInt(),
            NumeroReservaslimite.text.toString().toInt()
        )
        callUpdate.enqueue(object : Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                Toast.makeText(this@EspacioActivity, "Fallo $ID", Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragmetn:", "" + t.message)
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@EspacioActivity, "succes $ID", Toast.LENGTH_SHORT).show()
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

    private fun delete(ID: Int) {
        val post = RetrofitBuilder.builder().create(EspaciosApi::class.java)
        val calldelete = post.Delete(ID.toInt())
        calldelete.enqueue(object : Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@EspacioActivity, " borrado ", Toast.LENGTH_SHORT).show()
// Changed true
                viewModel.make_Change()
                finish()
            }
        })

    }
}