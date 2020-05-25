package ua.vlad.uklon.domain.model

data class Post(
    val id: Int,
    val idUser: Int,
    val title: String,
    val body: String
)