package com.epalburquerqueiii.aexperience.UI.Personas

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epalburquerqueiii.aexperience.Data.Model.Persona

class PersonaViewModel : ViewModel() {

    private val listPersonas = MutableLiveData<ArrayList<Persona>>()

    fun setListPersonas(Personas:ArrayList<Persona>) {
        listPersonas.value = Personas
    }



}