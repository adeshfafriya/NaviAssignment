package com.cricmads.naviassignment.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cricmads.naviassignment.R
import com.cricmads.naviassignment.databinding.PullRequestLayoutItemBinding
import com.cricmads.naviassignment.models.PullsItem
import com.cricmads.naviassignment.models.PullsResponse

class MainAdapter(private var list : PullsResponse): RecyclerView.Adapter<MainAdapter.PullsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullsHolder {
        return PullsHolder(PullRequestLayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PullsHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PullsHolder(private var binding: PullRequestLayoutItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindData(item: PullsItem){

            binding.title.text = item.title

            if (!TextUtils.isEmpty(item.createdAt)){
                binding.created.visibility = View.VISIBLE
                binding.created.text = binding.root.context.getString(R.string.created_on, item.createdAt)
            } else binding.created.visibility = View.GONE


            if (!TextUtils.isEmpty(item.closedAt)){
                binding.closed.visibility = View.VISIBLE
                binding.closed.text = binding.root.context.getString(R.string.closed_on, item.closedAt)
            } else binding.closed.visibility = View.GONE


            if (item.user != null){

                if (!TextUtils.isEmpty(item.user!!.avatarUrl)){
                    binding.image.visibility = View.VISIBLE
                    Glide.with(binding.root.context).load(item.user!!.avatarUrl).into(binding.image)
                }else binding.image.visibility = View.GONE

                binding.userName.visibility = View.VISIBLE
                binding.userName.text = binding.root.context.getString(R.string.user, item.user!!.login)

            } else {
                binding.image.visibility = View.GONE
                binding.userName.visibility = View.GONE
            }

        }
    }

}