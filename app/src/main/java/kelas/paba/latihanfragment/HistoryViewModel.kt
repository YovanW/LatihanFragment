package kelas.paba.latihanfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistoryViewModel : ViewModel() {

    private val data = mapOf(
        "+" to MutableLiveData<List<String>>(emptyList()),
        "-" to MutableLiveData<List<String>>(emptyList()),
        "*" to MutableLiveData<List<String>>(emptyList()),
        "/" to MutableLiveData<List<String>>(emptyList())
    )

    fun history(op: String): LiveData<List<String>> = data.getValue(op)

    fun add(op: String, entry: String) {
        val live = data.getValue(op)
        live.value = (live.value ?: emptyList()) + entry
    }
}