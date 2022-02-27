package com.cricmads.naviassignment

import android.app.Application
import com.cricmads.naviassignment.api.PullsService
import com.cricmads.naviassignment.api.RetrofitHelper
import com.cricmads.naviassignment.repository.PullsRepository

class PullRequestsApplication: Application() {

    lateinit var repository: PullsRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val pullsService = RetrofitHelper.getInstance().create(PullsService::class.java)
        repository = PullsRepository(pullsService, applicationContext)
    }

}