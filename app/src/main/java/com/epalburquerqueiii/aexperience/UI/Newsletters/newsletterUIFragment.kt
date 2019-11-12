package com.epalburquerqueiii.aexperience.UI.Newsletters


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.epalburquerqueiii.aexperience.Data.Model.*
import com.epalburquerqueiii.aexperience.Data.Network.MenuParentApi
import com.epalburquerqueiii.aexperience.Data.Network.ReservasApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.reservahoras_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var records: ArrayList<Option>

/**
 * A simple [Fragment] subclass.
 */
class newsletterUIFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_newsletter_ui, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val get = RetrofitBuilder.builder().create(MenuParentApi::class.java)
        val callget = get.GetOptions(AppData.CsrfRef)

        callget.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel :Int = 0
                records = response.Options!!
                if (size > 0) {
                    val Tiponoticia = ArrayList<String>()
                    var i:Int = 0
                    for ( item in records){
//                        menuParent.add(item.DisplayText.toString())
//                        if (registro.Idtiponoticias == item.Value) {
//                            sel = i }
//                        i++
                    }
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText( null,"failure", Toast.LENGTH_SHORT).show()
                Log.i("Error en ParentId :", "" + t.message)
            }

        })
    }

/*
    btn_guardar.setOnClickListener {
        val post = RetrofitBuilder.builder().create(ReservasApi::class.java)
// recorrer horasSwitch y si esta marcado llama a la funcion
        var IdEspacios: Int = 1
        IdEspacios = IdEspacioh.selectedItemPosition


        val callcreate = post.Reservahora(
            IdEspacios,
            Fecha24.text.toString(),
            if (horasSwitch[0].isChecked) {
                horasSwitch[0].text.toString()
            } else {
                "0"
            },
            if (horasSwitch[1].isChecked) {
                horasSwitch[1].text.toString()
            } else {
                "0"
            },
            if (horasSwitch[2].isChecked) {
                horasSwitch[2].text.toString()
            } else {
                "0"
            },
            if (horasSwitch[3].isChecked) {
                horasSwitch[3].text.toString()
            } else {
                "0"
            },
            if (horasSwitch[4].isChecked) {
                horasSwitch[4].text.toString()
            } else {
                "0"
            },
            if (horasSwitch[5].isChecked) {
                horasSwitch[5].text.toString()
            } else {
                "0"
            },
            if (horasSwitch[6].isChecked) {
                horasSwitch[6].text.toString()
            } else {
                "0"
            },
            if (horasSwitch[7].isChecked) {
                horasSwitch[7].text.toString()
            } else {
                "0"
            }
        )


        callcreate.enqueue(object : Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                // Toast.makeText(this@PagosActivity,"failure",Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragment:", "" + t.message)

            }

            override fun onResponse(
                call: Call<responseModel>,
                response: Response<responseModel>
            ) {
                //Toast.makeText(activity,"succes",Toast.LENGTH_SHORT).show()
                @Suppress("NAME_SHADOWING")
                val response = response.body() as responseModel
                println("test : " + response.Error)

            }

        })
    }

}*/




}
