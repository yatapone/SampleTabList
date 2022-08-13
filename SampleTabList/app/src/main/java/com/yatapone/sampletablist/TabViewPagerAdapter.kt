package com.yatapone.sampletablist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.yatapone.sampletablist.MainActivity.Companion.tabCategoryNum


class TabViewPagerAdapter(fragmentManager: FragmentManager, private val tabTitles: ArrayList<String>) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        private const val TAG = "TabViewPagerAdapter"
        const val VIEW_PAGE_NUM = "VIEW_PAGE_NUM"
    }

    override fun getCount(): Int {
        return tabCategoryNum
    }

    override fun getItem(position: Int): Fragment {
        // Log.d(TAG, "getItem: position=$position")
        val tabFragment = TabFragment.newInstance()
        val args = Bundle()
        args.putInt(VIEW_PAGE_NUM, position)
        tabFragment.arguments = args
        return tabFragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}
