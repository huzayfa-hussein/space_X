package com.hu.spacex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.hu.spacex.R
import com.hu.spacex.data.common.Resource
import com.hu.spacex.data.common.onError
import com.hu.spacex.data.common.onLoading
import com.hu.spacex.data.common.onSuccess
import com.hu.spacex.databinding.ActivityMainBinding
import com.hu.spacex.ui.items.LaunchUiItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
            lifecycleScope.launch {
                resource.onLoading {
                    // show loader
                }.onSuccess {
                    // hide loader
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
                }.onError { message, _ ->
// hide loading

                    // show error message
                }
            }


        }

        setRecyclerView()
    }

    private fun setRecyclerView() {
        binding.recyclerView.adapter = adapter

        viewModel.launchListData.observe(this) { resource ->
            lifecycleScope.launch {
                resource.onSuccess { items ->
                    adapter.items = items
                    adapter.notifyDataSetChanged()
                }
            }

        }
    }

    private fun callDummyData() {
        // get company info dummy data
        viewModel.getCompanyInfoData()
        viewModel.getAllLaunches()
    }
}