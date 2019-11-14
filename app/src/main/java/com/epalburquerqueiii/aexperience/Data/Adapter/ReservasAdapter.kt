package com.epalburquerqueiii.aexperience.Data.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epalburquerqueiii.aexperience.Data.Model.Reserva
import com.epalburquerqueiii.aexperience.Data.Util.Comun
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.item_horario.view.*
import kotlinx.android.synthetic.main.item_reserva.view.*



class ReservasAdapter(private val reservas: ArrayList<Reserva>, context: Context) : RecyclerView.Adapter<ReservasAdapter.ReservaViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_reserva, parent, false)
        return ReservaViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return reservas.size
    }

    override fun onBindViewHolder(holder: ReservaViewHolder, position: Int) {
       val itemnotes = reservas[position]
        holder.bindView(itemnotes)

    }

    inner class ReservaViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var Reserva:Reserva? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(reservas[position])
                }
            }
        }

// asigna el dato del adapter al control de la vista
        fun bindView(Reserva: Reserva){ 0
            this.Reserva = Reserva
            var MostrarFecha : String? = this.Reserva?.Fecha
            itemView.fecha_item.text = Comun.StringYMDtoDMY(MostrarFecha)
            if (this.Reserva?.Hora != null) {
                itemView.HoraReserva_item.setText(this.Reserva?.Hora.toString())
            }else{
                itemView.HoraReserva_item.text = "No hay horas registradas"
            }
            itemView.usuario_item.text = this.Reserva?.UsuarioNombre
            itemView.espacio_item.text = this.Reserva?.EspacioNombre
            itemView.autorizado_item.text = this.Reserva?.AutorizadoNombre
        }

    }

    interface OnItemClickListener {
        fun onItemClick(Reserva: Reserva)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}