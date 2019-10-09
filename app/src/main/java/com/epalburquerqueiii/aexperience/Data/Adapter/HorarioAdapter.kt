package com.epalburquerqueiii.aexperience.Data.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epalburquerqueiii.aexperience.Data.Model.Horario
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.item_horario.view.*


class HorarioAdapter(private val horarios: ArrayList<Horario>, context: Context) : RecyclerView.Adapter<HorarioAdapter.HorarioViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorarioViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_horario, parent, false)
        return HorarioViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return horarios.size
    }

    override fun onBindViewHolder(holder: HorarioViewHolder, position: Int) {
       val itemnotes = horarios[position]
        holder.bindView(itemnotes)

    }

    inner class HorarioViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var Horario:Horario? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(horarios[position])
                }
            }
        }

// asigna el dato del adapter al control de la vista
        fun bindView(Horario: Horario){
            this.Horario = Horario
            itemView.IdEspacio_item.text = this.Horario?.IDEspacio.toString()
            itemView.Descripcion_item.text = this.Horario?.Descripcion
            itemView.Hora_item.text = this.Horario?.Hora.toString()

        }

    }

    interface OnItemClickListener {
        fun onItemClick(Horario: Horario)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}