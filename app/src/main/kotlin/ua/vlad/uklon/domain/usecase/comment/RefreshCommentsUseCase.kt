package ua.vlad.uklon.domain.usecase.comment

import ua.vlad.uklon.domain.repository.CommentRepository

class RefreshCommentsUseCase(private val commentRepository: CommentRepository) {

    fun refreshByIdPost(idPost: Int) = commentRepository.refreshComments(idPost)

}