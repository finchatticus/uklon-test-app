package ua.vlad.uklon.data.net.source

import io.reactivex.rxjava3.core.Observable
import ua.vlad.uklon.data.net.api.ApiService
import ua.vlad.uklon.data.net.mapper.PostResponseToPostMapper
import ua.vlad.uklon.data.source.PostDataSource
import ua.vlad.uklon.domain.model.Post
import java.lang.UnsupportedOperationException

class RemotePostDataSource(private val apiService: ApiService) : PostDataSource {

    override fun getPosts(): Observable<List<Post>> {
        return apiService.getPosts()
            .map(PostResponseToPostMapper())
    }

    override fun put(posts: List<Post>) {
        throw UnsupportedOperationException()
    }

}