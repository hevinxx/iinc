package hevinxx.iinc.habit.data

import hevinxx.iinc.habit.Habit
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

class HabitRepository(
    private val localDataSource: HabitDataSource,
    private val remoteDataSource: HabitDataSource
) : HabitDataSource {
    override fun createNewHabit(newHabit: Habit): Completable {
        return localDataSource.createNewHabit(newHabit)
    }

    override fun getHabitsByDate(date: Date): Single<List<Habit>> {
        return localDataSource.getHabitsByDate(date)
    }
}