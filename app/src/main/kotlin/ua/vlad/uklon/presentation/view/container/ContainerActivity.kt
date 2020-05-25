package ua.vlad.uklon.presentation.view.container

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_container.*
import ua.vlad.uklon.R
import ua.vlad.uklon.data.NoInternetConnectionException
import ua.vlad.uklon.presentation.common.BaseView
import ua.vlad.uklon.presentation.common.OnErrorActionClicked


class ContainerActivity : AppCompatActivity(R.layout.activity_container), BaseView {

    private val router: Router by lazy { ContainerRouter(this) }
    private var snackbarError: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router.openCommentsScreen()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.container_menu, menu)
        return true
    }

    override fun showLoading() {
        container_pb.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        container_pb.visibility = View.GONE
    }

    override fun showError(e: Throwable, onErrorActionClicked: OnErrorActionClicked?) {
        val resId = when (e) {
            is NoInternetConnectionException -> R.string.error_no_internet_connection
            else -> R.string.error_unknown
        }
        snackbarError = Snackbar.make(window.decorView.findViewById(android.R.id.content), resId, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.error_action_retry) {
                onErrorActionClicked?.invoke()
            }
        snackbarError?.show()
    }

    override fun hideError() {
        snackbarError?.dismiss()
    }

}
