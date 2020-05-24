package ua.vlad.uklon.presentation.common

sealed class Status<out T : Any> {

    object Loading : Status<Nothing>()

    class Success<out T : Any>(val data: T) : Status<T>()

    class Error(val exception: Throwable) : Status<Nothing>()

    override fun toString(): String {
        return when(this) {
            is Loading -> "Status.Loading"
            is Success -> "Status.Success: ${this.data}"
            is Error -> "Status.Error: ${this.exception}"
        }
    }

}