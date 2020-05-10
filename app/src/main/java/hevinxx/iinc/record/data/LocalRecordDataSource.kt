package hevinxx.iinc.record.data

import hevinxx.iinc.AppDatabase
import hevinxx.iinc.record.Record
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

class LocalRecordDataSource(private val appDatabase: AppDatabase) : RecordDataSource {
    override fun saveRecord(record: Record): Completable {
        return appDatabase.recordDao().insertAll(record.toRoomDbEntity())
    }

    override fun getRecordsBetween(from: Date, until: Date): Single<List<Record>> {
        return appDatabase.recordDao().getRecordsBetween(from, until)
            .map { list -> list.map { it.toDomainEntity() } }
    }

    override fun getRecordsOf(habitIds: IntArray, from: Date, until: Date): Single<List<Record>> {
        return appDatabase.recordDao().getRecordsOf(habitIds, from, until)
            .map { list -> list.map { it.toDomainEntity() } }
    }
}