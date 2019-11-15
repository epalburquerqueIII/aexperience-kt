package com.epalburquerqueiii.aexperience.UI.Usuarios



import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.epalburquerqueiii.aexperience.Data.Model.AppData
import com.epalburquerqueiii.aexperience.Data.Model.responseModelAuth
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.Data.Network.UsuariosApi
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.UI.Eventos.EventosFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.commit()
    }

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
                    Toast.makeText(null, "Email o contraseña incorrectas", Toast.LENGTH_SHORT).show()
                    Log.i("Error Login:",""+ t.message)
                }

                override fun onResponse(call: Call<responseModelAuth>, response: Response<responseModelAuth>) {
                    //Toast.makeText(null,"succes", Toast.LENGTH_SHORT).show()
                    @Suppress("NAME_SHADOWING")
                    val response = response.body() as responseModelAuth
                    AppData.UserID=response.userid
                    AppData.CsrfRef=response.data2
                    AppData.lastdate!= Calendar.getInstance().time
                    val navView: NavigationView = activity!!.findViewById(com.epalburquerqueiii.aexperience.R.id.nav_view)
                    /*val toolbar: NavigationView = activity!!.findViewById(com.epalburquerqueiii.aexperience.R.id.toolbar)
                    val appbar: NavigationView = activity!!.findViewById(com.epalburquerqueiii.aexperience.R.id.app_bar_main)*/
                    val i : Int = navView.getMenu().size() - 1
                    for (a in 5..i) {
                        navView.getMenu().getItem(a).setVisible(true)
//                        toolbar.setBackgroundColor()
                    }
                    replaceFragment(EventosFragment())
                    if (AppData.CsrfRef.length > 3) {
                        AppData.CsrfRef = AppData.CsrfRef.substring(0,4) + "$" + AppData.CsrfRef.substring(5,AppData.CsrfRef.length-1)
                    }

                }

            })

        }
    }

}
