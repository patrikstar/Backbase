package com.backbase.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.databinding.SearchFragmentBinding
import com.backbase.domain.model.CityDomainModel
import com.backbase.ui.base.BaseFragment
import com.backbase.ui.search.model.ListViewState
import com.backbase.ui.search.recycler.ListAdapter
import com.backbase.ui.utils.extensions.hideKeyboardOnFocusLost
import com.backbase.ui.utils.extensions.onImeDone
import com.backbase.ui.utils.textwatcher.SimpleTextWatcher
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<SearchFragmentBinding>(SearchFragmentBinding::inflate) {

    private val listAdapter: ListAdapter by inject()
    private val viewModel by viewModel<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etSearch.apply {
            addTextChangedListener(SimpleTextWatcher(viewModel::onUserTyped))
            hideKeyboardOnFocusLost()
            onImeDone { clearFocus() }
        }
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
        }
        listAdapter.onItemClick = { item ->
            findNavController().navigate(
                SearchFragmentDirections.actionGoToMapFragment(item)
            )
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.listLiveData.observe(viewLifecycleOwner, Observer {
            handleViewState(it)
        })
    }

    private fun handleViewState(state: ListViewState) = when (state) {
        is ListViewState.Success -> showCities(state.cities)
        is ListViewState.Message -> showMessage(state)
        ListViewState.Loading -> showLoadingState()
    }

    private fun showCities(items: List<CityDomainModel>) = with(binding) {
        rvList.scrollToPosition(0)
        rvList.isVisible = true
        pbList.isVisible = false
        tvListError.isVisible = false
        listAdapter.items = items.toMutableList()
    }

    private fun showMessage(state: ListViewState.Message) = with(binding) {
        rvList.isVisible = false
        pbList.isVisible = false
        tvListError.text = getString(state.message.messageId)
        tvListError.isVisible = true
    }

    private fun showLoadingState() = with(binding) {
        rvList.isVisible = false
        pbList.isVisible = true
        tvListError.isVisible = false
    }
}
