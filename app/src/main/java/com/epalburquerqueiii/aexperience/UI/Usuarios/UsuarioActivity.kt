package com.epalburquerqueiii.aexperience.UI.Usuarios

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
import com.epalburquerqueiii.aexperience.Data.Model.Usuario
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.Data.Network.UsuariosApi
import com.epalburquerqueiii.aexperience.Data.Network.UsuariosrolesApi
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.UI.Dialog.DatePickerFragment
import com.epalburquerqueiii.aexperience.databinding.ActivityUsuarioBinding
import kotlinx.android.synthetic.main.activity_usuario.*
import kotlinx.android.synthetic.main.editupdate_botton.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuarioActivity : AppCompatActivity() {

    private lateinit var viewModel: UsuariosViewModel

    private var modo:Int? = 0
    private val Crear = 0
    private val Editar = 1
    var noticias :Int = 5
    var RolUsuario :Int = 5
    // TODO tomar el idusuario que se haga el login en el móvil
    private val idusuario:Int = 8
    private lateinit var records: ArrayList<Option>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModelAndObserve()


        val binding = DataBindingUtil.setContentView<ActivityUsuarioBinding>(this@UsuarioActivity,R.layout.activity_usuario)

        // var addnotemodel = ViewModelProviders.of(this).get(UsuariosViewModel::class.java)

        val bundle:Bundle? = intent.extras
        val registro = intent.extras.get("registro") as Usuario


        modo = bundle?.getInt("modo")


        if (modo == Editar){
            btn_delete.visibility = View.VISIBLE
            binding.setVariable(BR.addusuarioviewmodel,registro)
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

        // Obtiene los Roles de Usuarios
        val get = RetrofitBuilder.builder().create(UsuariosrolesApi::class.java)
        val callget = get.GetOptions()

        callget.enqueue(object : Callback<Options> {
            override fun onFailure(call: Call<Options>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel: Int = 0
                records = response.Options!!
                if (size > 0) {
                    val roles = ArrayList<String>()
                    var i: Int = 0
                    for (item in records) {
                        roles.add(item.DisplayText.toString())
                        if (registro.IdUsuarioRol == item.Value) {
                            sel = i
                        }
                        i++
                    }
                    val adapter = ArrayAdapter(
                        this@UsuarioActivity,
                        android.R.layout.simple_spinner_dropdown_item,
                        roles
                    )
                    // Set Adapter to Spinner
                    Rol!!.setAdapter(adapter)
                    Rol.setSelection(sel)
                }
                fun onFailure(call: Call<Options>, t: Throwable) {
                    Toast.makeText(this@UsuarioActivity, "failure", Toast.LENGTH_SHORT).show()
                    Log.i("Error en Roles :", "" + t.message)
                }
            }
            })


        FechaBaja.setOnClickListener {
            showDatePickerDialog()
        }

        FechaNacimiento.setOnClickListener {
            showDatePickerDialogo()
        }

        OpcionesNoticias.setChecked(registro.Newsletter == 1)

    }

    private fun ver_noticia():Int{
        if (OpcionesNoticias.isChecked()){
            return 1
        } else {
            return 0
        }

    }


    private fun setupViewModelAndObserve() {
        viewModel = ViewModelProvider(this).get(UsuariosViewModel::class.java)
        // TODO: Use the ViewModel si se necesita pedir datos del principal


    }
    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
            FechaBaja.setText(selectedDate)
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }
    private fun showDatePickerDialogo() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
            FechaBaja.setText(selectedDate)
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }

    private fun create(){
        val post = RetrofitBuilder.builder().create(UsuariosApi::class.java)

        if (records.size > 0) {
            RolUsuario = records[Rol.selectedItemPosition].Value!!.toInt()
        }

        val callcreate = post.Create(
            Nombre.text.toString(),
            Nif.text.toString(),
            Email.text.toString(),
            FechaNacimiento.text.toString(),
            RolUsuario,
            Telefono.text.toString(),
            Password.text.toString(),
            SesionesBonos.text.toString().toInt(),
            ver_noticia(),
            FechaBaja.text.toString())
        callcreate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                // Toast.makeText(this@UsuarioActivity,"failure",Toast.LENGTH_SHORT).show()
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

        val post = RetrofitBuilder.builder().create(UsuariosApi::class.java)

        if (records.size > 0) {
            RolUsuario = records[Rol.selectedItemPosition].Value!!.toInt()}

        val callUpdate = post.Update(
                ID,Nombre.text.toString(),
                Nif.text.toString(),
                Email.text.toString(),
                FechaNacimiento.text.toString(),
                RolUsuario,
                Telefono.text.toString(),
                Password.text.toString(),
                SesionesBonos.text.toString().toInt(),
                ver_noticia(),
                FechaBaja.text.toString())
        callUpdate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                Toast.makeText(this@UsuarioActivity, "Fallo $ID", Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragmetn:",""+ t.message)
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@UsuarioActivity,"succes $ID", Toast.LENGTH_SHORT).show()
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
        val post = RetrofitBuilder.builder().create(UsuariosApi::class.java)
        val calldelete = post.Delete(ID.toInt())
        calldelete.enqueue(object : Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@UsuarioActivity," borrado ", Toast.LENGTH_SHORT).show()
// Changed true
                viewModel.make_Change()
                finish()
            }
        })
    }
}
