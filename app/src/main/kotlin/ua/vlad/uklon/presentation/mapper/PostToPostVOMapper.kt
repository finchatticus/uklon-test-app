package ua.vlad.uklon.presentation.mapper

import io.reactivex.rxjava3.functions.Function
import ua.vlad.uklon.domain.model.Post
import ua.vlad.uklon.presentation.model.PostVO

class PostToPostVOMapper : Function<List<Post>, List<PostVO>> {

    override fun apply(posts: List<Post>) = posts.map {
        PostVO(it.id, it.idUser, it.title, it.body)
    }

}