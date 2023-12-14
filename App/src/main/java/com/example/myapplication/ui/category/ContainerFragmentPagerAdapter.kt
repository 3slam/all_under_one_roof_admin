package com.example.myapplication.ui.category

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.data.model.Category
import com.example.myapplication.ui.category.container.ContainerFragment

class ContainerFragmentPagerAdapter(
    fragmentManager: FragmentManager,
    private val categories: List<Category> ,
    private val apiToken :String
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return ContainerFragment.newInstance(categories[position],apiToken)
    }

    override fun getCount(): Int {
        return categories.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return categories[position].name
    }
}
