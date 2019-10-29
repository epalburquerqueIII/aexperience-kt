package com.epalburquerqueiii.aexperience.Data.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epalburquerqueiii.aexperience.Data.Model.MenuRol
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.item_menurol.view.*


class MenuRolesAdapter(private val menuroles: ArrayList<MenuRol>, context: Context) : RecyclerView.Adapter<MenuRolesAdapter.MenuRolViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuRolViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_menurol, parent, false)
        return MenuRolViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return menuroles.size
    }

    override fun onBindViewHolder(holder: MenuRolViewHolder, position: Int) {
       val itemnotes = menuroles[position]
        holder.bindView(itemnotes)

    }

    inner class MenuRolViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var MenuRol: MenuRol? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(menuroles[position])
                }
            }
        }

// asigna el dato del adapter al control de la vista
        fun bindView(MenuRol: MenuRol){
            this.MenuRol = MenuRol
            //itemView.id_item.text = this.MenuRol?.ParentId.toString()
            itemView.idmenu_item.text = this.MenuRol?.IDMenu.toString()
            itemView.idusuarioroles_item.text = this.MenuRol?.IDUsuarioRoles.toString()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(MenuRol: MenuRol)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}