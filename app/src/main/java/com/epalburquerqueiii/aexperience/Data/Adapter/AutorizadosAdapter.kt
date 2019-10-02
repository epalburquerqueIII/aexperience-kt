package com.epalburquerqueiii.aexperience.Data.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epalburquerqueiii.aexperience.Data.Model.Autorizado
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.item_autorizado.view.*


class AutorizadosAdapter(private val autorizados: ArrayList<Autorizado>, context: Context) : RecyclerView.Adapter<AutorizadosAdapter.AutorizadoViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutorizadoViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_autorizado, parent, false)
        return AutorizadoViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return autorizados.size
    }

    override fun onBindViewHolder(holder: AutorizadoViewHolder, position: Int) {
       val itemnotes = autorizados[position]
        holder.bindView(itemnotes)

    }

    inner class AutorizadoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var Autorizado:Autorizado? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(autorizados[position])
                }
            }
        }

// asigna el dato del adapter al control de la vista
        fun bindView(Autorizado: Autorizado){
            this.Autorizado = Autorizado
            itemView.Nombre_item.text = this.Autorizado?.NombreAutorizado
            itemView.nif_item.text = this.Autorizado?.Nif
        }

    }

    interface OnItemClickListener {
        fun onItemClick(Autorizado: Autorizado)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}