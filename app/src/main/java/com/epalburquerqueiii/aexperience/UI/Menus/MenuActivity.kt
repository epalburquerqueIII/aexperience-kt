package com.epalburquerqueiii.aexperience.UI.Menus

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.epalburquerqueiii.aexperience.BR
import com.epalburquerqueiii.aexperience.Data.Model.Menu
import com.epalburquerqueiii.aexperience.Data.Model.Option
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import com.epalburquerqueiii.aexperience.Data.Network.MenuParentApi
import com.epalburquerqueiii.aexperience.Data.Network.MenusApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.databinding.ActivityMenuBinding
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.editupdate_botton.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuActivity : AppCompatActivity() {

    private lateinit var viewModel: MenusViewModel

    private var modo:Int? = 0
    private val Crear = 0
    private val Editar = 1
    // TODO tomar el idusuario que se haga el login en el móvil
    private val idusuario:Int = 8

    private lateinit var records: ArrayList<Option>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModelAndObserve()


        val binding = DataBindingUtil.setContentView<ActivityMenuBinding>(this@MenuActivity,R.layout.activity_menu)

        // var addnotemodel = ViewModelProviders.of(this).get(MenusViewModel::class.java)

        val bundle:Bundle? = intent.extras
        val registro = intent.extras.get("registro") as Menu

//  sin databinding los campo se rellenarían manualmente
/*
        val registro = Menu(bundle?.getInt("ID"),
                                bundle?.getString("NombreMenu"),
                                bundle?.getString("Nif"))
*/

        modo = bundle?.getInt("modo")


        if (modo == Editar){
            btn_delete.visibility = View.VISIBLE
            binding.setVariable(BR.addmenuviewmodel,registro)
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
        val get = RetrofitBuilder.builder().create(MenuParentApi::class.java)
        val callget = get.GetOptions()

        callget.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel :Int = 0
                records = response.Options!!
                if (size > 0) {
                    val menuParent = ArrayList<String>()
                    var i:Int = 0
                    for ( item in records){
                        menuParent.add(item.DisplayText.toString())
                        if (registro.ParentId == item.Value) {
                            sel = i }
                        i++
                    }
                    val adapter = ArrayAdapter(this@MenuActivity, android.R.layout.simple_spinner_dropdown_item, menuParent)
                    // Set Adapter to Spinner
                    cbparentid!!.adapter = adapter
                    cbparentid.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@MenuActivity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Error en ParentId :", "" + t.message)
            }

        })
    }

    private fun setupViewModelAndObserve() {
        viewModel = ViewModelProvider(this).get(MenusViewModel::class.java)
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
        val post = RetrofitBuilder.builder().create(MenusApi::class.java)
        var ParentId :Int = 0
        if (records.size > 0) {
            ParentId = records[cbparentid.selectedItemPosition].Value!!.toInt()
        }

        val callcreate = post.Create(ParentId,Orden.text.toString().toInt(),Titulo.text.toString(),Icono.text.toString(),Url.text.toString(),HandleFunc.text.toString())
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

        val post = RetrofitBuilder.builder().create(MenusApi::class.java)
        var ParentId :Int = 0
        if (records.size > 0) {
            ParentId = records[cbparentid.selectedItemPosition].Value!!.toInt()
        }
        val callUpdate = post.Update(ID,ParentId,Orden.text.toString().toInt(),Titulo.text.toString(),Icono.text.toString(),Url.text.toString(),HandleFunc.text.toString())
        callUpdate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                Toast.makeText(this@MenuActivity, "Fallo $ID", Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragmetn:",""+ t.message)
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@MenuActivity,"succes $ID", Toast.LENGTH_SHORT).show()
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
        val post = RetrofitBuilder.builder().create(MenusApi::class.java)
        val calldelete = post.Delete(ID.toInt())
        calldelete.enqueue(object : Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@MenuActivity," borrado ", Toast.LENGTH_SHORT).show()
// Changed true
                viewModel.make_Change()
                finish()
            }
        })

    }
}
