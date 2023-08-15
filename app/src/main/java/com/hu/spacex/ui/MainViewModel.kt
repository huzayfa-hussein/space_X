package com.hu.spacex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hu.spacex.data.AppRepositoryImpl
import com.hu.spacex.data.common.Resource
import com.hu.spacex.ui.items.CompanyInfoUiItem
import com.hu.spacex.ui.items.LaunchUiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val appRepositoryImpl: AppRepositoryImpl) :
    ViewModel() {

    private var _companyInfoData = MutableLiveData<Resource<CompanyInfoUiItem>>()
    val companyInfoData: LiveData<Resource<CompanyInfoUiItem>> = _companyInfoData

    private var _launchListData = MutableLiveData<ArrayList<LaunchUiItem>>()
    val launchListData: LiveData<ArrayList<LaunchUiItem>> = _launchListData


    fun getCompanyInfoData() {
        viewModelScope.launch {
            appRepositoryImpl.fetchCompanyInfo().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        val companyData = it.data
                        if (companyData == null) {
                            _companyInfoData.value = Resource.Error(message = "No Data available")
                        } else {
                            val item = CompanyInfoUiItem(
                                companyName = companyData.name,
                                founderName = companyData.founder,
                                foundedYear = companyData.founded,
                                sitesNumber = companyData.launchSites,
                                employeesNumber = companyData.employees,
                                valuation = companyData.valuation
                            )
                            _companyInfoData.value = Resource.Success(item)
                        }
                    }

                    is Resource.Error -> {
                        _companyInfoData.value = Resource.Error(message = it.message)
                    }

                    else -> {}
                }
            }
        }
    }

    fun generateDummyLaunches() {
        val items = arrayListOf<LaunchUiItem>(
            LaunchUiItem(
                missionName = "FalconSat",
                missionDateAndTimes = "25 Mar 2006 at 01:30 am",
                rocketName = "Falcon 1 / Merlin A",
                launchDays = "6348",
                launchDaysLabel = "Days since now:",
                isSuccessful = false
            ), LaunchUiItem(
                missionName = "FalconSat",
                missionDateAndTimes = "25 Mar 2006 at 01:30 am",
                rocketName = "Falcon 1 / Merlin A",
                launchDays = "6348",
                launchDaysLabel = "Days from now:",
                isSuccessful = true
            ),
            LaunchUiItem(
                missionName = "FalconSat",
                missionDateAndTimes = "25 Mar 2006 at 01:30 am",
                rocketName = "Falcon 1 / Merlin A",
                launchDays = "6348",
                launchDaysLabel = "Days from now:",
                isSuccessful = false,
                showSeparator = false
            )
        )
        _launchListData.value = items
    }
}