package hevinxx.iinc.habit.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "habits")
data class HabitRoomDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val color: Int,
    val daysOfWeek: Set<DayOfWeek>,
    val achievementGrade: Int,
    val startDate: Date,
    val endDate: Date?
)