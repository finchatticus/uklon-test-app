package ua.vlad.uklon.data.repository

import io.reactivex.rxjava3.core.Observable
import ua.vlad.uklon.data.NoInternetConnectionException
import ua.vlad.uklon.data.platform.checkInternetConnection
import ua.vlad.uklon.data.source.PostDataSource
import ua.vlad.uklon.domain.model.Post
import ua.vlad.uklon.domain.repository.PostRepository

class PostRepositoryImpl(private val remotePostDataSource: PostDataSource, private val localPostDataSource: PostDataSource) : PostRepository {

    override fun getPosts(): Observable<List<Post>> = checkInternetConnection(
        onEnabled = {
            remotePostDataSource
                .getPosts()
                .doOnNext {
                    localPostDataSource.put(it)
                }
        },
        onDisabled = {
            localPostDataSource
                .getPosts()
                .doOnNext {
                    if (it.isEmpty())
                        throw NoInternetConnectionException()
                }
        })

    override fun refreshPosts(): Observable<List<Post>> = checkInternetConnection(
        onEnabled = {
            remotePostDataSource
                .getPosts()
                .doOnNext {
                    localPostDataSource.put(it)
                }
        },
        onDisabled = {
            Observable.error(NoInternetConnectionException())
        })

}
