package hevinxx.iinc.habit.data

import hevinxx.iinc.AppDatabase
import hevinxx.iinc.habit.Habit
import hevinxx.iinc.util.toDow
import io.reactivex.Completable
import io.reactivex.Single
import java.util.Date

class LocalHabitDataSource(val appDatabase: AppDatabase) : HabitDataSource {
    override fun createNewHabit(newHabit: Habit): Completable {
        return appDatabase.habitDao().insertAll(newHabit.toRoomDbEntity())
    }

    override fun getHabitsByDate(date: Date): Single<List<Habit>> {
        return appDatabase.habitDao().getHabitsByDate(date)
            .map {
                it.filter { checkDow(it, date) }
                    .map { it.toDomainEntity() }
            }
    }

    private fun checkDow(habitRoomDbEntity: HabitRoomDbEntity, date: Date): Boolean {
        val dow = date.toDow()
        return habitRoomDbEntity.dows.contains(dow)
    }
}