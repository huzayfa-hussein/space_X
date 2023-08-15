package com.hu.spacex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.hu.spacex.R
import com.hu.spacex.data.common.Resource
import com.hu.spacex.databinding.ActivityMainBinding
import com.hu.spacex.ui.items.LaunchUiItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val adapter by lazy {
        LaunchAdapter(arrayListOf()) {
            handleItemClicked(it)
        }
    }

    private fun handleItemClicked(item: LaunchUiItem) {
        openLinksBottomSheet(item)
    }

    private fun openLinksBottomSheet(item: LaunchUiItem) {
        Toast.makeText(this, item.missionName, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callDummyData()
        observeData()
    }

    private fun observeData() {
        binding.textViewDescription.text = resources.getString(R.string.company_description_info)

        // observe company info data and update text_view_description
        viewModel.companyInfoData.observe(this) { resource ->
            if (resource is Resource.Success) {
                val companyInfo = resource.data!!
                val text = String.format(
                    resources.getString(R.string.company_description_info),
                    companyInfo.companyName,
                    companyInfo.founderName,
                    companyInfo.foundedYear,
                    companyInfo.employeesNumber,
                    companyInfo.sitesNumber,
                    companyInfo.valuation
                )
                binding.textViewDescription.text = text
            }

        }

        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.recyclerView.adapter = adapter

        viewModel.launchListData.observe(this) { items ->
            adapter.items = items
            adapter.notifyDataSetChanged()
        }
    }

    private fun callDummyData() {
        // get company info dummy data
        viewModel.getCompanyInfoData()

        viewModel.generateDummyLaunches()
    }
}