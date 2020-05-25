package ua.vlad.uklon.data.local

import io.reactivex.rxjava3.core.Observable
import ua.vlad.uklon.data.NoInternetConnectionException
import ua.vlad.uklon.data.cache.MemoryCache

fun <K, V> MemoryCache<K, V>.getObservableDataOrNoInternetExceptionError(key: K? = null): Observable<V> {
    return try {
        val value = this.get(key)
        Observable.just(value)
    } catch (e: NoSuchElementException) {
        Observable.error(NoInternetConnectionException())
    }
}