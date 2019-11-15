package com.epalburquerqueiii.aexperience.Data.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.epalburquerqueiii.aexperience.Data.Model.Newsletter
import com.epalburquerqueiii.aexperience.R
import kotlinx.android.synthetic.main.item_newsletter.view.*


class NewslettersAdapter(private val newsletters: ArrayList<Newsletter>, context: Context) : RecyclerView.Adapter<NewslettersAdapter.NewsletterViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsletterViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_newsletter, parent, false)
        return NewsletterViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return newsletters.size
    }

    override fun onBindViewHolder(holder: NewsletterViewHolder, position: Int) {
       val itemnotes = newsletters[position]
        holder.bindView(itemnotes)

    }

    inner class NewsletterViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var Newsletter:Newsletter? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(newsletters[position])
                }
            }
        }

// asigna el dato del adapter al control de la vista
        fun bindView(Newsletter: Newsletter){
            this.Newsletter = Newsletter
            itemView.email_item.text = this.Newsletter?.Email
            itemView.idtiponoticias_item.text = this.Newsletter?.Temas

        }

    }

    interface OnItemClickListener {
        fun onItemClick(Newsletter: Newsletter)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}