package ua.vlad.uklon.domain.repository

import io.reactivex.rxjava3.core.Observable
import ua.vlad.uklon.domain.model.Post

interface PostRepository {

    fun getPosts(): Observable<List<Post>>

    fun refreshPosts(): Observable<List<Post>>

}