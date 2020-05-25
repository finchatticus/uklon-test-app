package ua.vlad.uklon.data.cache

import io.reactivex.rxjava3.core.Observable
import ua.vlad.uklon.data.NoInternetConnectionException

class MemoryCache<K, V> {

    private val lock = Any()
    private var item: MutableMap<K?, V> = mutableMapOf()

    fun get(key: K? = null): Observable<V> {
        synchronized(lock) {
            return try {
                Observable.just(item.getValue(key))
            } catch (e: NoSuchElementException) {
                Observable.error(NoInternetConnectionException())
            }
        }
    }

    fun put(value: V, key: K? = null) {
        synchronized(lock) {
            this.item.put(key, value)
        }
    }

}