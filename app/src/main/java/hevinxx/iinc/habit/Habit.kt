package hevinxx.iinc.habit

import java.util.*

data class Habit(
    var title: String,
    var color: Int,
    var daysOfWeek: MutableList<Boolean>, // [sunday, monday, ...]
    var achievementGrade: Int,
    var startDate: Date,
    var endDate: Date,
    var needToAlarm: Boolean
)