package com.hu.spacex.ui

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.hu.spacex.R
import com.hu.spacex.databinding.CellLaunchItemBinding
import com.hu.spacex.extensions.setImageUrl
import com.hu.spacex.ui.items.LaunchUiItem

class LaunchVH(val binding: CellLaunchItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LaunchUiItem) {
        binding.textViewMissionValue.text = item.missionName
        binding.textViewDateValue.text = item.missionDateAndTimes
        binding.textViewRocketValue.text = item.rocketName
        binding.textViewDaysValue.text = item.launchDays
        binding.textViewDaysLabel.text = item.launchDaysLabel
        val imageResId = if (item.isSuccessful) {
            R.drawable.ic_success
        } else {
            R.drawable.ic_failed
        }
        binding.imageViewStatus.setImageResource(imageResId)
        binding.separator.isVisible = item.showSeparator
        binding.imageViewLaunch.setImageUrl(item.launchIcon)
    }
}