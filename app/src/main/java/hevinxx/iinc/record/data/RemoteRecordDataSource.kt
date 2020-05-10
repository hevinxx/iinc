package hevinxx.iinc.record.data

import hevinxx.iinc.record.Record
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

class RemoteRecordDataSource : RecordDataSource {
    override fun saveRecord(record: Record): Completable {
        TODO("Not yet implemented")
    }

    override fun getRecordsBetween(from: Date, until: Date): Single<List<Record>> {
        TODO("Not yet implemented")
    }

    override fun getRecordsOf(habitIds: IntArray, from: Date, until: Date): Single<List<Record>> {
        TODO("Not yet implemented")
    }
}