package com.epalburquerqueiii.aexperience.Data.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epalburquerqueiii.aexperience.Data.Model.Persona
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.item_persona.view.Direccion
import kotlinx.android.synthetic.main.item_persona.view.Nombre
import kotlinx.android.synthetic.main.item_persona.view.Poblacion


class PersonasAdapter(private val personas: ArrayList<Persona>, context: Context) : RecyclerView.Adapter<PersonasAdapter.PersonaViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_persona, parent, false)
        return PersonaViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return personas.size
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
       val itemnotes = personas[position]
        holder.bindView(itemnotes)

    }

    inner class PersonaViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var Persona:Persona? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(personas[position])
                }
            }
        }

// asigna el dato del adapter al control de la vista
        fun bindView(Persona: Persona){
            this.Persona = Persona
            itemView.Nombre.text = this.Persona?.Nombre
            itemView.Direccion.text = this.Persona?.Direccion
            itemView.Poblacion.text = this.Persona?.Poblacion
//            itemView.Email.text = this.Persona?.Email
        }

    }

    interface OnItemClickListener {
        fun onItemClick(Persona: Persona)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}