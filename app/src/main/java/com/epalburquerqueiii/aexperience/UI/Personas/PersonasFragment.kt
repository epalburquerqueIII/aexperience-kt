package com.epalburquerqueiii.aexperience.UI.Personas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.epalburquerqueiii.aexperience.Data.Adapter.PersonasAdapter
import com.epalburquerqueiii.aexperience.Data.Model.Persona
import com.epalburquerqueiii.aexperience.Data.Model.Personas
import com.epalburquerqueiii.aexperience.Data.Network.PersonasApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.UI.AddPersonaActivity
import kotlinx.android.synthetic.main.list_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PersonasFragment : Fragment() {

    companion object {
        fun newInstance() = PersonasFragment()
    }

    private lateinit var viewModel: PersonasViewModel

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: PersonasAdapter

    private var newsList: ArrayList<Persona>? = null


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PersonasViewModel::class.java)
        // TODO: Use the ViewModel

        bt_new.setOnClickListener{
            var intent = Intent(activity, AddPersonaActivity::class.java)
            intent.putExtra("ID", 0)
            intent.putExtra("Nombre", "")
            intent.putExtra("Direccion", "")
            intent.putExtra("Poblacion", "")
            intent.putExtra("Provinciaid", 0)
            intent.putExtra("Telefono", "")
            intent.putExtra("Email", "")
            intent.putExtra("modo", 0)
            startActivity(intent) }

        val get =
            RetrofitBuilder.builder(activity!!.applicationContext).create(PersonasApi::class.java)
        val callget = get.Get()
        callget.enqueue(object : Callback<Personas> {
            override fun onFailure(call: Call<Personas>, t: Throwable) {
                Toast.makeText(activity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("Personas Fragment:", "" + t.message)
            }

            override fun onResponse(call: Call<Personas>, response: Response<Personas>) {
                val response = response.body() as Personas
                val size = response.Records!!.size
                val records = response.Records!!

                if (records.isEmpty()) {

                } else {
                    mAdapter = PersonasAdapter(records, activity!!)
                    linearLayoutManager = LinearLayoutManager(activity)

                    recycleview.layoutManager = linearLayoutManager
                    recycleview.setHasFixedSize(true)
                    recycleview.adapter = mAdapter


                    mAdapter.setOnItemClickListener(object : PersonasAdapter.OnItemClickListener {
                        override fun onItemClick(registro: Persona) {
                            //Toast.makeText(activity,""+registro.id,Toast.LENGTH_SHORT).show()
                            var intent = Intent(activity, AddPersonaActivity::class.java)
                            intent.putExtra("ID", registro.ID?.toInt())
                            intent.putExtra("Nombre", registro.Nombre?.toString())
                            intent.putExtra("Direccion", registro.Direccion?.toString())
                            intent.putExtra("Poblacion", registro.Poblacion?.toString())
                            intent.putExtra("Provinciaid", registro.Provinciaid)
                            intent.putExtra("Telefono", registro.Telefono?.toString())
                            intent.putExtra("Email", registro.Email?.toString())
                            intent.putExtra("modo", 1)
                            startActivity(intent)
                        }
                    })

                }
//                for (i in 0 until size){
//                    val TipoGasto = response.records!!.get(i).TipoGasto
//                    System.out.println("test : "+TipoGasto)
////                    Toast.makeText(activity,""+TipoGasto,Toast.LENGTH_SHORT)
//                }

            }

        })


    }

}
