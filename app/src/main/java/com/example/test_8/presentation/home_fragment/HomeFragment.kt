package com.example.test_8.presentation.home_fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.test_8.databinding.FragmentHomeLayoutBinding
import com.example.test_8.presentation.adapter.HomeImageViewPagerAdapter
import com.example.test_8.presentation.base.BaseFragment
import com.example.test_8.presentation.event.HomeEvent
import com.example.test_8.presentation.model.HomeImageState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeLayoutBinding>(FragmentHomeLayoutBinding::inflate) {

    private val homeViewModel : HomeViewModel by viewModels()

    override fun bind() {
        bindItems()
        initializeViewPager()
    }

    override fun bindViewActionListeners() {

    }

    override fun bindObservers() {
        bindHomeStateFlow()
    }

    private fun bindItems() {
        homeViewModel.onEvent(HomeEvent.GetHomeItems)
    }

    private fun initializeViewPager() {
        val adapter = HomeImageViewPagerAdapter()
        binding.pager.adapter = adapter
    }

    private fun bindHomeStateFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.homeItemState.collect {state ->
                    handleHomeState(state)
                }
            }
        }
    }

    private fun handleHomeState(state : HomeImageState) {
        binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE

        state.isSuccess?.let { homeImages ->
            (binding.pager.adapter as? HomeImageViewPagerAdapter)?.submitList(homeImages)
        }

        state.errorMessage?.let { errorMessage ->
            showError(errorMessage)
        }
    }

    private fun showError(errorMessage : String) {

    }
}