package hevinxx.iinc.habit.presentation

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import hevinxx.iinc.R
import hevinxx.iinc.databinding.ActivityEditHabitBinding
import hevinxx.iinc.habit.data.DayOfWeek
import kotlinx.android.synthetic.main.activity_edit_habit.*
import org.koin.android.ext.android.inject
import java.util.*

class NewHabitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditHabitBinding
    private val viewModel: NewHabitViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_habit)
        binding.vm = viewModel
        binding.lifecycleOwner = this

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
        setGradeSpinner()
        setStartDatePicker()
        setEndDatePicker()
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

    private fun setGradeSpinner() {
        binding.gradeSpinner.adapter = ArrayAdapter(this, R.layout.item_drop_down, viewModel.grades)
        binding.gradeSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.achievementGrade = position
            }

        }
    }

    private fun setStartDatePicker() {
        val today = Calendar.getInstance()
        val todayYear = today.get(Calendar.YEAR)
        val todayMonth = today.get(Calendar.MONTH)
        val todayDayOfMonth = today.get(Calendar.DAY_OF_MONTH)
        val listener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            viewModel.setStartDate(year, month, dayOfMonth)
        }
        val datePicker = DatePickerDialog(this, listener, todayYear, todayMonth, todayDayOfMonth)
        start_date.setOnClickListener { datePicker.show() }
    }

    private fun setEndDatePicker() {
        val today = Calendar.getInstance()
        val todayYear = today.get(Calendar.YEAR)
        val todayMonth = today.get(Calendar.MONTH)
        val todayDayOfMonth = today.get(Calendar.DAY_OF_MONTH)
        val listener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            viewModel.setEndDate(year, month, dayOfMonth)
        }
        val datePicker = DatePickerDialog(this, listener, todayYear, todayMonth, todayDayOfMonth)
        datePicker.setButton(DatePickerDialog.BUTTON_NEUTRAL, getString(R.string.no_choice)) { dialog, _ ->
            viewModel.setEndDate(null)
            dialog.dismiss()
        }
        end_date.setOnClickListener { datePicker.show() }
    }
}