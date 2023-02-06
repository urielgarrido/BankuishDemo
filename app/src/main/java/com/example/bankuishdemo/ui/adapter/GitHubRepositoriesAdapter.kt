package com.example.bankuishdemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bankuishdemo.R
import com.example.bankuishdemo.databinding.ItemGithubRepositoryBinding
import com.example.bankuishdemo.ui.model.GitHubRepositoryModel

class RepositoriesDiffCallback : DiffUtil.ItemCallback<GitHubRepositoryModel>() {
    override fun areItemsTheSame(
        oldItem: GitHubRepositoryModel,
        newItem: GitHubRepositoryModel
    ): Boolean {
        return oldItem.repositoryName == newItem.repositoryName
    }

    override fun areContentsTheSame(
        oldItem: GitHubRepositoryModel,
        newItem: GitHubRepositoryModel
    ): Boolean {
        return oldItem == newItem
    }
}

class GitHubRepositoriesAdapter(
    private val click: (GitHubRepositoryModel) -> Unit
) : PagingDataAdapter<GitHubRepositoryModel, GitHubRepositoriesAdapter.MyViewHolder>(RepositoriesDiffCallback()) {

    inner class MyViewHolder(private val itemBinding: ItemGithubRepositoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(model: GitHubRepositoryModel) {
            itemBinding.nameRepositoryTextView.text = model.repositoryName
            itemBinding.nameOwnerTextView.text = model.ownerName
            Glide.with(itemView).load(model.imageUrl).apply(RequestOptions.circleCropTransform())
                .placeholder(
                    itemView.context.resources.getDrawable(R.drawable.circle_background, itemView.context.theme)
                )
                .into(itemBinding.avatarImageView)
            itemBinding.root.setOnClickListener {
                click(model)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemGithubRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
}