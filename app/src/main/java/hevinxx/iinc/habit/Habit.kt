package hevinxx.iinc.habit

import java.util.*

data class Habit(
    var title: String,
    var color: Int,
    var daysOfWeek: MutableSet<Int>,
    var achievementGrade: Int,
    var startDate: Date,
    var endDate: Date,
    var needToAlarm: Boolean
)