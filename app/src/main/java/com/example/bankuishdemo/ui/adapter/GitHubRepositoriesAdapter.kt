package com.example.bankuishdemo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bankuishdemo.R
import com.example.bankuishdemo.databinding.ItemGithubRepositoryBinding
import com.example.bankuishdemo.ui.model.GitHubRepositoryModel

class GitHubRepositoriesAdapter(
    private val repositoriesModel: List<GitHubRepositoryModel>,
    private val click: (GitHubRepositoryModel) -> Unit
) : RecyclerView.Adapter<GitHubRepositoriesAdapter.MyViewHolder>() {

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

    override fun getItemCount(): Int = repositoriesModel.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(repositoriesModel[position])
    }
}