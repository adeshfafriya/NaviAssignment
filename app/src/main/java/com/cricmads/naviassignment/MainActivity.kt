package com.cricmads.naviassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cricmads.naviassignment.adapters.MainAdapter
import com.cricmads.naviassignment.databinding.ActivityMainBinding
import com.cricmads.naviassignment.models.PullsResponse
import com.cricmads.naviassignment.utils.Result
import com.cricmads.naviassignment.viewmodels.MainViewModel
import com.cricmads.naviassignment.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity(), Observer<Result<PullsResponse>> {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as PullRequestsApplication).repository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
        mainViewModel.resultLiveData.observe(this, this)
    }

    override fun onChanged(t: Result<PullsResponse>?) {

        binding.progressBar.visibility = View.GONE

        when(t){
            is Result.Success -> { onResultSuccess(t.data) }
            is Result.Error -> { onResultFailure(t.message) }
            is Result.NetworkUnavailable -> { onResultFailure(getString(R.string.internet_connection_unavailable)) }
            null -> { onResultFailure(getString(R.string.unknown_error)) }
        }
    }

    private fun onResultSuccess(data: PullsResponse?) {
        if (data != null && data.size > 0){

            binding.recyclerView.visibility = View.VISIBLE
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
            binding.recyclerView.adapter = MainAdapter(data)

        } else {
            onResultFailure(getString(R.string.no_requests_found))
        }
    }

    private fun onResultFailure(message: String?){
        binding.errorGroup.visibility = View.VISIBLE
        binding.messageText.text = message?:getString(R.string.unknown_error)
        binding.reloadButton.setOnClickListener { onReload() }
    }

    private fun onReload(){
        binding.progressBar.visibility = View.VISIBLE
        binding.errorGroup.visibility = View.GONE
        mainViewModel.fetchPullRequests()
    }

}