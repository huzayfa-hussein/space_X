package com.hu.spacex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hu.spacex.data.AppRepositoryImpl
import com.hu.spacex.data.common.Resource
import com.hu.spacex.data.common.onError
import com.hu.spacex.data.common.onSuccess
import com.hu.spacex.extensions.DATE_FORMAT
import com.hu.spacex.extensions.TIME_FORMAT
import com.hu.spacex.extensions.calculateDays
import com.hu.spacex.extensions.getFormattedDate
import com.hu.spacex.extensions.isInPast
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

    private var _launchListData = MutableLiveData<Resource<ArrayList<LaunchUiItem>>>()
    val launchListData: LiveData<Resource<ArrayList<LaunchUiItem>>> = _launchListData

    private var _loaderStatus = MutableLiveData<Boolean>()
    val loaderStatus: LiveData<Boolean> = _loaderStatus


    fun getCompanyInfoData() {
        viewModelScope.launch {
            _loaderStatus.value = true
            appRepositoryImpl.fetchCompanyInfo().collectLatest {
                it.onSuccess { companyData ->
                    val item = CompanyInfoUiItem(
                        companyName = companyData.name,
                        founderName = companyData.founder,
                        foundedYear = companyData.founded,
                        sitesNumber = companyData.launchSites,
                        employeesNumber = companyData.employees,
                        valuation = companyData.valuation
                    )
                    _loaderStatus.value = false
                    _companyInfoData.value = Resource.Success(item)
                }.onError { message, _ ->
                    _companyInfoData.value = Resource.Error(message = message)
                    _loaderStatus.value = false
                }
            }
        }
    }


    fun getAllLaunches() {
        viewModelScope.launch {
            appRepositoryImpl.fetchLaunches().collectLatest {
                it.onSuccess { launches ->
                    val sortedLaunches = launches.sortedByDescending { data -> data.date }
                    val items = arrayListOf<LaunchUiItem>()
                    sortedLaunches.forEach { launch ->
                        items.add(
                            LaunchUiItem(
                                missionName = launch.missionName,
                                launchIcon = launch.links.image ?: "",
                                rocketName = "${launch.rocket.rocketName} / ${launch.rocket.rocketType}",
                                missionDateAndTimes = getMissionDateAndTime(launch.date * 1000L),
                                isSuccessful = launch.launchSuccess,
                                launchDays = getLaunchDays(launch.date * 1000L),
                                launchDaysLabel = getLaunchDaysLabel(launch.date * 1000L),
                                articleLink = launch.links.articleLink ?: "",
                                youtubeLink = launch.links.youtubeLink ?: "",
                                wikipediaLink = launch.links.wikipediaLink ?: ""
                            )
                        )
                    }
                    _launchListData.value = Resource.Success(items)
                    _loaderStatus.value = false

                }
            }
        }
    }

    private fun getLaunchDaysLabel(date: Long): String {
        return if (date.isInPast()) {
            "Days since now:"
        } else {
            "Days from now:"
        }
    }

    private fun getLaunchDays(date: Long): String {
        return date.calculateDays()
    }

    private fun getMissionDateAndTime(date: Long): String {
        val dateString = date.getFormattedDate(format = DATE_FORMAT)
        val timeString = date.getFormattedDate(format = TIME_FORMAT)
        return "$dateString at $timeString"
    }

}