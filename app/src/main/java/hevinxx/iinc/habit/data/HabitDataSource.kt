package hevinxx.iinc.habit.data

import hevinxx.iinc.habit.Habit
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

interface HabitDataSource {
    fun createNewHabit(newHabit: Habit): Completable
    fun getHabitsByDate(date: Date): Single<List<Habit>>
}