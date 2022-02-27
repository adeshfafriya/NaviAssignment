package com.cricmads.naviassignment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cricmads.naviassignment.repository.PullsRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val repository: PullsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }

}