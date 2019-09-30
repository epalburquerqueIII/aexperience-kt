package com.epalburquerqueiii.aexperience.UI.Tipospagos

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
import com.epalburquerqueiii.aexperience.Data.Network.PersonasApi
import com.epalburquerqueiii.aexperience.Data.Network.ProvinciasApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.Data.Network.TipospagosApi
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.UI.Personas.PersonasViewModel
import com.epalburquerqueiii.aexperience.databinding.ActivityPersonaBinding
import com.epalburquerqueiii.aexperience.databinding.ActivityTipospagoBinding
import kotlinx.android.synthetic.main.activity_persona.*
import kotlinx.android.synthetic.main.activity_tipospago.*
import kotlinx.android.synthetic.main.editupdate_botton.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TipospagoActivity : AppCompatActivity() {

    private lateinit var viewModel: TipospagosViewModel

    private var modo:Int? = 0
    private val Crear = 0
    private val Editar = 1


    private lateinit var records: ArrayList<Option>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModelAndObserve()


        val binding = DataBindingUtil.setContentView<ActivityTipospagoBinding>(this@TipospagoActivity,R.layout.activity_tipospago)
        val bundle:Bundle? = intent.extras
        val registro = intent.extras.get("registro") as Tipospago

        modo = bundle?.getInt("modo")


        if (modo == Editar){
            btn_delete.visibility = View.VISIBLE
            binding.setVariable(BR.addtipospagoviewmodel,registro)
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

    }

    private fun setupViewModelAndObserve() {
        viewModel = ViewModelProvider(this).get(TipospagosViewModel::class.java)

        // TODO: Use the ViewModel si se necesita pedir datos del principal

    }

    private fun create(){

        val post = RetrofitBuilder.builder().create(TipospagosApi::class.java)
        val callcreate = post.Create(NombreTipospago.text.toString())
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

    private fun update(Id:Int){

        val post = RetrofitBuilder.builder().create(TipospagosApi::class.java)
        val callUpdate = post.Update(Id,NombreTipospago.text.toString())
        callUpdate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                Toast.makeText(this@TipospagoActivity, "Fallo $Id", Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragmetn:",""+ t.message)
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@TipospagoActivity,"succes $Id", Toast.LENGTH_SHORT).show()
                @Suppress("NAME_SHADOWING")
                val response = response.body() as responseModel
                println("test : "+response.Result)


                viewModel.make_Change()
                finish()


            }

        })

    }

    private fun delete(Id: Int){
        val post = RetrofitBuilder.builder().create(TipospagosApi::class.java)
        val calldelete = post.Delete(Id.toInt())
        calldelete.enqueue(object : Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@TipospagoActivity," borrado ", Toast.LENGTH_SHORT).show()

                viewModel.make_Change()
                finish()
            }
        })

    }


}

