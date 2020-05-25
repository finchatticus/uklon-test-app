package ua.vlad.uklon

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import ua.vlad.uklon.data.cache.MemoryCache

class MemoryCacheTest {

    @Test
    fun testWhenPutDataThenReadTheSameOn() {
        val key = 1;
        val putData = intArrayOf(0, 1, 2, 3, 4, 5)
        val memoryCache = MemoryCache<Int, IntArray>()
        memoryCache.put(putData, key)

        val getData = memoryCache.get(key)
        assertEquals(putData.size, getData.size)
        assertTrue(putData contentEquals getData)
    }

}