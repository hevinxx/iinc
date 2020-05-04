package hevinxx.iinc.habit.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hevinxx.iinc.R
import kotlinx.android.synthetic.main.dow_button.view.*

class HabitDayOfWeekAdapter(
    private val viewModel: EditHabitViewModel
) : RecyclerView.Adapter<HabitDayOfWeekViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitDayOfWeekViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dow_button, parent, false)
        return HabitDayOfWeekViewHolder(view)
    }

    override fun getItemCount() = viewModel.daysOfWeek.size

    override fun onBindViewHolder(holder: HabitDayOfWeekViewHolder, position: Int) {
        val dow = viewModel.daysOfWeek[position]
        holder.itemView.setOnClickListener {
            viewModel.turnOnOrOffDayOfWeek(dow)
            notifyItemChanged(position)
        }
        holder.setCheck(viewModel.daysOfWeekSelection.contains(dow))
        holder.setInitial(dow.getInitialId())
    }

}

class HabitDayOfWeekViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun setCheck(check: Boolean) {
        itemView.check.visibility = if (check) View.VISIBLE else View.INVISIBLE
    }
    fun setInitial(initialId: Int) {
        itemView.dow.text = itemView.context.getText(initialId)
    }
}