package hevinxx.iinc.habit

import hevinxx.iinc.util.Dow
import java.util.*

data class Habit(
    val id: Int = 0,
    var title: String,
    var color: Int,
    var dows: Set<Dow>,
    var achievementGrade: Int,
    var startDate: Date,
    var endDate: Date?
// TODO: Alarm
)