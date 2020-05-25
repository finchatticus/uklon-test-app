package ua.vlad.uklon.presentation.view.comments.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_comment.view.*
import ua.vlad.uklon.R
import ua.vlad.uklon.domain.model.Comment

class CommentViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(comment: Comment) = containerView.run {
        view_holder_comment_tv_user.text = resources.getString(R.string.comments_user, comment.name, comment.email)
        view_holder_comment_tv_body.text = comment.body
    }

}