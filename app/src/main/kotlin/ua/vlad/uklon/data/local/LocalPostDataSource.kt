package ua.vlad.uklon.data.local

import ua.vlad.uklon.data.cache.MemoryCache
import ua.vlad.uklon.data.source.PostDataSource
import ua.vlad.uklon.domain.model.Post

class LocalPostDataSource(private val cache: MemoryCache<List<Post>>) : PostDataSource {

    override fun getPosts() = this.cache.get()

}