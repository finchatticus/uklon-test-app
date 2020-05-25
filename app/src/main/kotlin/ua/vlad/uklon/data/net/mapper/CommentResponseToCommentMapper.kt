package ua.vlad.uklon.data.net.mapper

import io.reactivex.rxjava3.functions.Function
import ua.vlad.uklon.data.net.dto.CommentResponse
import ua.vlad.uklon.domain.model.Comment

class CommentResponseToCommentMapper : Function<List<CommentResponse>, List<Comment>> {

    override fun apply(t: List<CommentResponse>): List<Comment> {
        return t.map {
            Comment(it.id, it.postId, it.name, it.email, it.body)
        }
    }

}