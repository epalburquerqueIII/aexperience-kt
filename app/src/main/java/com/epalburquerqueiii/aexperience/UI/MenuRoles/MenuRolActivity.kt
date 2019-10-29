package com.epalburquerqueiii.aexperience.UI.MenuRoles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.epalburquerqueiii.aexperience.BR
import com.epalburquerqueiii.aexperience.Data.Model.*
import com.epalburquerqueiii.aexperience.Data.Network.MenuRolesApi
import com.epalburquerqueiii.aexperience.Data.Network.MenusApi

import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.Data.Network.UsuariosrolesApi
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.UI.MenuRoles.MenuRolesViewModel
import com.epalburquerqueiii.aexperience.databinding.ActivityMenurolBinding

import kotlinx.android.synthetic.main.activity_menurol.*

import kotlinx.android.synthetic.main.editupdate_botton.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuRolActivity : AppCompatActivity() {

    private lateinit var viewModel: MenuRolesViewModel

    private var modo:Int? = 0
    private val Crear = 0
    private val Editar = 1
    // TODO tomar el idusuario que se haga el login en el móvil
    //private val idusuario:Int = 8

    private lateinit var recordsusuariosroles: ArrayList<Option>
    private lateinit var recordsmenus: ArrayList<Option>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModelAndObserve()

        val binding = DataBindingUtil.setContentView<ActivityMenurolBinding>(this@MenuRolActivity,R.layout.activity_menurol)

        // var addnotemodel = ViewModelProviders.of(this).get(MenuRolesViewModel::class.java)

        val bundle:Bundle? = intent.extras
        val registro = intent.extras.get("registro") as MenuRol

//  sin databinding los campo se rellenarían manualmente
/*
        val registro = Menu(bundle?.getInt("ID"),
                                bundle?.getString("NombreMenu"),
                                bundle?.getString("Nif"))
*/

        modo = bundle?.getInt("modo")


        if (modo == Editar){
            btn_delete.visibility = View.VISIBLE
            binding.setVariable(BR.addmenurolviewmodel,registro)
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

        //----------------------------------------------------------------------------------
        //combo idmenus
        val getidmenus = RetrofitBuilder.builder().create(MenusApi::class.java)
        val callgetidmenus = getidmenus.GetOptions()

        callgetidmenus.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel :Int = 0
                recordsmenus = response.Options!!
                if (size > 0) {
                    val menusArray = ArrayList<String>()
                    var i:Int = 0
                    for ( item in recordsmenus){
                        menusArray.add(item.DisplayText.toString())
                        if (registro.IDMenu == item.Value) {
                            sel = i }
                        i++
                    }
                    val adapter = ArrayAdapter(this@MenuRolActivity, android.R.layout.simple_spinner_dropdown_item, menusArray)
                    // Set Adapter to Spinner
                    cbidMenu!!.setAdapter(adapter)
                    cbidMenu.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@MenuRolActivity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Error en MenuRol id :", "" + t.message)
            }
        })

        //----------------------------------------------------------------------------------------
        //combo idusuarios roles
        val getidusuariosroles = RetrofitBuilder.builder().create(UsuariosrolesApi::class.java)
        val callgetidusuariosroles = getidusuariosroles.GetOptions()

        callgetidusuariosroles.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel :Int = 0
                recordsusuariosroles = response.Options!!
                if (size > 0) {
                    val usuariosrolesArray = ArrayList<String>()
                    var i:Int = 0
                    for ( item in recordsusuariosroles){
                        usuariosrolesArray.add(item.DisplayText.toString())
                        if (registro.IDUsuarioRoles == item.Value) {
                            sel = i }
                        i++
                    }
                    val adapter = ArrayAdapter(this@MenuRolActivity, android.R.layout.simple_spinner_dropdown_item, usuariosrolesArray)
                    // Set Adapter to Spinner
                    cbidUsuarioRoles!!.setAdapter(adapter)
                    cbidUsuarioRoles.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@MenuRolActivity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Error en MenuRol id :", "" + t.message)
            }
        })
    }


private fun setupViewModelAndObserve() {
        viewModel = ViewModelProvider(this).get(MenuRolesViewModel::class.java)
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
        val post = RetrofitBuilder.builder().create(MenuRolesApi::class.java)

        var IDMenu :Int = 1
        if (recordsmenus.size > 0) {
            IDMenu = recordsmenus[cbidMenu.selectedItemPosition].Value!!.toInt()
        }

        var IDUsuarioRoles :Int = 1
        if (recordsusuariosroles.size > 0) {
            IDUsuarioRoles = recordsusuariosroles[cbidUsuarioRoles.selectedItemPosition].Value!!.toInt()
        }

        val callcreate = post.Create(
            IDMenu.toString().toInt(),
            IDUsuarioRoles)
        callcreate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                // Toast.makeText(this@MenuActivity,"failure",Toast.LENGTH_SHORT).show()
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

        val post = RetrofitBuilder.builder().create(MenuRolesApi::class.java)
        var IDMenu :Int = 0
        var IDUsuarioRoles :Int = 0

        if (recordsmenus.size > 0) {
            IDMenu = recordsmenus[cbidMenu.selectedItemPosition].Value!!.toInt()
        }

        if (recordsusuariosroles.size > 0) {
            IDUsuarioRoles = recordsusuariosroles[cbidUsuarioRoles.selectedItemPosition].Value!!.toInt()
        }
        val callUpdate = post.Update(ID,IDMenu, IDUsuarioRoles.toString().toInt())
        callUpdate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                Toast.makeText(this@MenuRolActivity, "Fallo $ID", Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragmetn:",""+ t.message)
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@MenuRolActivity,"succes $ID", Toast.LENGTH_SHORT).show()
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
        val post = RetrofitBuilder.builder().create(MenuRolesApi::class.java)
        val calldelete = post.Delete(ID.toInt())
        calldelete.enqueue(object : Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@MenuRolActivity," borrado ", Toast.LENGTH_SHORT).show()
// Changed true
                viewModel.make_Change()
                finish()
            }
        })
    }
}
