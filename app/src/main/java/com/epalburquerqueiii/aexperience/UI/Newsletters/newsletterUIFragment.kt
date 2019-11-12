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
import kotlinx.android.synthetic.main.fragment_newsletter_ui.*

/**
 * A simple [Fragment] subclass.
 */
class newsletterUIFragment : Fragment() {


    private lateinit var records: ArrayList<Option>
    private lateinit var nwch : ArrayList<CheckBox>

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
        val callget = get.GetOptions()

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
                        nwch[item + 1].text = records[item].DisplayText
                        nwch[item + 1].setTag(records[item].Value)
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
                if (nwch[1].isChecked) {
                    nwch[1].text.toString().toInt()
                } else {
                    0
                },
                if (nwch[2].isChecked) {
                    nwch[2].text.toString().toInt()
                } else {
                    0
                },
                if (nwch[3].isChecked) {
                    nwch[3].text.toString().toInt()
                } else {
                    0
                },
                if (nwch[4].isChecked) {
                    nwch[4].text.toString().toInt()
                } else {
                    0
                },
                if (nwch[5].isChecked) {
                    nwch[5].text.toString().toInt()
                } else {
                    0
                },
                if (nwch[6].isChecked) {
                    nwch[6].text.toString().toInt()
                } else {
                    0
                },
                if (nwch[7].isChecked) {
                    nwch[7].text.toString().toInt()
                } else {
                    0
                },
                if (nwch[8].isChecked) {
                    nwch[8].text.toString().toInt()
                } else {
                    0
                },
                if (nwch[9].isChecked) {
                    nwch[9].text.toString().toInt()
                } else {
                    0
                },
                if (nwch[10].isChecked) {
                    nwch[10].text.toString().toInt()
                } else {
                    0
                }

            )


            callcreate.enqueue(object : Callback<responseModel> {
                override fun onFailure(call: Call<responseModel>, t: Throwable) {
                    // Toast.makeText(this@PagosActivity,"failure",Toast.LENGTH_SHORT).show()
                    Log.i("dasboardfragment:", "" + t.message)

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
