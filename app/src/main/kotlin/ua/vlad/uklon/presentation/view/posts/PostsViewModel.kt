package ua.vlad.uklon.presentation.view.posts

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.kotlin.subscribeBy
import ua.vlad.uklon.domain.model.Post
import ua.vlad.uklon.domain.usecase.post.GetPostsUseCase
import ua.vlad.uklon.domain.usecase.post.RefreshPostUseCase
import ua.vlad.uklon.presentation.common.BaseViewModel
import ua.vlad.uklon.presentation.common.Status
import ua.vlad.uklon.presentation.common.StatusLiveData
import ua.vlad.uklon.presentation.common.applySchedulers
import ua.vlad.uklon.presentation.view.container.Router

class PostsViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val refreshPostUseCase: RefreshPostUseCase,
    private val router: Router
) : BaseViewModel() {

    val postsLiveData = StatusLiveData<List<Post>>()
    val openCommentsViewModel = MutableLiveData<Int>()

    fun fetchPosts() {
        if (postsLiveData.isLoading())
            return
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
        if (postsLiveData.isLoading())
            return
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
        router.openCommentsScreen(post.id)
    }

    fun onErrorActionClicked(e: Throwable) {
        fetchPosts()
    }

}