package com.maxim.jokesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.maxim.jokesapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        show(JokeFragment(), "jokes")
        val tabChosen: (Boolean) -> Unit = { jokeChosen ->
            if (jokeChosen)
                show(JokeFragment(), "jokes")
            else
                show(QuoteFragment(), "quotes")
        }
        tabLayout.addOnTabSelectedListener(TabListener(tabChosen))
    }

    private fun show(fragment: Fragment, tag: String) {
        if (supportFragmentManager.fragments.isEmpty() || supportFragmentManager.fragments.last().tag != tag)
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment, tag).commit()
    }
}

private class TabListener(private val tabChosen: (Boolean) -> Unit) :
    TabLayout.OnTabSelectedListener {
    override fun onTabSelected(tab: TabLayout.Tab?) = tabChosen.invoke(tab?.position == 0)

    override fun onTabUnselected(tab: TabLayout.Tab?) = Unit

    override fun onTabReselected(tab: TabLayout.Tab?) = Unit
}