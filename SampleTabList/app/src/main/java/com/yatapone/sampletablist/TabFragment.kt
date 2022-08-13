package com.yatapone.sampletablist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yatapone.sampletablist.databinding.TabFragmentBinding

class TabFragment : Fragment() {
    companion object {
        private const val TAG = "TabFragment"

        fun newInstance() = TabFragment()
    }

    private lateinit var binding: TabFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d(TAG, "onCreateView: ")
        binding = TabFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pageNum = requireArguments().getInt(TabViewPagerAdapter.VIEW_PAGE_NUM)
        val tabCategory = MainActivity.allCards.tabCategoryList[pageNum]
        Log.d(TAG, "onViewCreated: pageNum=$pageNum, tabCategoryId=${tabCategory.tabCategoryId}, tabCategoryName=${tabCategory.tabCategoryName}")

        if (tabCategory.subCategoryList.size != 0) {
            tabCategory.subCategoryList.forEach { subCategory ->
                // insert a subCategoryHeader
                val subCategoryHeaderContainer = View.inflate(context , R.layout.card_list_recycler_header, null)
                binding.linearLayout.addView(subCategoryHeaderContainer)
                val subCategoryHeader = (subCategoryHeaderContainer as LinearLayout).getChildAt(0) as TextView
                subCategoryHeader.text = subCategory.subCategoryName
                subCategoryHeader.setOnClickListener { onClickSubCategoryHeader(subCategory) }

                // insert a cardListRecycler
                val cardListRecyclerContainer = View.inflate(context, R.layout.card_list_recycler, null)
                binding.linearLayout.addView(cardListRecyclerContainer)
                val cardListRecycler = (cardListRecyclerContainer as LinearLayout).getChildAt(0) as RecyclerView
                val cardListAdapter = CardListAdapter { card -> onClickCard(card) }
                cardListRecycler.adapter = cardListAdapter
                cardListRecycler.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                cardListAdapter.submitList(subCategory.cardList)
            }
        }
    }

    private fun onClickSubCategoryHeader(subCategory: SubCategory) {
        Log.d(TAG, "onClickSubCategoryHeader: subCategoryId=${subCategory.subCategoryId}, subCategoryName=${subCategory.subCategoryName}")
        Toast.makeText(requireContext(), "subCategoryHeader clicked. \nsubCategoryId=${subCategory.subCategoryId}, subCategoryName=${subCategory.subCategoryName}", Toast.LENGTH_SHORT).show()
    }

    private fun onClickCard(card: Card) {
        Log.d(TAG, "onClickCard: card=$card")
        Toast.makeText(requireContext(), "card clicked. \ncard=$card", Toast.LENGTH_SHORT).show()
    }

}