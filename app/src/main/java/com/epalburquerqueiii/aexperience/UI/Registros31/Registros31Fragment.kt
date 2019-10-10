package com.epalburquerqueiii.aexperience.UI.Registros31

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.epalburquerqueiii.aexperience.R


class Registros31Fragment : Fragment() {

        private lateinit var viewModel: Registros31ViewModel

        override fun onCreateView(

            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_registro31, container, false)

        }
    }
