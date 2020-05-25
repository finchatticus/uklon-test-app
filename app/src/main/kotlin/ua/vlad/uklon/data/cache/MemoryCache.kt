package ua.vlad.uklon.data.cache

import io.reactivex.rxjava3.core.Observable

class MemoryCache<T> {

    private val lock = Any()
    private var item: T? = null

    fun get(): Observable<T> {
        synchronized(lock) {
            return when (val immutableItem = item) {
                null -> Observable.error(NullPointerException())
                else -> Observable.just(immutableItem)
            }
        }
    }

    fun put(item: T) {
        synchronized(lock) {
            this.item = item
        }
    }

}