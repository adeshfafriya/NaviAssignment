package com.cricmads.naviassignment.models

import com.google.gson.annotations.SerializedName

data class PullsItem(
    @SerializedName("title") var title: String?,
    @SerializedName("created_at") var createdAt: String?,
    @SerializedName("closed_at") var closedAt: String?,
    @SerializedName("user") var user: User?)
