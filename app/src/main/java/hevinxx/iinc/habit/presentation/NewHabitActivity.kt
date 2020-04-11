package hevinxx.iinc.habit.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject

class NewHabitActivity : AppCompatActivity() {

    val viewModel: NewHabitViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}