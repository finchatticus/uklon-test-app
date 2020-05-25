package ua.vlad.uklon.domain.repository

import io.reactivex.rxjava3.core.Observable
import ua.vlad.uklon.domain.model.Comment

interface CommentRepository {

    fun getComments(idPost: Int): Observable<List<Comment>>

    fun refreshComments(idPost: Int): Observable<List<Comment>>

}