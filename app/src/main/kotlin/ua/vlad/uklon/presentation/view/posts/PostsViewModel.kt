package ua.vlad.uklon.presentation.view.posts

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable
import io.reactivex.rxjava3.schedulers.Schedulers
import ua.vlad.uklon.domain.model.Post
import ua.vlad.uklon.presentation.common.Status
import ua.vlad.uklon.presentation.common.StatusLiveData
import java.lang.Exception
import java.util.concurrent.TimeUnit

class PostsViewModel : ViewModel() {

    val postsLiveData = StatusLiveData<List<Post>>()

    fun fetchPosts() {
        postsLiveData.value = Status.Loading

        Observable.just(listOf(
            Post(1, 1, "title1", "body1"),
            Post(2, 2, "title2", "body2"),
            Post(3, 3, "title3", "body3"),
            Post(4, 4, "title4", "body4"),
            Post(5, 5, "title5", "body5"))
        ).delay(5, TimeUnit.SECONDS)
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
        Log.i("PostsViewModel", "onErrorActionClicked: $e")
    }

}