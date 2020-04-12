package hevinxx.iinc.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import hevinxx.iinc.R
import hevinxx.iinc.habit.presentation.NewHabitActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTabs()
        setOnClickListeners()
    }

    private fun setTabs() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = "${getTabName(TABS.values()[position])}"
        }.attach()
    }

    private fun setOnClickListeners() {
        create_new_habit.setOnClickListener {
            val intent = Intent(this, NewHabitActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getTabName(tab: TABS): String {
        return when (tab) {
            TABS.TODAY -> getString(R.string.tab_today)
            TABS.MONTHLY -> getString(R.string.tab_monthly)
            TABS.HISTORY -> getString(R.string.tab_history)
            TABS.STATISTICS -> getString(R.string.tab_statistics)
        }
    }
}