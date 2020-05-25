package ua.vlad.uklon.data.net.api

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import ua.vlad.uklon.data.net.dto.CommentResponse
import ua.vlad.uklon.data.net.dto.PostResponse

interface ApiService {

    @GET("/posts")
    fun getPosts(): Observable<List<PostResponse>>

    @GET("/posts/{idPost}/comments")
    fun getPostComments(@Path("idPost") idPost: Int): Observable<List<CommentResponse>>

}