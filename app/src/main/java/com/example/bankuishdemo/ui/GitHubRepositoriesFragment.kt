package com.example.bankuishdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bankuishdemo.databinding.FragmentGithubRepositoriesBinding
import com.example.bankuishdemo.ui.adapter.GitHubRepositoriesAdapter
import com.example.bankuishdemo.ui.model.GitHubRepositoryModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GitHubRepositoriesFragment : Fragment() {

    private lateinit var binding: FragmentGithubRepositoriesBinding
    private val viewModel: GitHubRepositoriesViewModel by viewModels()

    private var githubAdapter: GitHubRepositoriesAdapter? = null
    private var gitHubRepositoriesPagingData: PagingData<GitHubRepositoryModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        githubAdapter = GitHubRepositoriesAdapter {
            navigateToDetailRepository(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGithubRepositoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObservers()
    }

    private fun initView() {
        binding.githubRepositoriesRecyclerView.apply {
            adapter = githubAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getGitHubRepositories().collectLatest {
                if (gitHubRepositoriesPagingData == null) {
                    gitHubRepositoriesPagingData = it
                }
                githubAdapter?.submitData(gitHubRepositoriesPagingData!!)
            }
        }
    }

    private fun navigateToDetailRepository(gitHubRepositoryModel: GitHubRepositoryModel) {
        val action =
            GitHubRepositoriesFragmentDirections.actionGitHubRepositoriesFragmentToGitHubRepositoryDetailFragment(
                gitHubRepositoryModel
            )
        findNavController().navigate(action)
    }

}