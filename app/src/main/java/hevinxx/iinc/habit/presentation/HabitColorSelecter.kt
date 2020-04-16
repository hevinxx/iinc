package hevinxx.iinc.habit.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import hevinxx.iinc.R
import kotlinx.android.synthetic.main.color_button.view.*

class HabitColorSelectAdapter : RecyclerView.Adapter<HabitColorSelectViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitColorSelectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.color_button, parent, false)
        return HabitColorSelectViewHolder(view)
    }

    override fun getItemCount() = HabitColor.values().size

    override fun onBindViewHolder(holder: HabitColorSelectViewHolder, position: Int) {

    }

}

class HabitColorSelectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun setCheck(check: Boolean) {
        itemView.check.visibility = if (check) View.VISIBLE else View.INVISIBLE
    }
    fun setColor(colorId: Int) {
        itemView.circle.setColorFilter(
            ContextCompat.getColor(itemView.context, colorId)
        )
    }
}

enum class HabitColor {

}