package ua.vlad.uklon.presentation.common

import androidx.lifecycle.MutableLiveData

class StatusLiveData<T : Any> : MutableLiveData<Status<T>>() {

    fun isLoading(): Boolean {
        return value is Status.Loading
    }

}