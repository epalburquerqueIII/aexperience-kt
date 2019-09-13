package com.epalburquerqueiii.aexperience.UI

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.epalburquerqueiii.aexperience.BR
import com.epalburquerqueiii.aexperience.Data.Model.Persona
import com.epalburquerqueiii.aexperience.Data.Model.Option
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Network.ProvinciasApi
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import com.epalburquerqueiii.aexperience.Data.Network.PersonasApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.databinding.ActivityPersonaBinding
import kotlinx.android.synthetic.main.activity_persona.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddPersonaActivity : AppCompatActivity() {


    //private lateinit var binding:ActivityAddNoteBinding
    private lateinit var TipoGasto:String
    private lateinit var add_or_edit:String

    private var modo:Int? = 0
    private val Crear = 0
    private val Editar = 1

    private lateinit var records: ArrayList<Option>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_add_Movimiento)


        val binding = DataBindingUtil.setContentView<ActivityPersonaBinding>(this@AddPersonaActivity,R.layout.activity_persona)

       // var addnotemodel = ViewModelProviders.of(this).get(PersonaViewModel::class.java)

        val bundle:Bundle? = intent.extras
        val registro = Persona(bundle?.getInt("ID"),
                                bundle?.getString("Nombre"),
                                bundle?.getString("Direccion"),
                                bundle?.getString("Poblacion"),
                                bundle?.getInt("Provinciaid"),
                                bundle?.getString("Telefono"),
                                bundle?.getString("Email"))

        modo = bundle?.getInt("modo")


        if (modo == Editar){
            btn_delete.visibility = View.VISIBLE
            binding.setVariable(BR.addregistroviewmodel,registro)
            binding.executePendingBindings()
        }

        btn_next.setOnClickListener {
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
        val get = RetrofitBuilder.builder(this.applicationContext).create(ProvinciasApi::class.java)
        val callget = get.GetOptions()

        callget.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
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
                    val adapter = ArrayAdapter(this@AddPersonaActivity, android.R.layout.simple_spinner_dropdown_item, provincias)
                    // Set Adapter to Spinner
                    cbprovincia!!.setAdapter(adapter)
                    cbprovincia.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@AddPersonaActivity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Error en Provincias :", "" + t.message)
            }
        })

    }

    private fun create(){

        val get = RetrofitBuilder.builder(this.applicationContext).create(PersonasApi::class.java)
        var Provinciaid :Int = 0
        if (records.size > 0) {
             Provinciaid = records[cbprovincia.selectedItemPosition].Value!!.toInt()
        }
        val callcreate = get.Create(Nombre.text.toString(),Direccion.text.toString(),Poblacion.text.toString(),Provinciaid,Telefono.text.toString(),Email.text.toString())
        callcreate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
               // Toast.makeText(this@AddPersonaActivity,"failure",Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragment:",""+t.message)
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                //Toast.makeText(activity,"succes",Toast.LENGTH_SHORT).show()
                val response = response.body() as responseModel
                println("test : "+response.Error)

                //val resultIntent = Intent()
                //setResult(Activity.RESULT_OK,resultIntent)
                finish()


            }

        })

    }

    private fun update(ID:Int){

        val get = RetrofitBuilder.builder(this.applicationContext).create(PersonasApi::class.java)
        var Provinciaid :Int = 0
        if (records.size > 0) {
            Provinciaid = records[cbprovincia.selectedItemPosition].Value!!.toInt()
        }

        val callUpdate = get.Update(ID,Nombre.text.toString(),Direccion.text.toString(),Poblacion.text.toString(),Provinciaid,Telefono.text.toString(),Email.text.toString())
        callUpdate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                Toast.makeText(this@AddPersonaActivity, "Fallo $ID",Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragmetn:",""+ t.message)
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@AddPersonaActivity,"succes $ID",Toast.LENGTH_SHORT).show()
                val response = response.body() as responseModel
                println("test : "+response.Result)

                //val resultIntent = Intent()
                //setResult(Activity.RESULT_OK,resultIntent)
                finish()


            }

        })

    }

    private fun delete(ID: Int){
        val get = RetrofitBuilder.builder(this.applicationContext).create(PersonasApi::class.java)
        val calldelete = get.Delete(ID.toString())
        calldelete.enqueue(object : Callback<responseModel>{
            override fun onFailure(call: Call<responseModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@AddPersonaActivity," borrado ",Toast.LENGTH_SHORT).show()
                finish()
            }
        })

    }
}
