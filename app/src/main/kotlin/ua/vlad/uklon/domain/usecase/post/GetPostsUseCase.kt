package ua.vlad.uklon.domain.usecase.post

import ua.vlad.uklon.domain.repository.PostRepository

class GetPostsUseCase(private val postRepository: PostRepository) {

    fun getAll() = postRepository.getPosts()

}