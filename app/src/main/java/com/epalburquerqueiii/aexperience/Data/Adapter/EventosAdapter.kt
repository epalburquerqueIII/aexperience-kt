package com.epalburquerqueiii.aexperience.Data.Adapter


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import com.epalburquerqueiii.aexperience.Data.Model.Config.Companion.cms_url
import com.epalburquerqueiii.aexperience.Data.Model.Evento
import com.epalburquerqueiii.aexperience.Data.Util.Comun.Companion.StringYMDtoDMY
import com.epalburquerqueiii.aexperience.R
import com.epalburquerqueiii.aexperience.UI.Espacios.EspacioActivity
import com.epalburquerqueiii.aexperience.UI.Eventos.DetailsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_eventos.view.*
import kotlin.collections.ArrayList

class EventosAdapter(private val eventoList: ArrayList<Evento>, context: Context) : RecyclerView.Adapter<EventosAdapter.EventoViewHolder>() {

    private var fecha : String = ""

    private var context: Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_eventos, parent, false)
        return EventoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return eventoList.size
    }

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val itemnotes = eventoList[position]
        holder.bindView(itemnotes)
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
            if (this.Evento?.Title != "No hay registros") {
                var fecha: String = ""
                if (this.Evento?.Date != "")
                    fecha = StringYMDtoDMY(this.Evento?.Date!!.substring(0, 9))
                loadImageFromUrl(itemView.itemImagen, this.Evento?.Image)
                itemView.itemTitle.text = this.Evento?.Title
                itemView.itemFecha.text = fecha
            }
        }

        fun loadImageFromUrl(imageview:ImageView, imageurl: String?) {
            Log.i("Procesando la  imagen ", imageurl)
            try {
                Picasso.get()
                    .load(cms_url+imageurl)
                    .into(imageview)

            }
            catch (e: Exception) {
                Log.i("Error en la imagen ", imageurl)

            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(Evento: Evento)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

}