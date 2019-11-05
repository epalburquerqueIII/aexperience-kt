package com.epalburquerqueiii.aexperience.UI.Usuarios



import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.epalburquerqueiii.aexperience.Data.Model.responseModelAuth
import com.epalburquerqueiii.aexperience.Data.Network.UsuariosApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder

import com.epalburquerqueiii.aexperience.R

import com.google.android.material.navigation.NavigationView

import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    fun String.SH256(): String {
        val md = MessageDigest.getInstance("SHA-256")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bt_login.setOnClickListener {
            val post = RetrofitBuilder.builder().create(UsuariosApi::class.java)
            val Password: String = LoginPassword.text.toString()

            val cryptoPassword = Password.SH256()
            val callLogin = post.login(
                LoginEmail.text.toString(),
                cryptoPassword)//+"$!#"
            callLogin.enqueue(object: Callback<responseModelAuth> {
                override fun onFailure(call: Call<responseModelAuth>, t: Throwable) {
                   // Toast.makeText(null, "Fallo", Toast.LENGTH_SHORT).show()
                    Log.i("Error Login:",""+ t.message)
                }

                override fun onResponse(call: Call<responseModelAuth>, response: Response<responseModelAuth>) {
                    //Toast.makeText(null,"succes", Toast.LENGTH_SHORT).show()
                    @Suppress("NAME_SHADOWING")
                    val response = response.body() as responseModelAuth

                    val navView: NavigationView = activity!!.findViewById(R.id.nav_view)
                    val i : Int = navView.getMenu().size()
                    navView.getMenu().getItem(4).setVisible(true);

//  Cambiamos el menu de la app a privado
//                    val navView: NavigationView = it.findViewById(R.id.nav_view_private)
// Soluciona los problemas de Click
//                    navView.bringToFront()

                }

            })

        }
    }

}
