package ua.vlad.uklon.domain.usecase

import ua.vlad.uklon.domain.repository.PostRepository

class GetPostsUseCase(private val postRepository: PostRepository) {

    fun getAll() = postRepository.getPosts()

}