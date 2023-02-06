package com.example.bankuishdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankuishdemo.databinding.FragmentGithubRepositoriesBinding
import com.example.bankuishdemo.ui.adapter.GitHubRepositoriesAdapter
import com.example.bankuishdemo.ui.model.GitHubRepositoryModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitHubRepositoriesFragment : Fragment() {

    private lateinit var binding: FragmentGithubRepositoriesBinding
    private val viewModel: GitHubRepositoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGithubRepositoriesBinding.inflate(inflater, container, false)

        loading(true)
        getGitHubRepositories()
        setObservers()

        return binding.root
    }

    private fun getGitHubRepositories() {
        viewModel.getGitHubRepositories()
    }

    private fun setObservers() {
        viewModel.gitHubRepositories.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                setRecyclerViewWithData(it)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(
                    requireContext(),
                    "Ocurrio un error con la obtencion de los repositorios de GitHub",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun setRecyclerViewWithData(gitHubRepositoryModels: List<GitHubRepositoryModel>) {
        binding.githubRepositoriesRecyclerView.apply {
            adapter = GitHubRepositoriesAdapter(gitHubRepositoryModels) {
                navigateToDetailRepository(it)
            }
            layoutManager = LinearLayoutManager(requireContext())
        }
        loading(false)
    }

    private fun navigateToDetailRepository(gitHubRepositoryModel: GitHubRepositoryModel) {
        val action =
            GitHubRepositoriesFragmentDirections.actionGitHubRepositoriesFragmentToGitHubRepositoryDetailFragment(
                gitHubRepositoryModel
            )
        findNavController().navigate(action)
    }

    private fun loading(value: Boolean) {
        binding.loadingProgressBar.isVisible = value
        binding.githubRepositoriesRecyclerView.isVisible = !value
    }

}