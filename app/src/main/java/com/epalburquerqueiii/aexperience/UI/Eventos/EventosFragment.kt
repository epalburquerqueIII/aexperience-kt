package com.epalburquerqueiii.aexperience.UI.Eventos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.epalburquerqueiii.aexperience.Data.Adapter.EventosAdapter
import com.epalburquerqueiii.aexperience.Data.Model.Evento
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.list_fragment.*


class EventosFragment : Fragment() {

    private lateinit var viewModel: EventosViewModel

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: EventosAdapter


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
        bt_new.setVisibility(View.GONE)



        bt_new.setOnClickListener{
            var intent = Intent(activity, DetailsActivity::class.java)
            var registro = Evento(0,"","","","","","","","","","","")
            intent.putExtra("registro",registro)
            startActivity(intent) }
    }


    fun setupViewModelAndObserve() {

        viewModel = ViewModelProvider(this).get(EventosViewModel::class.java)
        // TODO: Use the ViewModel
        val registrosObserver = Observer<ArrayList<Evento>> {

            mAdapter = EventosAdapter(it, activity!!)
            linearLayoutManager = LinearLayoutManager(activity)

            recycleview.layoutManager = linearLayoutManager
            recycleview.setHasFixedSize(true)
            recycleview.adapter = mAdapter

            mAdapter.setOnItemClickListener(object : EventosAdapter.OnItemClickListener {
                override fun onItemClick(registro: Evento) {
                    //Toast.makeText(activity,""+registro.id,Toast.LENGTH_SHORT).show()
                    var intent = Intent(activity, DetailsActivity::class.java)

                    intent.putExtra("registro", registro)

                    startActivityForResult(intent,1)
                }
            })

        }
        viewModel.getregistrosLiveData().observe(viewLifecycleOwner, registrosObserver)
   }

}
