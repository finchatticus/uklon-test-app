package ua.vlad.uklon.presentation.view.posts

import android.util.Log
import io.reactivex.rxjava3.kotlin.subscribeBy
import ua.vlad.uklon.domain.model.Post
import ua.vlad.uklon.domain.usecase.GetPostsUseCase
import ua.vlad.uklon.domain.usecase.RefreshPostUseCase
import ua.vlad.uklon.presentation.common.BaseViewModel
import ua.vlad.uklon.presentation.common.Status
import ua.vlad.uklon.presentation.common.StatusLiveData
import ua.vlad.uklon.presentation.common.applySchedulers

class PostsViewModel(private val getPostsUseCase: GetPostsUseCase, private val refreshPostUseCase: RefreshPostUseCase) : BaseViewModel() {

    val postsLiveData = StatusLiveData<List<Post>>()

    fun fetchPosts() {
        disposeAll()
        postsLiveData.value = Status.Loading
        getPostsUseCase.getAll()
            .applySchedulers()
            .subscribeBy(
                onNext = { postsLiveData.value = Status.Success(it) },
                onError = { postsLiveData.value = Status.Error(it) }
            )
            .registerDisposable()
    }

    fun refreshPosts() {
        disposeAll()
        postsLiveData.value = Status.Loading
        refreshPostUseCase.refresh()
            .applySchedulers()
            .subscribeBy(
                onNext = { postsLiveData.value = Status.Success(it) },
                onError = { postsLiveData.value = Status.Error(it) }
            )
            .registerDisposable()
    }

    fun onPostClicked(post: Post) {
        Log.i("PostsViewModel", "onPostClicked: $post")
    }

    fun onErrorActionClicked(e: Throwable) {
        fetchPosts()
    }

}