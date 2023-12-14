package com.example.myapplication.ui.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


class PagerAdapter (
    private val myFragments: MutableList<Fragment>  ,
    private val myFragmentTitles: MutableList<String>  ,
    private var context: Context ,
){

    fun addFragment(fragment: Fragment, title: String) {
        myFragments.add(fragment)
        myFragmentTitles.add(title)
    }

    fun getItem(position: Int): Fragment? {
        return myFragments[position]
    }

    fun getCount(): Int {
        return myFragments.size
    }

    fun getPageTitle(position: Int): CharSequence? {
        return myFragmentTitles[position]
    }
}