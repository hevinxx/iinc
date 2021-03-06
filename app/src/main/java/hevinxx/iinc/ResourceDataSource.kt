package hevinxx.iinc

import android.content.Context
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat

interface ResourceDataSource {
    fun getString(id: Int): String
    fun getStringArray(id: Int): Array<String>
    fun getColor(id: Int): Int
    fun getDateFormat(): SimpleDateFormat
}

open class ResourceDataSourceImpl(
    private val context: Context
)  : ResourceDataSource {
    override fun getString(id: Int): String {
        return context.getString(id)
    }

    override fun getStringArray(id: Int): Array<String> {
        return context.resources.getStringArray(id)
    }

    override fun getColor(id: Int): Int {
        return ContextCompat.getColor(context, id)
    }

    override fun getDateFormat(): SimpleDateFormat {
        val format = getString(R.string.date_format)
        return SimpleDateFormat(format)
    }
}