package hevinxx.iinc.habit.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import hevinxx.iinc.R
import hevinxx.iinc.databinding.ActivityEditHabitBinding
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

    private fun setAdapters() {
        val habitColorSelectAdapter = HabitColorSelectAdapter()
        binding.colorSelector.adapter = habitColorSelectAdapter
    }

    private fun showToast(message: String?) {
        message?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
    }
}