package ua.vlad.uklon.domain.model

data class Comment(
    val id: Int,
    val idPost: Int,
    val name: String,
    val email: String,
    val body: String
)