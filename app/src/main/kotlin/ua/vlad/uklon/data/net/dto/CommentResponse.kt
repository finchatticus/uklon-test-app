package ua.vlad.uklon.data.net.dto

class CommentResponse(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)