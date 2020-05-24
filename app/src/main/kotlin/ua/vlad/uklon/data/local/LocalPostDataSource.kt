package ua.vlad.uklon.data.local

import io.reactivex.rxjava3.core.Observable
import ua.vlad.uklon.data.source.PostDataSource
import ua.vlad.uklon.domain.model.Post

class LocalPostDataSource : PostDataSource {

    private val lock = Any()
    private var posts = listOf<Post>()

    override fun getPosts(): Observable<List<Post>> {
        synchronized(lock) {
            return Observable.just(posts)
        }
    }

    override fun put(posts: List<Post>) {
        synchronized(lock) {
            this.posts = posts
        }
    }

}