package hevinxx.iinc.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import hevinxx.iinc.databinding.FragmentTodayBinding
import org.koin.android.ext.android.inject

class TodayFragment : Fragment() {

    private lateinit var binding: FragmentTodayBinding
    private val viewModel: TodayViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodayBinding.inflate(inflater, container, false).apply {
            vm = viewModel
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.initTodayHabitsAndRecords()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this.viewLifecycleOwner
        setTodayHabitsAdapter()
    }

    private fun setTodayHabitsAdapter() {
        val adapter = TodayHabitsAdapter()
        binding.todayHabits.adapter = adapter
        viewModel.todayHabitAndRecords.observe(this.viewLifecycleOwner, Observer {
            adapter.replaceHabitAndRecords(it)
        })
    }
}