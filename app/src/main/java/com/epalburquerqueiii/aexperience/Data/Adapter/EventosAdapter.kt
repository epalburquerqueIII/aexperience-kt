package com.epalburquerqueiii.aexperience.Data.Adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.epalburquerqueiii.aexperience.Data.Model.Config.Companion.cms_url
import com.epalburquerqueiii.aexperience.Data.Model.Evento
import com.epalburquerqueiii.aexperience.Data.Model.Eventos
import com.epalburquerqueiii.aexperience.Data.Model.Menu
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.UI.Eventos.DetailsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_eventos.view.*
import kotlinx.android.synthetic.main.item_menu.view.*

class EventosAdapter(private val eventoList: ArrayList<Evento>, context: Context) : RecyclerView.Adapter<EventosAdapter.EventoViewHolder>() {

    private var context: Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_eventos, parent, false)
        return EventoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return eventoList.size
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val event: Evento = eventoList[position]
        holder.bindView(event)
    }

    inner class EventoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var Evento:Evento? = null

        fun onClick(position: Int) {
            Toast.makeText(context,""+position, Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(eventoList[position])
                }
            }
        }

        // asigna el dato del adapter al control de la vista
        fun bindView(Evento: Evento){
            this.Evento = Evento
            val myImageView = itemView.findViewById(R.id.eventoThumbnail) as ImageView
            loadImageFromUrl(myImageView,this.Evento?.Image)
            itemView.textViewTitle.text = this.Evento?.Description
            itemView.textViewYear.text = this.Evento?.Date

        }

        fun loadImageFromUrl(imageview:ImageView, imageurl: String?) {
            Picasso.get()
                .load(cms_url+imageurl)
                .into(imageview)
        }

    }

    interface OnItemClickListener {
        fun onItemClick(Evento: Evento)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}