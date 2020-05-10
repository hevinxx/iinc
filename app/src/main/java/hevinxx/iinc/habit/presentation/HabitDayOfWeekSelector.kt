package hevinxx.iinc.habit.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hevinxx.iinc.R
import kotlinx.android.synthetic.main.dow_button.view.*

class HabitDowAdapter(
    private val viewModel: EditHabitViewModel
) : RecyclerView.Adapter<HabitDowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitDowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dow_button, parent, false)
        return HabitDowViewHolder(view)
    }

    override fun getItemCount() = viewModel.dows.size

    override fun onBindViewHolder(holder: HabitDowViewHolder, position: Int) {
        val dow = viewModel.dows[position]
        holder.itemView.setOnClickListener {
            viewModel.turnOnOrOffDow(dow)
            notifyItemChanged(position)
        }
        holder.setCheck(viewModel.dowsSelection.contains(dow))
        holder.setInitial(dow.getInitialId())
    }

}

class HabitDowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun setCheck(check: Boolean) {
        itemView.check.visibility = if (check) View.VISIBLE else View.INVISIBLE
    }
    fun setInitial(initialId: Int) {
        itemView.dow.text = itemView.context.getText(initialId)
    }
}