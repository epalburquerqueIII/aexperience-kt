package com.epalburquerqueiii.aexperience.UI.Autorizados

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.epalburquerqueiii.aexperience.BR
import com.epalburquerqueiii.aexperience.Data.Model.AppData
import com.epalburquerqueiii.aexperience.Data.Model.Autorizado
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import com.epalburquerqueiii.aexperience.Data.Network.AutorizadosApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.databinding.ActivityAutorizadoBinding
import kotlinx.android.synthetic.main.activity_autorizado.*
import kotlinx.android.synthetic.main.editupdate_botton.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AutorizadoActivity : AppCompatActivity() {

    private lateinit var viewModel: AutorizadosViewModel

    private var modo:Int? = 0
    private val Crear = 0
    private val Editar = 1
// TODO tomar el idusuario que se haga el login en el móvil
    private val idusuario:Int = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModelAndObserve()


        val binding = DataBindingUtil.setContentView<ActivityAutorizadoBinding>(this@AutorizadoActivity,R.layout.activity_autorizado)

        // var addnotemodel = ViewModelProviders.of(this).get(ReservasViewModel::class.java)

        val bundle:Bundle? = intent.extras
        val registro = intent.extras.get("registro") as Autorizado

//  sin databinding los campo se rellenarían manualmente
/*
        val registro = Autorizado(bundle?.getInt("ID"),
                                bundle?.getString("NombreAutorizado"),
                                bundle?.getString("Nif"))
*/

        modo = bundle?.getInt("modo")


        if (modo == Editar){
            btn_delete.visibility = View.VISIBLE
            binding.setVariable(BR.addautorizadoviewmodel,registro)
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
    }

    private fun setupViewModelAndObserve() {
        viewModel = ViewModelProvider(this).get(AutorizadosViewModel::class.java)
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

        val post = RetrofitBuilder.builder().create(AutorizadosApi::class.java)
        val callcreate = post.Create(AppData.CsrfRef,idusuario,NombreAutorizado.text.toString(),nif.text.toString())
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

        val post = RetrofitBuilder.builder().create(AutorizadosApi::class.java)

        val callUpdate = post.Update(AppData.CsrfRef,ID,idusuario,NombreAutorizado.text.toString(),nif.text.toString())
        callUpdate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                Toast.makeText(this@AutorizadoActivity, "Fallo $ID", Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragmetn:",""+ t.message)
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@AutorizadoActivity,"succes $ID", Toast.LENGTH_SHORT).show()
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
        val calldelete = post.Delete(AppData.CsrfRef,ID.toInt())
        calldelete.enqueue(object : Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@AutorizadoActivity," borrado ", Toast.LENGTH_SHORT).show()
// Changed true
                viewModel.make_Change()
                finish()
            }
        })

    }


}
