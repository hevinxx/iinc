package hevinxx.iinc.habit.data

import hevinxx.iinc.habit.Habit
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*

class HabitRepository(
    private val localDataSource: HabitDataSource,
    private val remoteDataSource: HabitDataSource
) : HabitDataSource {
    override fun createNewHabit(newHabit: Habit): Completable {
        // TODO
        return localDataSource.createNewHabit(newHabit)
            .subscribeOn(Schedulers.io())
    }

    override fun getHabitsByDate(date: Date): Single<List<Habit>> {
        // TODO
        return localDataSource.getHabitsByDate(date)
            .subscribeOn(Schedulers.io())
    }
}