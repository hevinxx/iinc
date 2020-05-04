package hevinxx.iinc.habit.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits WHERE id IN (:habitIds)")
    fun getHabitsByIds(habitIds: IntArray): Single<List<HabitRoomDbEntity>>

    @Query("SELECT * FROM habits WHERE (:date >= startDate) AND (endDate ISNULL OR :date <= endDate)")
    fun getHabitsByDate(date: Date): Single<List<HabitRoomDbEntity>>

    @Insert
    fun insertAll(vararg habits: HabitRoomDbEntity): Completable

    @Delete
    fun delete(habit: HabitRoomDbEntity): Completable
}