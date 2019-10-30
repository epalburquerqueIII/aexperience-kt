package com.epalburquerqueiii.aexperience.Data.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epalburquerqueiii.aexperience.Data.Model.Pago
import com.epalburquerqueiii.aexperience.Data.Util.Comun
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.item_pago.view.*


class PagosAdapter(private val pagos: ArrayList<Pago>, context: Context) : RecyclerView.Adapter<PagosAdapter.PagoViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagoViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_pago, parent, false)
        return PagoViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return pagos.size
    }

    override fun onBindViewHolder(holder: PagoViewHolder, position: Int) {
       val itemnotes = pagos[position]
        holder.bindView(itemnotes)

    }

    inner class PagoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var Pago:Pago? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(pagos[position])
                }
            }
        }

// asigna el dato del adapter al control de la vista
        fun bindView(Pago: Pago){
            this.Pago = Pago
            var MostrarFechaReserva : String? = this.Pago?.FechaReserva
            itemView.Reservapago_item.text = Comun.StringYMDtoDMY(MostrarFechaReserva)
            var MostrarFechaPago : String? = this.Pago?.FechaPago
            itemView.Fechapagopago_item.text = Comun.StringYMDtoDMY(MostrarFechaPago)
            itemView.Importe_item.text = this.Pago?.Importe.toString()
            itemView.Tipopago_item.text = this.Pago?.TipoPago.toString()
//            itemView.Email.text = this.Persona?.Email
        }

    }

    interface OnItemClickListener {
        fun onItemClick(Pago: Pago)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}