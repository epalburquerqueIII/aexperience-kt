package com.epalburquerqueiii.aexperience.UI.Newsletters


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import com.epalburquerqueiii.aexperience.Data.Model.*
import com.epalburquerqueiii.aexperience.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.epalburquerqueiii.aexperience.Data.Network.NewslettersApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.UI.Eventos.EventosFragment
import kotlinx.android.synthetic.main.fragment_newsletter_ui.*
import kotlinx.android.synthetic.main.fragment_newsletter_ui.btn_guardar
import kotlinx.android.synthetic.main.reservahoras_fragment.*

/**
 * A simple [Fragment] subclass.
 */
class newsletterUIFragment : Fragment() {


    private lateinit var records: ArrayList<Option>
    private lateinit var nwch : ArrayList<CheckBox>

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.commit()
    }

    companion object {
        fun newInstance() = NewslettersFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_newsletter_ui, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        nwch = ArrayList()

        nwch.add(nwch1 as CheckBox)
        nwch.add(nwch2 as CheckBox)
        nwch.add(nwch3 as CheckBox)
        nwch.add(nwch4 as CheckBox)
        nwch.add(nwch5 as CheckBox)
        nwch.add(nwch6 as CheckBox)
        nwch.add(nwch7 as CheckBox)
        nwch.add(nwch8 as CheckBox)
        nwch.add(nwch9 as CheckBox)
        nwch.add(nwch10 as CheckBox)

        val get = RetrofitBuilder.builder().create(NewslettersApi::class.java)
        val callget = get.GetOptions(AppData.CsrfRef)

        callget.enqueue(object : Callback<Options> {
            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Options
                val size = response.Options!!.size
                var sel :Int = 0
                records = response.Options!!
                if (size > 0) {
                    var i:Int = 0
                    for ( item in records.indices) {
                        nwch[item].text = records[item].DisplayText
                        nwch[item].setTag(records[item].Value)
                    }
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                Toast.makeText( getActivity(),"failure", Toast.LENGTH_SHORT).show()
                Log.i("Error NW Noticias :", "" + t.message)
            }

        })

        btn_guardar.setOnClickListener {
            val post = RetrofitBuilder.builder().create(NewslettersApi::class.java)
        // recorrer tiponoticias y si esta marcado llama a la funcion

            val callcreate = post.SaveNewsletterUser(
                Email.text.toString(),
                if (nwch[0].isChecked) {
                    1
                } else {
                    0
                },
                if (nwch[1].isChecked) {
                    2
                } else {
                    0
                },
                if (nwch[2].isChecked) {
                    3
                } else {
                    0
                },
                if (nwch[3].isChecked) {
                    4
                } else {
                    0
                },
                if (nwch[4].isChecked) {
                    5
                } else {
                    0
                },
                if (nwch[5].isChecked) {
                    6
                } else {
                    0
                },
                if (nwch[6].isChecked) {
                    7
                } else {
                    0
                },
                if (nwch[7].isChecked) {
                    8
                } else {
                    0
                },
                if (nwch[8].isChecked) {
                    9
                } else {
                    0
                },
                if (nwch[9].isChecked) {
                    10
                } else {
                    0
                }

            )


            callcreate.enqueue(object: Callback<responseModel> {
                override fun onFailure(call: Call<responseModel>, t: Throwable) {
                    // Toast.makeText(this@MenuActivity,"failure",Toast.LENGTH_SHORT).show()
                    Log.i("dasboardfragment:", "" + t.message)
                    replaceFragment(EventosFragment())
                }

                override fun onResponse(
                    call: Call<responseModel>,
                    response: Response<responseModel>
                ) {
                    //Toast.makeText(activity,"succes",Toast.LENGTH_SHORT).show()
                    @Suppress("NAME_SHADOWING")
                    val response = response.body() as responseModel
                    println("test : " + response.Error)
                }
            })
        }

    }

}
