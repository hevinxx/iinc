package hevinxx.iinc.habit.data

import hevinxx.iinc.R

enum class DayOfWeek {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    fun getDatabaseIndex(): Int {
        return when (this) {
            SUNDAY -> 0
            MONDAY -> 1
            TUESDAY -> 2
            WEDNESDAY -> 3
            THURSDAY -> 4
            FRIDAY -> 5
            SATURDAY -> 6
        }
    }

    fun getInitialId(): Int {
        return when (this) {
            SUNDAY -> R.string.sunday_initial
            MONDAY -> R.string.monday_initial
            TUESDAY -> R.string.tuesday_initial
            WEDNESDAY -> R.string.wednesday_initial
            THURSDAY -> R.string.thursday_initial
            FRIDAY -> R.string.friday_initial
            SATURDAY -> R.string.saturday_initial
        }
    }
}