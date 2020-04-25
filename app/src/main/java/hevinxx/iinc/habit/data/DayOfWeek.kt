package hevinxx.iinc.habit.data

import hevinxx.iinc.R

enum class DayOfWeek(
    val index: Int,
    val initialId: Int
) {
    SUNDAY(0, R.string.sunday_initial),
    MONDAY(1, R.string.monday_initial),
    TUESDAY(2, R.string.tuesday_initial),
    WEDNESDAY(3, R.string.wednesday_initial),
    THURSDAY(4, R.string.thursday_initial),
    FRIDAY(5, R.string.friday_initial),
    SATURDAY(6, R.string.saturday_initial);
}