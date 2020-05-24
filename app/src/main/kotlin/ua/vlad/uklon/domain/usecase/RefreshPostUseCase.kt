package ua.vlad.uklon.domain.usecase

import ua.vlad.uklon.domain.repository.PostRepository

class RefreshPostUseCase(private val postRepository: PostRepository) {

    fun refresh() = postRepository.refreshPosts()

}