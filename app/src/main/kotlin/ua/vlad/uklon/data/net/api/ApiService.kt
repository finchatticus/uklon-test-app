package ua.vlad.uklon.data.net.api

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import ua.vlad.uklon.data.net.dto.PostResponse

interface ApiService {

    @GET("/posts")
    fun getPosts(): Observable<List<PostResponse>>

}