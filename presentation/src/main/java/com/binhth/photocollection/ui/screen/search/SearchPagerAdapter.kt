package com.binhth.photocollection.ui.screen.search

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.binhth.photocollection.R
import com.binhth.photocollection.ui.screen.collection.ListCollectionFragment
import com.binhth.photocollection.ui.screen.photo.ListPhotoFragment


class SearchPagerAdapter(fm: FragmentManager?, context: Context?) : FragmentPagerAdapter(fm) {
    private val tabTitles = arrayOf(
        context?.getString(R.string.search_collection_tab1),
        context?.getString(R.string.search_collection_tab2)
    )

    private var listCollectionFragment: ListCollectionFragment? = null
    private var listPhotoFragment: ListPhotoFragment? = null

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ListCollectionFragment.newInstance()
            1 -> ListPhotoFragment.newInstance(null, false)
            else -> ListPhotoFragment.newInstance(null, false)
        }
    }

    override fun getCount(): Int {
        return tabTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val createdFragment = super.instantiateItem(container, position) as Fragment
        // save the appropriate reference depending on position
        when (position) {
            0 -> listCollectionFragment = createdFragment as ListCollectionFragment
            1 -> listPhotoFragment = createdFragment as ListPhotoFragment
        }
        return createdFragment
    }

    fun requestNewSearch(searchQuery: String) {
        listCollectionFragment?.viewModel?.queryString?.value = searchQuery
        listPhotoFragment?.viewModel?.queryString?.value = searchQuery
    }
}
