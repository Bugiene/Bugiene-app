package com.example.bugiene.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bugiene.R
import com.example.bugiene.model.HistoryResultResponse

/** Raihan Chaira on 5/29/2023
 * raihanchaira21@gmail.com
 */
class HistoryAdapter (var listHistory : List<HistoryResultResponse>?) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTittle: TextView = view.findViewById(R.id.tv_tittle)
        val tvAccuracy: TextView = view.findViewById(R.id.tv_accuracy)
        val tvImage: TextView = view.findViewById(R.id.iv_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        )
    }

    override fun getItemCount(): Int = listHistory?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history = listHistory?.get(position)
        if (history != null) {
            holder.tvTittle.text = history.freshness
        }
        if (history != null) {
            holder.tvAccuracy.text = history.accuracy.toString()
        }
        if (history != null) {
            holder.tvImage.text = history.imageUrl
        }
    }

}