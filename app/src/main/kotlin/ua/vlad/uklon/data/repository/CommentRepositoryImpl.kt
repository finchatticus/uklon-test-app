package ua.vlad.uklon.data.repository

import io.reactivex.rxjava3.core.Observable
import ua.vlad.uklon.data.NoInternetConnectionException
import ua.vlad.uklon.data.cache.MemoryCache
import ua.vlad.uklon.data.platform.checkInternetConnection
import ua.vlad.uklon.data.source.CommentDataSource
import ua.vlad.uklon.domain.model.Comment
import ua.vlad.uklon.domain.repository.CommentRepository

class CommentRepositoryImpl(
    private val remoteCommentDataSource: CommentDataSource,
    private val commentsCache: MemoryCache<List<Comment>>,
    private val localCommentDataSource: CommentDataSource
) : CommentRepository {

    override fun getComments(idPost: Int): Observable<List<Comment>> = checkInternetConnection(
        onEnabled = {
            getCommentsFromRemoteDataSource(idPost)
        },
        onDisabled = {
            localCommentDataSource
                .getComments(idPost)
                .doOnNext {
                    if (it.isEmpty())
                        throw NoInternetConnectionException()
                }
        })

    override fun refreshComments(idPost: Int): Observable<List<Comment>> = checkInternetConnection(
        onEnabled = {
            getCommentsFromRemoteDataSource(idPost)
        },
        onDisabled = {
            Observable.error(NoInternetConnectionException())
        })

    private fun getCommentsFromRemoteDataSource(idPost: Int) = remoteCommentDataSource
        .getComments(idPost)
        .doOnNext {
            commentsCache.put(it)
        }

}