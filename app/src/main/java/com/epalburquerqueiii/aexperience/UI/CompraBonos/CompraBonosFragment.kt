package com.epalburquerqueiii.aexperience.UI.CompraBonos

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.epalburquerqueiii.aexperience.Data.Model.*
import com.epalburquerqueiii.aexperience.Data.Network.*
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.UI.Eventos.EventosFragment
import kotlinx.android.synthetic.main.activity_consumo_bono.*
import kotlinx.android.synthetic.main.activity_horario.*
import kotlinx.android.synthetic.main.activity_tiposevento.*
import kotlinx.android.synthetic.main.compra_bonos_fragment.*
import kotlinx.android.synthetic.main.compra_bonos_fragment.view.*
import kotlinx.android.synthetic.main.fragment_registro.*
import kotlinx.android.synthetic.main.item_reserva.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CompraBonosFragment : Fragment() {
    private lateinit var datos: ArrayList<Bono>

    private lateinit var RB : ArrayList<RadioButton>

    private lateinit var vTEntradas : ArrayList<TextView>

    private lateinit var vTPrecios : ArrayList<TextView>

    private lateinit var records: ArrayList<Option>

    companion object {
        fun newInstance() = CompraBonosFragment()
    }

    private lateinit var viewModel: CompraBonosViewModel

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.commit()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(R.layout.compra_bonos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        records = ArrayList<Option>()


        RB = ArrayList<RadioButton>()
        vTEntradas = ArrayList<TextView>()
        vTPrecios = ArrayList<TextView>()

        // 0 Bono 12 Sesiones
        RB.add(rB0 as RadioButton)
        // 1 Bono 25 Sesiones
        RB.add(rB1 as RadioButton)
        // 2 Bono 70 Sesiones
        RB.add(rB2 as RadioButton)
        // 3 Entrada 1 día
        RB.add(rB3 as RadioButton)
        // 4 Entrada 1 día menores
        RB.add(rB4 as RadioButton)

        vTEntradas.add(tVEntradas0)
        vTEntradas.add(tVEntradas1)
        vTEntradas.add(tVEntradas2)
        vTEntradas.add(tVEntradas3)
        vTEntradas.add(tVEntradas4)

        vTPrecios.add(tVPrecios0)
        vTPrecios.add(tVPrecios1)
        vTPrecios.add(tVPrecios2)
        vTPrecios.add(tVPrecios3)
        vTPrecios.add(tVPrecios4)


        // Obtiene los datos para los rb
        val get = RetrofitBuilder.builder().create(BonosApi::class.java)
        val callget = get.List(AppData.CsrfRef)

        callget.enqueue(object : Callback<Bonos> {
            override fun onResponse(call: Call<Bonos>, response: Response<Bonos>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Bonos
                val size = response.Records!!.size
                datos = response.Records!!
                if (size > 0) {
                    for (item in datos.indices) {
                        vTPrecios[item].setText(datos[item].Precio.toString())
                        vTEntradas[item].setText(datos[item].Sesiones.toString())
                    }
                }
            }
            override fun onFailure(call: Call<Bonos>, t: Throwable) {
                Log.i("Error Compra de Bonos :", "" + t.message)
            }
        })


        btncomprar.setOnClickListener {

            var idUsuario = 18
            var tipodepago: Int = 0

            if (rBTransferencia.isChecked) {
                tipodepago = 2
            } else {
                if (rBEfectivo.isChecked) {
                    tipodepago = 1
                }
            }
             for (indice in RB.indices) {
                 if (RB[indice].isChecked) {

                     val post = RetrofitBuilder.builder().create(ReservasApi::class.java)
                     val callcreate = post.ComprarBono(AppData.CsrfRef,
                         idUsuario,
                         vTEntradas[indice].text.toString().toInt(),
                         tipodepago
                     )


                     callcreate.enqueue(object : Callback<responseModel> {
                         override fun onFailure(
                             call: Call<responseModel>,
                             t: Throwable
                         ) {
                             // Toast.makeText(this@PagosActivity,"failure",Toast.LENGTH_SHORT).show()
                             Log.i("dasboardfragment:", "" + t.message)
                         }

                         override fun onResponse(
                             call: Call<responseModel>,
                             response: Response<responseModel>
                         ) {
                             Toast.makeText(activity,"La compra se hizo correctamente",Toast.LENGTH_SHORT).show()
                             @Suppress("NAME_SHADOWING")
                             val response = response.body() as responseModel
                             println("test : " + response.Error)
                             replaceFragment(EventosFragment())
                         }
                     })
                     break
                 }
             }
         }
    }
}



