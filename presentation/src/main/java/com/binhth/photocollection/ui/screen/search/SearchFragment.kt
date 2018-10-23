package com.binhth.photocollection.ui.screen.search

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import com.binhth.photocollection.BR
import com.binhth.photocollection.R
import com.binhth.photocollection.databinding.FragmentSearchBinding
import com.binhth.photocollection.ui.screen.core.BaseFragment
import com.mancj.materialsearchbar.MaterialSearchBar
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(),
    MaterialSearchBar.OnSearchActionListener {

    companion object {
        const val TAG = "SearchFragment"

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    private lateinit var searchbar: MaterialSearchBar

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel by viewModel<SearchViewModel>()

    override fun initContent(viewBinding: FragmentSearchBinding) {
        val fragmentAdapter = SearchPagerAdapter(childFragmentManager, context)
        viewBinding.viewModel = viewModel
        viewBinding.apply {
            searchbar = searchBar
            viewpagerSearch.adapter = fragmentAdapter
            tabsSearch.setupWithViewPager(viewpagerSearch)
            searchbar.setOnSearchActionListener(this@SearchFragment)
            searchbar.setCardViewElevation(10)
            searchbar.addTextChangeListener(object : TextWatcher {
                override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                }

                override fun afterTextChanged(editable: Editable) {
                }
            })
        }

        viewModel.getSearchHistory()
        viewModel.apply {
            queryString.observe(this@SearchFragment, Observer {
                fragmentAdapter.requestNewSearch(it)
            })
            listHistoryItem.observe(this@SearchFragment, Observer {
                searchbar.lastSuggestions = it
            })
        }
        activity?.title = tag
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.isLoading.removeObservers(this)
        viewModel.listHistoryItem.removeObservers(this)
        viewModel.saveSearchHistory(ArrayList(searchbar.lastSuggestions))
    }

    override fun onButtonClicked(buttonCode: Int) {
    }

    override fun onSearchStateChanged(enabled: Boolean) {
    }

    override fun onSearchConfirmed(text: CharSequence?) {
        viewModel.apply { queryString.value = text.toString() }
    }

    override val layoutId: Int
        get() = R.layout.fragment_search
}