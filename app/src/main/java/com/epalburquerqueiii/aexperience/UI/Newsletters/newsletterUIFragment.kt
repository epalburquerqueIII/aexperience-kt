package com.epalburquerqueiii.aexperience.UI.Newsletters


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.epalburquerqueiii.aexperience.Data.Model.Option
import com.epalburquerqueiii.aexperience.Data.Model.Options
import com.epalburquerqueiii.aexperience.Data.Network.MenuParentApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.activity_menu.*
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
//                        menuParent.add(item.DisplayText.toString())
//                        if (registro.ParentId == item.Value) {
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





}
