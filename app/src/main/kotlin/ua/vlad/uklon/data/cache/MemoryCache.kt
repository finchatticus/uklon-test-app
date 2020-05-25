package ua.vlad.uklon.data.cache

class MemoryCache<K, V> {

    private val lock = Any()
    private var item: MutableMap<K?, V> = mutableMapOf()

    fun get(key: K? = null): V {
        synchronized(lock) {
            return item.getValue(key)
        }
    }

    fun put(value: V, key: K? = null) {
        synchronized(lock) {
            this.item.put(key, value)
        }
    }

}