package ua.vlad.uklon.domain.usecase.comment

import ua.vlad.uklon.domain.repository.CommentRepository

class GetCommentsUseCase(private val commentRepository: CommentRepository) {

    fun getByIdPost(idPost: Int) = commentRepository.getComments(idPost)

}