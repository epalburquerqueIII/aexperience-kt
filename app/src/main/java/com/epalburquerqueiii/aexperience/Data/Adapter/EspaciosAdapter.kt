package com.epalburquerqueiii.aexperience.Data.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epalburquerqueiii.aexperience.Data.Model.Espacio
import com.epalburquerqueiii.aexperience.Data.Util.Comun
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.item_espacios.view.*


class EspaciosAdapter(private val espacios: ArrayList<Espacio>, context: Context) : RecyclerView.Adapter<EspaciosAdapter.EspacioViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EspacioViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_espacios, parent, false)
        return EspacioViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return espacios.size
    }

    override fun onBindViewHolder(holder: EspacioViewHolder, position: Int) {
       val itemnotes = espacios[position]
        holder.bindView(itemnotes)

    }

    inner class EspacioViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var Espacio:Espacio? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(espacios[position])
                }
            }
        }

// asigna el dato del adapter al control de la vista
        fun bindView(Espacio: Espacio){
            this.Espacio = Espacio
            itemView.Descripcion.text = this.Espacio?.Descripcion
            if (this.Espacio?.Estado== 0){
                itemView.Estado.text = "cerrado"
            }else{
                itemView.Estado.text = "abierto"

            }

            var MostrarFecha : String? = this.Espacio?.Fecha
            itemView.Precio.text = this.Espacio?.Precio.toString()
                if (MostrarFecha != "") {
                    itemView.Fecha.text = Comun.StringYMDtoDMY(MostrarFecha)
                }

}

    }

    interface OnItemClickListener {
        fun onItemClick(Espacio: Espacio)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}