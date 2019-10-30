package com.epalburquerqueiii.aexperience.UI.Horasdia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.epalburquerqueiii.aexperience.R

class ReservaHorasFragment : Fragment() {


    companion object {
        fun newInstance() = ReservaHorasFragment()
    }

    private lateinit var viewModel: HorasdiaViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reservahoras_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    }


