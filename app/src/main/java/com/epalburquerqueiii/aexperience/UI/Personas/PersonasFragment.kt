package com.epalburquerqueiii.aexperience.UI.Personas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.epalburquerqueiii.aexperience.Data.Adapter.PersonasAdapter
import com.epalburquerqueiii.aexperience.Data.Model.Persona
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.UI.PersonaActivity
import kotlinx.android.synthetic.main.list_fragment.*


class PersonasFragment : Fragment() {


    private lateinit var viewModel: PersonasViewModel

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: PersonasAdapter

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModelAndObserve()
        viewModel.getRegistros()
        bt_new.setOnClickListener{
            var intent = Intent(activity, PersonaActivity::class.java)
            intent.putExtra("ID", 0)
            intent.putExtra("Nombre", "")
            intent.putExtra("Direccion", "")
            intent.putExtra("Poblacion", "")
            intent.putExtra("Provinciaid", 0)
            intent.putExtra("Telefono", "")
            intent.putExtra("Email", "")
            intent.putExtra("modo", 0)
            startActivity(intent) }
    }

    fun setupViewModelAndObserve() {

        viewModel = ViewModelProvider(this).get(PersonasViewModel::class.java)
        // TODO: Use the ViewModel
        val registrosObserver = Observer<ArrayList<Persona>> {

            mAdapter = PersonasAdapter(it, activity!!)
            linearLayoutManager = LinearLayoutManager(activity)

            recycleview.layoutManager = linearLayoutManager
            recycleview.setHasFixedSize(true)
            recycleview.adapter = mAdapter


            mAdapter.setOnItemClickListener(object : PersonasAdapter.OnItemClickListener {
                override fun onItemClick(registro: Persona) {
                    //Toast.makeText(activity,""+registro.id,Toast.LENGTH_SHORT).show()
                    var intent = Intent(activity, PersonaActivity::class.java)
                    intent.putExtra("ID", registro.ID?.toInt())
                    intent.putExtra("Nombre", registro.Nombre?.toString())
                    intent.putExtra("Direccion", registro.Direccion?.toString())
                    intent.putExtra("Poblacion", registro.Poblacion?.toString())
                    intent.putExtra("Provinciaid", registro.Provinciaid)
                    intent.putExtra("Telefono", registro.Telefono?.toString())
                    intent.putExtra("Email", registro.Email?.toString())
                    intent.putExtra("modo", 1)
                    startActivityForResult(intent,1)
                }
            })

        }
        viewModel.getregistrosLiveData().observe(this, registrosObserver)
   }

}
