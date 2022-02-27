package com.cricmads.naviassignment.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cricmads.naviassignment.api.PullsService
import com.cricmads.naviassignment.models.PullsResponse
import com.cricmads.naviassignment.utils.NetworkUtils
import com.cricmads.naviassignment.utils.Result

class PullsRepository(
    private val pullsService: PullsService,
    private val applicationContext: Context
) {

    private val responseLiveData = MutableLiveData<Result<PullsResponse>>()

    val liveData: LiveData<Result<PullsResponse>>
    get() = responseLiveData

    suspend fun getPullRequests(user: String, repo: String, state: String){

        if (NetworkUtils.isInternetAvailable(applicationContext)){

            val response = pullsService.getPullRequests(user, repo, state)

            if (response.isSuccessful && response.body() != null)
                responseLiveData.postValue(Result.Success(response.body()!!))
            else responseLiveData.postValue(Result.Error(response.message()))

        }else responseLiveData.postValue(Result.NetworkUnavailable())

    }
}