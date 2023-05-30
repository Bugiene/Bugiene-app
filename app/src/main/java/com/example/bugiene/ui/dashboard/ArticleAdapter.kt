package com.example.bugiene.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bugiene.R
import com.example.bugiene.model.Article

/** Raihan Chaira on 5/20/2023
 * raihanchaira21@gmail.com
 */
class ArticleAdapter(private val ListArticle: ArrayList<Article>) : RecyclerView.Adapter<ArticleAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, field, photo) = ListArticle[position]
        holder.tittle.text = title
        holder.field.text = field
        holder.photo.setImageResource(photo)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(ListArticle[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = ListArticle.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Article)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.iv_image)
        val tittle: TextView = itemView.findViewById(R.id.tv_tittle)
        val field: TextView = itemView.findViewById(R.id.tv_field)
    }
}
