package ua.vlad.uklon.data.local

import io.reactivex.rxjava3.core.Observable
import ua.vlad.uklon.data.cache.MemoryCache
import ua.vlad.uklon.data.source.CommentDataSource
import ua.vlad.uklon.domain.model.Comment

class LocalCommentsDataSource(private val commentCache: MemoryCache<Int, List<Comment>>) : CommentDataSource {

    override fun getComments(idPost: Int): Observable<List<Comment>>
            = this.commentCache.getObservableDataOrNoInternetExceptionError(idPost)

}