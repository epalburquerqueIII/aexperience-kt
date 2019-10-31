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
import com.epalburquerqueiii.aexperience.Data.Network.BonosApi
import com.epalburquerqueiii.aexperience.Data.Network.EspaciosApi
import com.epalburquerqueiii.aexperience.Data.Network.ReservasApi
import com.epalburquerqueiii.aexperience.Data.Network.RetrofitBuilder
import com.epalburquerqueiii.aexperience.R
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

    private lateinit var st : ArrayList<String>

    private lateinit var vTEntradas : ArrayList<TextView>

    private lateinit var vTPrecios : ArrayList<TextView>

    companion object {
        fun newInstance() = CompraBonosFragment()
    }

    private lateinit var viewModel: CompraBonosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(R.layout.compra_bonos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        st = ArrayList<String>()

        st.add("deja el movil")
        st.add("deja el movil")
        st.add("deja el movil")
        st.add("deja el movil")
        st.add("deja el movil")
        st.add("deja el movil")

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



        //TODO: asignar valores a rb
        // TODO: añadir listener al boton de comprar. Pasar desde ahi los datos con sql


        // Obtiene los datos para los rb
        val get = RetrofitBuilder.builder().create(BonosApi::class.java)
        val callget = get.Get()

        callget.enqueue(object : Callback<Bonos> {
            override fun onResponse(call: Call<Bonos>, response: Response<Bonos>) {
                @Suppress("NAME_SHADOWING")
                val response = response.body() as Bonos
                val size = response.Records!!.size
                datos = response.Records!!
                if (size > 0) {
                    for (item in datos.indices) {
                        vTEntradas[item].setText(datos[item].Sesiones.toString())
                        vTPrecios[item].setText(datos[item].Precio.toString())
                    }

                }
            }

            override fun onFailure(call: Call<Bonos>, t: Throwable) {
                Log.i("Error Compra de Bonos :", "" + t.message)
            }

        })

         fun transferencia_sesiones() {


             var i: Int = 9

             for (item in RB) {
                 if (RB[i].isChecked) {
                vTEntradas[i]
                vTPrecios[i]

                 }
             }
            val post = RetrofitBuilder.builder().create(ReservasApi::class.java)
            val callcreate = post.ReservarBono(

                IDUsuario_item.text.toString().toInt(),
                Sesiones.text.toString().toInt(),
                Importe.text.toString().toFloat()

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
                    //Toast.makeText(activity,"succes",Toast.LENGTH_SHORT).show()
                    @Suppress("NAME_SHADOWING")
                    val response = response.body() as responseModel
                    println("test : " + response.Error)
                }

            })
         }


        btncomprar.setOnClickListener {
            transferencia_sesiones()
        }




    }

}



