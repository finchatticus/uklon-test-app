package ua.vlad.uklon.presentation.view.posts

import androidx.lifecycle.SavedStateHandle
import io.reactivex.rxjava3.kotlin.subscribeBy
import ua.vlad.uklon.domain.usecase.post.GetPostsUseCase
import ua.vlad.uklon.domain.usecase.post.RefreshPostUseCase
import ua.vlad.uklon.presentation.common.BaseViewModel
import ua.vlad.uklon.presentation.common.Status
import ua.vlad.uklon.presentation.common.StatusLiveData
import ua.vlad.uklon.presentation.common.applySchedulers
import ua.vlad.uklon.presentation.mapper.PostToPostVOMapper
import ua.vlad.uklon.presentation.model.PostVO
import ua.vlad.uklon.presentation.view.container.Router

private const val KEY_SAVED_STATE_HANDLE_POSTS = "KEY_SAVED_STATE_HANDLE_POSTS"

class PostsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getPostsUseCase: GetPostsUseCase,
    private val refreshPostUseCase: RefreshPostUseCase,
    private val router: Router
) : BaseViewModel() {

    private val _postsLiveData = StatusLiveData<List<PostVO>>(savedStateHandle.get(KEY_SAVED_STATE_HANDLE_POSTS))
    val postsLiveData = _postsLiveData

    fun fetchPosts() {
        if (postsLiveData.isLoadingOrSuccess())
            return
        postsLiveData.value = Status.Loading
        getPostsUseCase.getAll()
            .map(PostToPostVOMapper())
            .applySchedulers()
            .subscribeBy(
                onNext = {
                    savedStateHandle.set(KEY_SAVED_STATE_HANDLE_POSTS, it)
                    postsLiveData.value = Status.Success(it)
                },
                onError = { postsLiveData.value = Status.Error(it) }
            )
            .registerDisposable()
    }

    fun refreshPosts() {
        if (postsLiveData.isLoadingOrSuccess())
            return
        postsLiveData.value = Status.Loading
        refreshPostUseCase.refresh()
            .map(PostToPostVOMapper())
            .applySchedulers()
            .subscribeBy(
                onNext = {
                    savedStateHandle.set(KEY_SAVED_STATE_HANDLE_POSTS, it)
                    postsLiveData.value = Status.Success(it)
                },
                onError = { postsLiveData.value = Status.Error(it) }
            )
            .registerDisposable()
    }

    fun onPostClicked(post: PostVO) {
        router.openCommentsScreen(post.id)
    }

    fun onErrorActionClicked(e: Throwable) {
        fetchPosts()
    }

}