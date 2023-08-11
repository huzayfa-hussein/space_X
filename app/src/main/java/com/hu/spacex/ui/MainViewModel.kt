package com.hu.spacex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hu.spacex.ui.items.CompanyInfoUiItem
import com.hu.spacex.ui.items.LaunchUiItem

class MainViewModel : ViewModel() {

    private var _companyInfoData = MutableLiveData<CompanyInfoUiItem>()
    val companyInfoData: LiveData<CompanyInfoUiItem> = _companyInfoData

    private var _launchListData = MutableLiveData<ArrayList<LaunchUiItem>>()
    val launchListData: LiveData<ArrayList<LaunchUiItem>> = _launchListData

    fun generateDummyCompanyInfo() {
        val item = CompanyInfoUiItem(
            companyName = "SpaceX",
            founderName = "Huzayfa",
            foundedYear = "1997",
            sitesNumber = "3",
            employeesNumber = "2000",
            valuation = "250000"
        )

        _companyInfoData.value = item
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