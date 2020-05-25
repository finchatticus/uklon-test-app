package ua.vlad.uklon.presentation.view.comments

import io.reactivex.rxjava3.kotlin.subscribeBy
import ua.vlad.uklon.domain.model.Comment
import ua.vlad.uklon.domain.usecase.comment.GetCommentsUseCase
import ua.vlad.uklon.domain.usecase.comment.RefreshCommentsUseCase
import ua.vlad.uklon.presentation.common.BaseViewModel
import ua.vlad.uklon.presentation.common.Status
import ua.vlad.uklon.presentation.common.StatusLiveData
import ua.vlad.uklon.presentation.common.applySchedulers

class CommentsViewModel(
    private val idPost: Int,
    private val getCommentsUseCase: GetCommentsUseCase,
    private val refreshCommentsUseCase: RefreshCommentsUseCase
) : BaseViewModel() {

    val commentsLiveData = StatusLiveData<List<Comment>>()

    fun fetchComments() {
        if (commentsLiveData.isLoading())
            return
        commentsLiveData.value = Status.Loading
        getCommentsUseCase.getByIdPost(idPost)
            .applySchedulers()
            .subscribeBy(
                onNext = { commentsLiveData.value = Status.Success(it) },
                onError = { commentsLiveData.value = Status.Error(it) }
            )
            .registerDisposable()
    }

    fun refreshComments() {
        if (commentsLiveData.isLoading())
            return
        commentsLiveData.value = Status.Loading
        refreshCommentsUseCase.refreshByIdPost(idPost)
            .applySchedulers()
            .subscribeBy(
                onNext = { commentsLiveData.value = Status.Success(it) },
                onError = { commentsLiveData.value = Status.Error(it) }
            )
            .registerDisposable()
    }

    fun onErrorActionClicked(e: Throwable) {
        fetchComments()
    }

}