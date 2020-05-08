package hevinxx.iinc.util

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<MutableList<T>>.set(index: Int, element: T) {
    val mutableList = this.value
    mutableList?.set(index, element)
    this.value = mutableList
}