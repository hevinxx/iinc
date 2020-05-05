package hevinxx.iinc.habit

import hevinxx.iinc.util.Dow
import java.util.*

data class Habit(
    var title: String,
    var color: Int,
    var dows: Set<Dow>,
    var achievementGrade: Int,
    var startDate: Date,
    var endDate: Date?
)