package hevinxx.iinc.habit.data

import hevinxx.iinc.habit.Habit
import io.reactivex.Completable

class HabitRepository(
    private val localDataSource: HabitDataSource,
    private val remoteDataSource: HabitDataSource
) : HabitDataSource {
    override fun postNewHabit(newHabit: Habit): Completable {
        return remoteDataSource.postNewHabit(newHabit)
    }
}