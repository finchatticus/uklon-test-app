package ua.vlad.uklon.presentation.common

typealias OnErrorActionClicked = () -> Unit

interface BaseView {

    fun showLoading()

    fun hideLoading()

    fun showError(e: Throwable, onErrorActionClicked: OnErrorActionClicked?)

    fun hideError()

}