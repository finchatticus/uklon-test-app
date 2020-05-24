package ua.vlad.uklon.data.net.mapper

import io.reactivex.rxjava3.functions.Function
import ua.vlad.uklon.data.net.dto.PostResponse
import ua.vlad.uklon.domain.model.Post

class PostResponseToPostMapper : Function<List<PostResponse>, List<Post>> {

    override fun apply(t: List<PostResponse>): List<Post> {
        return t.map {
            Post(it.id, it.userId, it.title, it.body)
        }
    }

}