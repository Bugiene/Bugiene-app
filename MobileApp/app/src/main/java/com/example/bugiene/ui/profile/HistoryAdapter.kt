package com.example.bugiene.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bugiene.R
import com.example.bugiene.model.HistoryResultResponse

/** Raihan Chaira on 5/29/2023
 * raihanchaira21@gmail.com
 */
class HistoryAdapter (var listHistory : List<HistoryResultResponse>?) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){
    fun setData(data: List<HistoryResultResponse>){
        listHistory = data
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTittle: TextView = view.findViewById(R.id.tv_title_fresh)
        val tvAccuracy: TextView = view.findViewById(R.id.tv_accuracy)
        val tvImage: ImageView = view.findViewById(R.id.iv_image)
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
            val freshness = boolConvert(history.freshness)
            holder.tvTittle.text = freshness

            val accuracy = accConvert(history.accuracy)
            holder.tvAccuracy.text = accuracy

            Glide.with(holder.itemView.context)
                .load(history.imageUrl)
                .into(holder.tvImage)
        }
    }

    private fun accConvert(result: Float) : String{
        var hasil = result * 100
        return "${hasil.toInt()} %"
    }

    private fun boolConvert(result: String) : String {
        if (result == "1"){
            return "Fresh"
        }else{
            return "Rotten"
        }
    }
}