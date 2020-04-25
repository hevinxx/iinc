package hevinxx.iinc.habit

import hevinxx.iinc.habit.data.DayOfWeek
import java.util.*

data class Habit(
    var title: String,
    var color: Int,
    var daysOfWeek: Set<DayOfWeek>,
    var achievementGrade: Int,
    var startDate: Date,
    var endDate: Date?
)