package hevinxx.iinc.habit.data

import hevinxx.iinc.habit.Habit
import io.reactivex.Completable

interface HabitDataSource {
    fun postNewHabit(newHabit: Habit): Completable
}