package com.epalburquerqueiii.aexperience.UI.ConsumoBonos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.epalburquerqueiii.aexperience.Data.Adapter.ConsumoBonosAdapter
import com.epalburquerqueiii.aexperience.Data.Model.ConsumoBono
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.list_fragment.*


class ConsumoBonosFragment : Fragment() {


    private lateinit var viewModel: ConsumoBonosViewModel

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: ConsumoBonosAdapter

    private val Crear = 0
    private val Editar = 1

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
            var intent = Intent(activity, ConsumoBonoActivity::class.java)
            var registro = ConsumoBono(0,"",0,0, "", 0,0)
            intent.putExtra("registro",registro)
            intent.putExtra("modo", Crear)
            startActivity(intent) }
    }

    fun setupViewModelAndObserve() {

        viewModel = ViewModelProvider(this).get(ConsumoBonosViewModel::class.java)
        // TODO: Use the ViewModel
        val registrosObserver = Observer<ArrayList<ConsumoBono>> {

            mAdapter = ConsumoBonosAdapter(it, activity!!)
            linearLayoutManager = LinearLayoutManager(activity)

            recycleview.layoutManager = linearLayoutManager
            recycleview.setHasFixedSize(true)
            recycleview.adapter = mAdapter


            mAdapter.setOnItemClickListener(object : ConsumoBonosAdapter.OnItemClickListener {
                override fun onItemClick(registro: ConsumoBono) {
                    //Toast.makeText(activity,""+registro.id,Toast.LENGTH_SHORT).show()
                    var intent = Intent(activity, ConsumoBonoActivity::class.java)

                    intent.putExtra("registro", registro)

                    intent.putExtra("modo", Editar)
                    startActivityForResult(intent,1)
                }
            })

        }
        viewModel.getregistrosLiveData().observe(viewLifecycleOwner, registrosObserver)
   }

}
