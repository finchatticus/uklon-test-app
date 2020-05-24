package ua.vlad.uklon.presentation.view.container

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_container.*
import ua.vlad.uklon.R
import ua.vlad.uklon.presentation.common.BaseView
import ua.vlad.uklon.presentation.common.OnErrorActionClicked
import ua.vlad.uklon.presentation.view.posts.PostsFragment


class ContainerActivity : AppCompatActivity(R.layout.activity_container), BaseView {

    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, PostsFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun showLoading() {
        container_pb.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        container_pb.visibility = View.GONE
    }

    override fun showError(e: Throwable, onErrorActionClicked: OnErrorActionClicked?) {
        snackbar = Snackbar.make(window.decorView.findViewById(android.R.id.content), R.string.error_unknown, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.error_action_retry) {
                onErrorActionClicked?.invoke()
            }
        snackbar?.show()
    }

    override fun hideError() {
        snackbar?.dismiss()
    }

}
