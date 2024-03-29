package com.epalburquerqueiii.aexperience.UI.Usuariosroles

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.epalburquerqueiii.aexperience.BR
import com.epalburquerqueiii.aexperience.Data.Model.AppData
import com.epalburquerqueiii.aexperience.Data.Model.Option
import com.epalburquerqueiii.aexperience.Data.Model.Usuariorol
import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.Data.Network.UsuariosrolesApi
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.databinding.ActivityUsuariorolBinding
import kotlinx.android.synthetic.main.activity_usuariorol.*
import kotlinx.android.synthetic.main.editupdate_botton.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuariorolActivity : AppCompatActivity() {

    private lateinit var viewModel: UsuariosrolesViewModel

    private var modo:Int? = 0
    private val Crear = 0
    private val Editar = 1

    private lateinit var records:ArrayList<Option>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModelAndObserve()


        val binding = DataBindingUtil.setContentView<ActivityUsuariorolBinding>(this@UsuariorolActivity,
            R.layout.activity_usuariorol)

        val bundle: Bundle? = intent.extras
        val registro = intent.extras.get("registro") as Usuariorol


        modo = bundle?.getInt("modo")


        if (modo == Editar){
            btn_delete.visibility = View.VISIBLE
            binding.setVariable(BR.addusuariorolviewmodel,registro)
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
        viewModel = ViewModelProvider(this).get(UsuariosrolesViewModel::class.java)
        // TODO: Use the ViewModel si se necesita pedir datos del principal
    }

    private fun create(){

        val post = RetrofitBuilder.builder().create(UsuariosrolesApi::class.java)
        val callcreate = post.Create(AppData.CsrfRef,
                                    NombreRol.text.toString())
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

        val post = RetrofitBuilder.builder().create(UsuariosrolesApi::class.java)

        val callUpdate = post.Update(AppData.CsrfRef,
                                    ID,NombreRol.text.toString())
        callUpdate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                Toast.makeText(this@UsuariorolActivity, "Fallo $ID", Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragmetn:",""+ t.message)
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@UsuariorolActivity,"succes $ID", Toast.LENGTH_SHORT).show()
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
        val post = RetrofitBuilder.builder().create(UsuariosrolesApi::class.java)
        val calldelete = post.Delete(AppData.CsrfRef,
                                    ID.toInt())
        calldelete.enqueue(object : Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@UsuariorolActivity," borrado ", Toast.LENGTH_SHORT).show()
// Changed true
                viewModel.make_Change()
                finish()
            }
        })

    }


}
