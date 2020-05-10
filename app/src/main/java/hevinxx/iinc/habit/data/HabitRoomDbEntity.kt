package hevinxx.iinc.habit.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import hevinxx.iinc.habit.Habit
import hevinxx.iinc.util.Dow
import java.util.*

@Entity(tableName = "habits")
data class HabitRoomDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val color: Int,
    val dows: Set<Dow>,
    val achievementGrade: Int,
    val startDate: Date,
    val endDate: Date?
)

fun HabitRoomDbEntity.toDomainEntity(): Habit {
    return Habit(
        title = this.title,
        color = this.color,
        dows = this.dows,
        achievementGrade = this.achievementGrade,
        startDate = this.startDate,
        endDate = this.endDate
    )
}

fun Habit.toRoomDbEntity(): HabitRoomDbEntity {
    return HabitRoomDbEntity(
        title = this.title,
        color = this.color,
        dows = this.dows,
        achievementGrade = this.achievementGrade,
        startDate = this.startDate,
        endDate = this.endDate
    )
}