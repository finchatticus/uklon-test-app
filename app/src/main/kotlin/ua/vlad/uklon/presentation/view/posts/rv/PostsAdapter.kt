package ua.vlad.uklon.presentation.view.posts.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ua.vlad.uklon.R
import ua.vlad.uklon.presentation.model.PostVO

typealias OnPostClicked = (post: PostVO) -> Unit

class PostsAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private var posts = listOf<PostVO>()
    var onPostClicked: OnPostClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_post, parent, false)
        return PostViewHolder(v)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position], onPostClicked)
    }

    override fun getItemCount() = posts.size

    fun addPosts(posts: List<PostVO>) {
        this.posts = posts
        notifyDataSetChanged()
    }

}