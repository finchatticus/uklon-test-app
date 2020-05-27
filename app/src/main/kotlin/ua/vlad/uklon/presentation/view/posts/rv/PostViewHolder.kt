package ua.vlad.uklon.presentation.view.posts.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_post.view.*
import ua.vlad.uklon.presentation.model.PostVO

class PostViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun bind(post: PostVO, onPostClicked: OnPostClicked? = null) = containerView.run {
        setOnClickListener {
            onPostClicked?.invoke(post)
        }

        view_holder_post_tv_title.text = post.title
        view_holder_post_tv_body.text = post.body
    }

}