package hevinxx.iinc

import android.content.Context

class ResourceDataSource(
    private val context: Context
) {
    fun getString(id: Int): String {
        return context.getString(id)
    }
}