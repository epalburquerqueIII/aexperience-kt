package com.epalburquerqueiii.aexperience.UI.MenuRoles

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.epalburquerqueiii.aexperience.Data.Adapter.MenuRolesAdapter
import com.epalburquerqueiii.aexperience.Data.Model.MenuRol
import com.epalburquerqueiii.aexperience.Data.Model.MenuRoles
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.list_fragment.*


class MenuRolesFragment : Fragment() {


    private lateinit var viewModel: MenuRolesViewModel

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: MenuRolesAdapter

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
            var intent = Intent(activity, MenuRolActivity::class.java)
            var registro = MenuRol(0,0,0)
            intent.putExtra("registro",registro)
            intent.putExtra("modo", Crear)
            startActivity(intent) }
    }

    fun setupViewModelAndObserve() {

        viewModel = ViewModelProvider(this).get(MenuRolesViewModel::class.java)
        // TODO: Use the ViewModel
        val registrosObserver = Observer<ArrayList<MenuRol>> {

            mAdapter = MenuRolesAdapter(it, activity!!)
            linearLayoutManager = LinearLayoutManager(activity)

            recycleview.layoutManager = linearLayoutManager
            recycleview.setHasFixedSize(true)
            recycleview.adapter = mAdapter


            mAdapter.setOnItemClickListener(object : MenuRolesAdapter.OnItemClickListener {
                override fun onItemClick(registro: MenuRol) {
                    //Toast.makeText(activity,""+registro.id,Toast.LENGTH_SHORT).show()
                    var intent = Intent(activity, MenuRolActivity::class.java)

                    intent.putExtra("registro", registro)

                    intent.putExtra("modo", Editar)
                    startActivityForResult(intent,1)
                }
            })

        }
        viewModel.getregistrosLiveData().observe(this, registrosObserver)
   }

}
