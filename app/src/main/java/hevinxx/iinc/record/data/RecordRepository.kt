package hevinxx.iinc.record.data

import hevinxx.iinc.record.Record
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*

class RecordRepository(
    private val localRecordDataSource: RecordDataSource,
    private val remoteRecordDataSource: RecordDataSource
): RecordDataSource {
    override fun saveRecord(record: Record): Completable {
        // TODO
        return localRecordDataSource.saveRecord(record)
            .subscribeOn(Schedulers.io())
    }

    override fun getRecordsBetween(from: Date, until: Date): Single<List<Record>> {
        // TODO
        return localRecordDataSource.getRecordsBetween(from, until)
            .subscribeOn(Schedulers.io())
    }

    override fun getRecordsOf(habitIds: IntArray, from: Date, until: Date): Single<List<Record>> {
        // TODO
        return localRecordDataSource.getRecordsOf(habitIds, from, until)
            .subscribeOn(Schedulers.io())
    }
}