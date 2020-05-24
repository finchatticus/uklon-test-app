package ua.vlad.uklon.presentation.common

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<T : ViewModel>(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId), BaseView {

    private val containerActivity: BaseView?
        get() = when(activity) {
            is BaseView -> activity as BaseView
            else -> null
        }

    protected abstract val viewModel: T

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeLiveData()
    }

    @CallSuper
    override fun onDestroyView() {
        unSubscribeLiveData()
        super.onDestroyView()
    }

    abstract fun subscribeLiveData()

    abstract fun unSubscribeLiveData()

    override fun showLoading() {
        containerActivity?.showLoading()
    }

    override fun hideLoading() {
        containerActivity?.hideLoading()
    }

    override fun showError(e: Throwable, onErrorActionClicked: OnErrorActionClicked?) {
        containerActivity?.showError(e, onErrorActionClicked)
    }

    override fun hideError() {
        containerActivity?.hideError()
    }

}
