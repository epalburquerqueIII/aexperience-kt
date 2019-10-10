package com.epalburquerqueiii.aexperience.UI.Newsletters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.epalburquerqueiii.aexperience.BR
import com.epalburquerqueiii.aexperience.Data.Model.Newsletter
import com.epalburquerqueiii.aexperience.Data.Model.Option
import com.epalburquerqueiii.aexperience.Data.Model.Options

import com.epalburquerqueiii.aexperience.Data.Model.responseModel
import com.epalburquerqueiii.aexperience.Data.Network.NewslettersApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.Data.Network.TiponoticiasApi
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.databinding.ActivityNewsletterBinding
import kotlinx.android.synthetic.main.activity_newsletter.*
import kotlinx.android.synthetic.main.editupdate_botton.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsletterActivity : AppCompatActivity() {

    private lateinit var viewModel: NewslettersViewModel

    private var modo:Int? = 0
    private val Crear = 0
    private val Editar = 1
    // TODO tomar el idusuario que se haga el login en el móvil

    private lateinit var records: ArrayList<Option>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModelAndObserve()


        val binding = DataBindingUtil.setContentView<ActivityNewsletterBinding>(this@NewsletterActivity, R.layout.activity_newsletter)

        // var addnotemodel = ViewModelProviders.of(this).get(MenusViewModel::class.java)

        val bundle:Bundle? = intent.extras
        val registro = intent.extras.get("registro") as Newsletter


        modo = bundle?.getInt("modo")


        if (modo == Editar){
            btn_delete.visibility = View.VISIBLE
            binding.setVariable(BR.addnewsletterviewmodel,registro)
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
        val get = RetrofitBuilder.builder().create(TiponoticiasApi::class.java)
        val callget = get.GetOptions()

        callget.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel :Int = 0
                records = response.Options!!
                if (size > 0) {
                    val tiponoticias = ArrayList<String>()
                    var i:Int = 0
                    for ( item in records){
                        tiponoticias.add(item.DisplayText.toString())
                        if (registro.Idtiponoticias == item.Value) {
                            sel = i }
                        i++
                    }
                    val adapter = ArrayAdapter(this@NewsletterActivity, android.R.layout.simple_spinner_dropdown_item, tiponoticias)
                    // Set Adapter to Spinner
                    cbidtiponoticias!!.setAdapter(adapter)
                    cbidtiponoticias.setSelection(sel)
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText(this@NewsletterActivity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Error Idtiponoticias:", "" + t.message)
            }

        })
    }

    private fun setupViewModelAndObserve() {
        viewModel = ViewModelProvider(this).get(NewslettersViewModel::class.java)
        // TODO: Use the ViewModel si se necesita pedir datos del principal

    }

    private fun create(){
        val post = RetrofitBuilder.builder().create(NewslettersApi::class.java)
        var Idtiponoticias :Int = 0
        if (records.size > 0) {
            Idtiponoticias = records[cbidtiponoticias.selectedItemPosition].Value!!.toInt()
        }

        val callcreate = post.Create(Email.text.toString(),Idtiponoticias)
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

    private fun update(Id:Int){

        val post = RetrofitBuilder.builder().create(NewslettersApi::class.java)
        var Idtiponoticias :Int = 0
        if (records.size > 0) {
            Idtiponoticias = records[cbidtiponoticias.selectedItemPosition].Value!!.toInt()
        }
        val callUpdate = post.Update(Id,Email.text.toString(),Idtiponoticias)
        callUpdate.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                Toast.makeText(this@NewsletterActivity, "Fallo $Id", Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragmetn:",""+ t.message)
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@NewsletterActivity,"succes $Id", Toast.LENGTH_SHORT).show()
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

    private fun delete(Id: Int){
        val post = RetrofitBuilder.builder().create(NewslettersApi::class.java)
        val calldelete = post.Delete(Id.toInt())
        calldelete.enqueue(object : Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@NewsletterActivity," borrado ", Toast.LENGTH_SHORT).show()
// Changed true
                viewModel.make_Change()
                finish()
            }
        })

    }
}

