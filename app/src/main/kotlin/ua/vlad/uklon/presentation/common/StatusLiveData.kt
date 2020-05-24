package ua.vlad.uklon.presentation.common

import androidx.lifecycle.MutableLiveData

class StatusLiveData<T : Any> : MutableLiveData<Status<T>>()