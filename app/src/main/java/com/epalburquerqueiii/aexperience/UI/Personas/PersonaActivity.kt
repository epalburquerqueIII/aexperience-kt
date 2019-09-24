package com.epalburquerqueiii.aexperience.UI

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.epalburquerqueiii.aexperience.BR
import com.epalburquerqueiii.aexperience.Data.Model.Persona
import com.epalburquerqueiii.aexperience.Data.Model.Option
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Network.ProvinciasApi
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import com.epalburquerqueiii.aexperience.Data.Network.PersonasApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.UI.Personas.PersonasViewModel
import com.epalburquerqueiii.aexperience.databinding.ActivityPersonaBinding
import kotlinx.android.synthetic.main.activity_persona.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PersonaActivity : AppCompatActivity() {

    private lateinit var viewModel: PersonasViewModel

    private var modo:Int? = 0
    private val Crear = 0
    private val Editar = 1

    private lateinit var records: ArrayList<Option>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModelAndObserve()


        val binding = DataBindingUtil.setContentView<ActivityPersonaBinding>(this@PersonaActivity,R.layout.activity_persona)

       // var addnotemodel = ViewModelProviders.of(this).get(PersonasViewModel::class.java)

        val bundle:Bundle? = intent.extras
        val registro = intent.extras.get("registro") as Persona

/*
        val registro = Persona(bundle?.getInt("ID"),
                                bundle?.getString("Nombre"),
                                bundle?.getString("Direccion"),
                                bundle?.getString("Poblacion"),
                                bundle?.getInt("Provinciaid"),
                                bundle?.getString("Telefono"),
                                bundle?.getString("Email"))
*/

        modo = bundle?.getInt("modo")


        if (modo == Editar){
            btn_delete.visibility = View.VISIBLE
            binding.setVariable(BR.addregistroviewmodel,registro)
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
// Obtiene las provincias
        val get = RetrofitBuilder.builder().create(ProvinciasApi::class.java)
        val callget = get.GetOptions()

        callget.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel :Int = 0
                records = response.Options!!
                if (size > 0) {
                    val provincias = ArrayList<String>()
                    var i:Int = 0
                    for ( item in records){
                        provincias.add(item.DisplayText.toString())
                        if (registro.Provinciaid == item.Value) {
                            sel = i }
                        i++
                    }
                    val adapter = ArrayAdapter(this@PersonaActivity, android.R.layout.simple_spinner_dropdown_item, provincias)
                    // Set Adapter to Spinner
                    cbprovincia!!.setAdapter(adapter)
                    cbprovincia.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@PersonaActivity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Error en Provincias :", "" + t.message)
            }

        })

    }

    private fun setupViewModelAndObserve() {
        viewModel = ViewModelProvider(this).get(PersonasViewModel::class.java)
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

    private fun create(){

        val post = RetrofitBuilder.builder().create(PersonasApi::class.java)
        var Provinciaid :Int = 0
        if (records.size > 0) {
             Provinciaid = records[cbprovincia.selectedItemPosition].Value!!.toInt()
        }
        val callcreate = post.Create(Nombre.text.toString(),Direccion.text.toString(),Poblacion.text.toString(),Provinciaid,Telefono.text.toString(),Email.text.toString())
        callcreate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
               // Toast.makeText(this@PersonaActivity,"failure",Toast.LENGTH_SHORT).show()
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

        val post = RetrofitBuilder.builder().create(PersonasApi::class.java)
        var Provinciaid :Int = 0
        if (records.size > 0) {
            Provinciaid = records[cbprovincia.selectedItemPosition].Value!!.toInt()
        }

        val callUpdate = post.Update(ID,Nombre.text.toString(),Direccion.text.toString(),Poblacion.text.toString(),Provinciaid,Telefono.text.toString(),Email.text.toString())
        callUpdate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                Toast.makeText(this@PersonaActivity, "Fallo $ID",Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragmetn:",""+ t.message)
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@PersonaActivity,"succes $ID",Toast.LENGTH_SHORT).show()
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
        val post = RetrofitBuilder.builder().create(PersonasApi::class.java)
        val calldelete = post.Delete(ID.toInt())
        calldelete.enqueue(object : Callback<responseModel>{
            override fun onFailure(call: Call<responseModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@PersonaActivity," borrado ",Toast.LENGTH_SHORT).show()
// Changed true
                viewModel.make_Change()
                finish()
            }
        })

    }


}
