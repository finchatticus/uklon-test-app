package ua.vlad.uklon.data.net.source

import io.reactivex.rxjava3.core.Observable
import ua.vlad.uklon.data.net.api.ApiService
import ua.vlad.uklon.data.net.mapper.CommentResponseToCommentMapper
import ua.vlad.uklon.data.source.CommentDataSource
import ua.vlad.uklon.domain.model.Comment

class RemoteCommentDataSource(private val apiService: ApiService) : CommentDataSource {

    override fun getComments(idPost: Int): Observable<List<Comment>> {
        return apiService.getPostComments(idPost)
            .map(CommentResponseToCommentMapper())
    }

}