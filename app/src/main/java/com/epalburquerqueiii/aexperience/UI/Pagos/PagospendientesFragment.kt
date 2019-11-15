package com.epalburquerqueiii.aexperience.UI.Pagos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.epalburquerqueiii.aexperience.Data.Adapter.PagospendientesAdapter
import com.epalburquerqueiii.aexperience.Data.Model.Pagopendiente
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.list_fragment.*


class PagospendientesFragment : Fragment() {


    private lateinit var viewModel: PagospendientesViewModel

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: PagospendientesAdapter


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
        bt_new.setVisibility(View.INVISIBLE)
       // var registro = Pagopendiente(0,0,"","",0,"","",0f)
       // intent.putExtra("registro",registro)

    }

    fun setupViewModelAndObserve() {

        viewModel = ViewModelProvider(this).get(PagospendientesViewModel::class.java)
        // TODO: Use the ViewModel
        val registrosObserver = Observer<ArrayList<Pagopendiente>> {

            mAdapter = PagospendientesAdapter(it, activity!!)
            linearLayoutManager = LinearLayoutManager(activity)

            recycleview.layoutManager = linearLayoutManager
            recycleview.setHasFixedSize(true)
            recycleview.adapter = mAdapter


            mAdapter.setOnItemClickListener(object : PagospendientesAdapter.OnItemClickListener {
                override fun onItemClick(registro: Pagopendiente) {
                    //Toast.makeText(activity,""+registro.id,Toast.LENGTH_SHORT).show()
                    var intent = Intent(activity, PagopendienteActivity::class.java)

                    intent.putExtra("registro", registro)
                    startActivity(intent)
                }
            })

        }
        viewModel.getregistrosLiveData().observe(viewLifecycleOwner, registrosObserver)
   }

}
