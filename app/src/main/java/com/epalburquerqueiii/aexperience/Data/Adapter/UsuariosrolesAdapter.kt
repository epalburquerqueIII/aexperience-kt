package com.epalburquerqueiii.aexperience.Data.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epalburquerqueiii.aexperience.Data.Model.Usuariorol
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.item_usuariorol.view.*


class UsuariosrolesAdapter(private val usuariosroles: ArrayList<Usuariorol>, context: Context) : RecyclerView.Adapter<UsuariosrolesAdapter.UsuariosrolesViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosrolesViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_usuariorol, parent, false)
        return UsuariosrolesViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return usuariosroles.size
    }

    override fun onBindViewHolder(holder: UsuariosrolesViewHolder, position: Int) {
       val itemnotes = usuariosroles[position]
        holder.bindView(itemnotes)

    }

    inner class UsuariosrolesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var Usuariorol:Usuariorol? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(usuariosroles[position])
                }
            }
        }

// asigna el dato del adapter al control de la vista
        fun bindView(Usuariorol: Usuariorol){
            this.Usuariorol = Usuariorol
            itemView.nombreusuariorol_item.text = this.Usuariorol?.Nombre
        }

    }

    interface OnItemClickListener {
        fun onItemClick(Usuariorol: Usuariorol)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}