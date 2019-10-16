package com.epalburquerqueiii.aexperience.Data.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epalburquerqueiii.aexperience.Data.Model.Bono
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.item_bono.view.*


class BonosAdapter(private val bonos: ArrayList<Bono>, context: Context) : RecyclerView.Adapter<BonosAdapter.BonoViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BonoViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_bono, parent, false)
        return BonoViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return bonos.size
    }

    override fun onBindViewHolder(holder: BonoViewHolder, position: Int) {
       val itemnotes = bonos[position]
        holder.bindView(itemnotes)

    }

    inner class BonoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var Bono:Bono? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(bonos[position])
                }
            }
        }

// asigna el dato del adapter al control de la vista
        fun bindView(Bono: Bono){
            this.Bono = Bono
            itemView.precio_item.text = this.Bono?.Precio.toString()
            itemView.sesiones_item.text = this.Bono?.Sesiones.toString()
        }

    }
    interface OnItemClickListener {
        fun onItemClick(Bono: Bono)
    }


    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}