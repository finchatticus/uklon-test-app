package ua.vlad.uklon.presentation.view.posts

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import ua.vlad.uklon.domain.model.Post
import ua.vlad.uklon.domain.usecase.GetPostsUseCase
import ua.vlad.uklon.presentation.common.Status
import ua.vlad.uklon.presentation.common.StatusLiveData

class PostsViewModel(private val getPostsUseCase: GetPostsUseCase) : ViewModel() {

    val postsLiveData = StatusLiveData<List<Post>>()

    fun fetchPosts() {
        postsLiveData.value = Status.Loading
        getPostsUseCase.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { postsLiveData.value = Status.Success(it) },
                onError = { postsLiveData.value = Status.Error(it) }
            )
    }

    fun onPostClicked(post: Post) {
        Log.i("PostsViewModel", "onPostClicked: $post")
    }

    fun onErrorActionClicked(e: Throwable) {
        fetchPosts()
    }

}