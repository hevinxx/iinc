package hevinxx.iinc

import android.content.Context
import java.text.SimpleDateFormat

interface ResourceDataSource {
    fun getString(id: Int): String
    fun getDateFormat(): SimpleDateFormat
}

class ResourceDataSourceImpl(
    private val context: Context
)  : ResourceDataSource {
    override fun getString(id: Int): String {
        return context.getString(id)
    }

    override fun getDateFormat(): SimpleDateFormat {
        val format = getString(R.string.date_format)
        return SimpleDateFormat(format)
    }
}