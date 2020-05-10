package hevinxx.iinc.today

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hevinxx.iinc.databinding.ItemTodayHabitBinding
import hevinxx.iinc.habit.Habit
import hevinxx.iinc.record.Record

class TodayHabitsAdapter : RecyclerView.Adapter<TodayHabitViewHolder>() {

    private var habitAndRecords: List<Pair<Habit, Record?>> = listOf()
    fun replaceHabitAndRecords(habitAndRecords: List<Pair<Habit,Record?>>) {
        this.habitAndRecords = habitAndRecords
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayHabitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTodayHabitBinding.inflate(inflater, parent, false)
        return TodayHabitViewHolder(binding)
    }

    override fun getItemCount(): Int = habitAndRecords.size

    override fun onBindViewHolder(holder: TodayHabitViewHolder, position: Int) {
        holder.setHabitAndRecord(habitAndRecords[position])
    }
}

class TodayHabitViewHolder(private val binding: ItemTodayHabitBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setHabitAndRecord(habitAndRecord: Pair<Habit, Record?>) {
        binding.habit = habitAndRecord.first
        binding.record = habitAndRecord.second
    }
}