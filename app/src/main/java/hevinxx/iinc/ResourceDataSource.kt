package hevinxx.iinc

import android.content.Context

interface ResourceDataSource {
    fun getString(id: Int): String
}

class ResourceDataSourceImpl(
    private val context: Context
)  : ResourceDataSource {
    override fun getString(id: Int): String {
        return context.getString(id)
    }
}