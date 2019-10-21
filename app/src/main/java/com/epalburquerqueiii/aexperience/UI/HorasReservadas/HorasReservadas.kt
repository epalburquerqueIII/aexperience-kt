package com.epalburquerqueiii.aexperience.UI.HorasReservadas

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.epalburquerqueiii.aexperience.R

class HorasReservadas : Fragment() {

    companion object {
        fun newInstance() = HorasReservadas()
    }

    private lateinit var viewModel: HorasReservadasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.horas_reservadas_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HorasReservadasViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
