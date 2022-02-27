package com.cricmads.naviassignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cricmads.naviassignment.models.PullsResponse
import com.cricmads.naviassignment.repository.PullsRepository
import com.cricmads.naviassignment.utils.Constants
import com.cricmads.naviassignment.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PullsRepository): ViewModel() {

    init {
        fetchPullRequests()
    }

    val resultLiveData: LiveData<Result<PullsResponse>>
    get() = repository.liveData

    fun fetchPullRequests(){
        viewModelScope.launch(Dispatchers.IO){
            repository.getPullRequests(Constants.USER_ID, Constants.REPOSITORY_ID, Constants.STATE_CLOSED)
        }
    }
}