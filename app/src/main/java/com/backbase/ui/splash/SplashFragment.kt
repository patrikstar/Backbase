package com.backbase.ui.splash

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.backbase.databinding.SplashFragmentBinding
import com.backbase.ui.base.BaseFragment
import com.backbase.ui.splash.model.SplashViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<SplashFragmentBinding>(SplashFragmentBinding::inflate) {

    private val viewModel by viewModel<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.splashLiveData.observe(viewLifecycleOwner) { state ->
            handleSplashViewState(state)
        }
    }

    private fun handleSplashViewState(state: SplashViewState) = when (state) {
        is SplashViewState.Success -> {
            findNavController().navigate(SplashFragmentDirections.actionGoToSearchFragment())
        }
        else -> showErrorMessage()
    }

    private fun showErrorMessage() {
        binding.pbSplash.isVisible = false
        binding.tvSplashError.isVisible = true
    }
}
