package ua.vlad.uklon.data.source

import io.reactivex.rxjava3.core.Observable
import ua.vlad.uklon.domain.model.Post

interface PostDataSource {

    fun getPosts(): Observable<List<Post>>

    fun put(posts: List<Post>)

}