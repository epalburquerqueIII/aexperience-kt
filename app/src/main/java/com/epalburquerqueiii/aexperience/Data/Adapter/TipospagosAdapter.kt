package com.epalburquerqueiii.aexperience.Data.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epalburquerqueiii.aexperience.Data.Model.Tipospago
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.item_tipospago.view.*


class TipospagosAdapter(private val tipospagos: ArrayList<Tipospago>, context: Context) : RecyclerView.Adapter<TipospagosAdapter.TipospagoViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipospagoViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_tipospago, parent, false)
        return TipospagoViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return tipospagos.size
    }

    override fun onBindViewHolder(holder: TipospagoViewHolder, position: Int) {
       val itemnotes = tipospagos[position]
        holder.bindView(itemnotes)

    }

    inner class TipospagoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var Tipospago:Tipospago? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(tipospagos[position])
                }
            }
        }

// asigna el dato del adapter al control de la vista
        fun bindView(Tipospago: Tipospago){
            this.Tipospago = Tipospago
            itemView.nombretipospago_item.text = this.Tipospago?.Nombre
        }

    }

    interface OnItemClickListener {
        fun onItemClick(Tipospago: Tipospago)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}