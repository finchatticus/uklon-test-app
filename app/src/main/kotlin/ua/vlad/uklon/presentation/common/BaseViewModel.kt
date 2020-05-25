package ua.vlad.uklon.presentation.common

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private var disposables: CompositeDisposable? = null
        get() {
            if (field == null || field!!.isDisposed) {
                field = CompositeDisposable()
            }
            return field
        }

    protected fun Disposable.registerDisposable() {
        disposables?.add(this)
    }

    @CallSuper
    protected fun disposeAll() {
        this.disposables?.clear()
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposeAll()
        this.disposables = null
    }

}