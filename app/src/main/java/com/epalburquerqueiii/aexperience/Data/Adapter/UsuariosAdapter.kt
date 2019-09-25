package com.epalburquerqueiii.aexperience.Data.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epalburquerqueiii.aexperience.Data.Model.Usuario
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.item_usuario.view.*


class UsuariosAdapter(private val usuarios: ArrayList<Usuario>, context: Context) : RecyclerView.Adapter<UsuariosAdapter.UsuarioViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_usuario, parent, false)
        return UsuarioViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
       val itemnotes = usuarios[position]
        holder.bindView(itemnotes)

    }

    inner class UsuarioViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var Usuario:Usuario? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(usuarios[position])
                }
            }
        }

// asigna el dato del adapter al control de la vista
        fun bindView(Usuario: Usuario){
            this.Usuario = Usuario
            itemView.nombreusuario_item.text = this.Usuario?.Nombre
            itemView.nif_item.text = this.Usuario?.Nif
        }

    }

    interface OnItemClickListener {
        fun onItemClick(Usuario: Usuario)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}