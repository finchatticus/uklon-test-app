package ua.vlad.uklon.presentation.view.container

import androidx.appcompat.app.AppCompatActivity
import ua.vlad.uklon.R
import ua.vlad.uklon.presentation.view.comments.CommentsFragment
import ua.vlad.uklon.presentation.view.posts.PostsFragment

class ContainerRouter(activity: AppCompatActivity) : Router {

    private val fragmentManager = activity.supportFragmentManager

    override fun openCommentsScreen() {
        fragmentManager.beginTransaction()
            .add(R.id.fragment_container, PostsFragment.newInstance())
            .commit()
    }

    override fun openCommentsScreen(idPost: Int) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CommentsFragment.newInstance(idPost))
            .addToBackStack(null)
            .commit()
    }

}