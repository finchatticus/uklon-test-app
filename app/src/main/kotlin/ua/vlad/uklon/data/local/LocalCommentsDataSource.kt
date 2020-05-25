package ua.vlad.uklon.data.local

import io.reactivex.rxjava3.core.Observable
import ua.vlad.uklon.data.cache.MemoryCache
import ua.vlad.uklon.data.source.CommentDataSource
import ua.vlad.uklon.domain.model.Comment

class LocalCommentsDataSource(private val commentCache: MemoryCache<List<Comment>>) : CommentDataSource {

    override fun getComments(idPost: Int): Observable<List<Comment>> {
        return this.commentCache.get()
            .flatMap { comments -> Observable.fromIterable(comments) }
            .filter { comment -> comment.idPost == idPost }
            .toList()
            .toObservable()
    }

}