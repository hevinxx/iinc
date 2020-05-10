package hevinxx.iinc.record.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

@Dao
interface RecordDao {
    @Query("SELECT * FROM records WHERE date >= :from AND date <= :until AND habitId IN (:habitIds)")
    fun getRecordsOf(habitIds: IntArray, from: Date, until: Date): Single<List<RecordRoomDbEntity>>

    @Query("SELECT * FROM records WHERE date >= :from AND date <= :until")
    fun getRecordsBetween(from: Date, until: Date): Single<List<RecordRoomDbEntity>>

    @Insert
    fun insertAll(vararg records: RecordRoomDbEntity): Completable

    @Delete
    fun delete(record: RecordRoomDbEntity): Completable
}