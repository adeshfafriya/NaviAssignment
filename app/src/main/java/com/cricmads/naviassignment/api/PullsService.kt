package com.cricmads.naviassignment.api

import com.cricmads.naviassignment.models.PullsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PullsService {

    @GET("/repos/{user}/{repo}/pulls")
    suspend fun getPullRequests(@Path("user")user: String, @Path("repo")repo: String, @Query("state")state: String) : Response<PullsResponse>

}