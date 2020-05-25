package ua.vlad.uklon.presentation.view.comments.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ua.vlad.uklon.R
import ua.vlad.uklon.domain.model.Comment

class CommentsAdapter : RecyclerView.Adapter<CommentViewHolder>() {

    private var comments = listOf<Comment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_comment, parent, false)
        return CommentViewHolder(v)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    override fun getItemCount() = comments.size

    fun addComments(comments: List<Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }

}