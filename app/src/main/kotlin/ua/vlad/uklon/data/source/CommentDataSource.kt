package ua.vlad.uklon.data.source

import io.reactivex.rxjava3.core.Observable
import ua.vlad.uklon.domain.model.Comment

interface CommentDataSource {

    fun getComments(idPost: Int): Observable<List<Comment>>

}