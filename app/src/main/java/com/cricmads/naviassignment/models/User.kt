package com.cricmads.naviassignment.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") var login: String?,
    @SerializedName("avatar_url") var avatarUrl: String?)
