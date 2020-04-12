package hevinxx.iinc.habit.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import hevinxx.iinc.R
import hevinxx.iinc.databinding.ActivityNewHabitBinding
import org.koin.android.ext.android.inject

class NewHabitActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewHabitBinding
    val viewModel: NewHabitViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_habit)
        binding.vm = viewModel
        handleErrorMessage()
    }

    private fun handleErrorMessage() {
        viewModel.notFilledEnoughError.observe(this, Observer {
            showToast(it.getContentIfNotHandledOrReturnNull())
        })
    }

    private fun showToast(message: String?) {
        message?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
    }
}