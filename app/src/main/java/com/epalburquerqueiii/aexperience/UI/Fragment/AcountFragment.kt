package com.epalburquerqueiii.aexperience.UI.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.epalburquerqueiii.aexperience.Data.ViewModel.AcountViewModel
import com.epalburquerqueiii.aexperience.R

class AcountFragment : Fragment() {

    companion object {
        fun newInstance() = AcountFragment()
    }

    private lateinit var viewModel: AcountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.acount_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AcountViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
