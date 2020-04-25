package hevinxx.iinc.habit.data

import android.content.Context
import androidx.core.content.ContextCompat
import hevinxx.iinc.R

enum class HabitColor(val colorId: Int) {
    RED(R.color.red),
    ORANGE(R.color.orange),
    YELLOW(R.color.yellow),
    LIGHT_GREEN(R.color.lightGreen),
    LIGHT_BLUE(R.color.lightBlue),
    COBALT_BLUE(R.color.cobaltBlue),
    DARK_BLUE(R.color.darkBlue),
    PURPLE(R.color.purple);

    fun getColor(context: Context): Int {
        return ContextCompat.getColor(context, colorId)
    }
}