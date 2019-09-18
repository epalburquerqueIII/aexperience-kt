package com.epalburquerqueiii.aexperience.UI.Usuarios

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.epalburquerqueiii.aexperience.R


class usuarios_fragment : Fragment() {

    companion object {
        fun newInstance() = usuarios_fragment()
    }

    private lateinit var viewModel: UsuariosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.usuarios_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UsuariosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
