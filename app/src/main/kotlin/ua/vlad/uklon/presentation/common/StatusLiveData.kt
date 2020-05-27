package ua.vlad.uklon.presentation.common

import androidx.lifecycle.MutableLiveData

class StatusLiveData<T : Any>() : MutableLiveData<Status<T>>() {

    constructor(value: T?) : this() {
        if (value == null) return
        this.value = Status.Success(value)
    }

    fun isLoading(): Boolean {
        return value is Status.Loading
    }

    fun isLoadingOrSuccess(): Boolean {
        return value is Status.Loading || value is Status.Success<*>
    }

}