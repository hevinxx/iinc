package hevinxx.iinc.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import hevinxx.iinc.today.TodayFragment

enum class TABS {
    TODAY,
    MONTHLY,
    HISTORY,
    STATISTICS
}

class SectionsPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount() = TABS.values().size

    override fun createFragment(position: Int): Fragment {
        return when(TABS.values()[position]) {
            TABS.TODAY -> TodayFragment()
            TABS.MONTHLY -> MonthlyFragment()
            TABS.HISTORY -> HistoryFragment()
            TABS.STATISTICS -> StatisticsFragment()
        }
    }
}