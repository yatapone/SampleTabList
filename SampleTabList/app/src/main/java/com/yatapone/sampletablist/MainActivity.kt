package com.yatapone.sampletablist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yatapone.sampletablist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
        var tabCategoryNum: Int = 0
        lateinit var allCards: AllCards
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        allCards = AllCards().loadAllData()

        /*
        allCards.tabCategoryList.forEach { tabCategory ->
            Log.d(TAG, "tabCategory: tabCategoryId=${tabCategory.tabCategoryId}, tabCategoryName=${tabCategory.tabCategoryName}")
            tabCategory.subCategoryList.forEach { subCategory ->
                Log.d(TAG, "subCategory: subCategoryId=${subCategory.subCategoryId}, subCategoryName=${subCategory.subCategoryName}")
                subCategory.cardList.forEach { card ->
                    Log.d(TAG, "card: cardId=${card.cardId}, cardTitle=${card.cardTitle}")
                }
            }
        }
        */

        buildUi(allCards)
    }

    private fun buildUi(allCards: AllCards) {
        val tabTitles: ArrayList<String> = ArrayList()
        tabCategoryNum = 0
        allCards.tabCategoryList.forEach {
            tabTitles.add(it.tabCategoryName)
            tabCategoryNum++
        }
        Log.d(TAG, "buildUi: tabCategoryNum=$tabCategoryNum, tabTitles=$tabTitles")

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        viewPager.adapter = TabViewPagerAdapter(supportFragmentManager, tabTitles)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        Log.d(TAG, "onPause: ")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }
}