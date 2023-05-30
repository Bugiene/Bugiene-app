package com.example.bugiene.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bugiene.databinding.ItemSlideBinding

/** Raihan Chaira on 5/20/2023
 * raihanchaira21@gmail.com
 */

class ImageSliderAdapter(
    private val drawableResourceIds: List<Int>
) : RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(private val binding: ItemSlideBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(drawableResourceId: Int) {
            binding.ivSlider.setImageResource(drawableResourceId)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageSliderAdapter.ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSlideBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageSliderAdapter.ImageViewHolder, position: Int) {
        val drawableResourceId = drawableResourceIds[position]
        holder.bind(drawableResourceId)
    }

    override fun getItemCount(): Int {
        return drawableResourceIds.size
    }
}