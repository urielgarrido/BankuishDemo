package com.example.bankuishdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bankuishdemo.R
import com.example.bankuishdemo.databinding.FragmentDetailGithubRepositoryBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class GitHubRepositoryDetailFragment: Fragment() {

    private lateinit var binding: FragmentDetailGithubRepositoryBinding
    private val args: GitHubRepositoryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailGithubRepositoryBinding.inflate(inflater, container, false)
        setData()
        return binding.root
    }

    private fun setData() {
        val gitHubRepositoryModel = args.githubRepository
        binding.apply {
            nameRepositoryTextView.text = gitHubRepositoryModel.repositoryName
            setImage(gitHubRepositoryModel.imageUrl)
            ownerNameTextView.text = gitHubRepositoryModel.ownerName
            descriptionDataTextView.text = gitHubRepositoryModel.description
            createdAtDataTextView.text = transformDate(gitHubRepositoryModel.createdAt)
            watchersDataTextView.text = gitHubRepositoryModel.watchers.toString()
        }
        if (gitHubRepositoryModel.license.isNullOrEmpty()) {
            binding.licenseTextView.isVisible = false
            binding.licenseDataTextView.isVisible = false
        } else {
            binding.licenseDataTextView.text = gitHubRepositoryModel.license
        }
    }

    private fun transformDate(date: String): String {
        val sdfIso = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val dateIso = sdfIso.parse(date)!!
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return sdf.format(dateIso)
    }

    private fun setImage(imageUrl: String) {
        Glide.with(requireContext()).load(imageUrl).apply(RequestOptions.circleCropTransform())
            .placeholder(
                resources.getDrawable(R.drawable.circle_background, requireContext().theme)
            )
            .into(binding.ownerImageView)
    }
}