package com.hu.spacex.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hu.spacex.databinding.CellLaunchItemBinding
import com.hu.spacex.ui.items.LaunchUiItem

class LaunchAdapter(
    var items: ArrayList<LaunchUiItem>,
    val onItemClicked: (item: LaunchUiItem) -> Unit
) :
    RecyclerView.Adapter<LaunchVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchVH {
        val binding =
            CellLaunchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return LaunchVH(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: LaunchVH, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.binding.root.setOnClickListener {
            onItemClicked.invoke(item)
        }
    }

}