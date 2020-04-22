package hevinxx.iinc.habit.presentation

import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import hevinxx.iinc.R
import hevinxx.iinc.databinding.ActivityEditHabitBinding
import hevinxx.iinc.habit.data.DayOfWeek
import org.koin.android.ext.android.inject

class NewHabitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditHabitBinding
    private val viewModel: NewHabitViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_habit)
        binding.vm = viewModel

        handleErrorMessage()
        setAdapters()
    }

    private fun handleErrorMessage() {
        viewModel.toastMessage.observe(this, Observer {
            showToast(it.peekContent())
        })
    }

    private fun showToast(message: String?) {
        message?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
    }

    private fun setAdapters() {
        setColorSelector()
        setDowSelector()
    }

    private fun setColorSelector() {
        val habitColorSelectAdapter = HabitColorSelectAdapter(viewModel)
        binding.colorSelector.layoutManager = GridLayoutManager(this, viewModel.colors.size)
        binding.colorSelector.adapter = habitColorSelectAdapter
    }

    private fun setDowSelector() {
        val dowSelectAdapter = HabitDayOfWeekAdapter(viewModel)
        binding.dowSelector.layoutManager = GridLayoutManager(this, DayOfWeek.values().size)
        binding.dowSelector.adapter = dowSelectAdapter
    }
}