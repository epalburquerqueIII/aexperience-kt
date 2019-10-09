package com.epalburquerqueiii.aexperience.UI.Registros31

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.epalburquerqueiii.aexperience.Data.Model.Usuario
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.UI.Usuarios.UsuarioActivity
import kotlinx.android.synthetic.main.list_fragment.*

class Registros31Fragment : Fragment() {

        private lateinit var viewModel: Registros31ViewModel

        override fun onCreateView(

            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_registro31, container, false)

        }

        fun setupViewModelAndObserve() {

            viewModel = ViewModelProvider(this).get(Registros31ViewModel::class.java)
            // TODO: Use the ViewModel
            val registrosObserver = Observer<Usuario> {
            }
            viewModel.getregistrosLiveData().observe(this, registrosObserver)
        }

    }
