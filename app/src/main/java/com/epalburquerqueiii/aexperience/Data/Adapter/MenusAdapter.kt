package com.epalburquerqueiii.aexperience.Data.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epalburquerqueiii.aexperience.Data.Model.Menu
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.item_menu.view.*


class MenusAdapter(private val menus: ArrayList<Menu>, context: Context) : RecyclerView.Adapter<MenusAdapter.MenuViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return MenuViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return menus.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
       val itemnotes = menus[position]
        holder.bindView(itemnotes)

    }

    inner class MenuViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var Menu:Menu? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(menus[position])
                }
            }
        }

// asigna el dato del adapter al control de la vista
        fun bindView(Menu: Menu){
            this.Menu = Menu
            itemView.parentid_item.text = this.Menu?.ParentId.toString()
            itemView.titulo_item.text = this.Menu?.Titulo
            itemView.url_item.text = this.Menu?.Url
        }

    }

    interface OnItemClickListener {
        fun onItemClick(Menu: Menu)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}