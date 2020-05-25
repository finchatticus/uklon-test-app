package ua.vlad.uklon.di.domain

import org.koin.dsl.module
import ua.vlad.uklon.domain.usecase.comment.GetCommentsUseCase
import ua.vlad.uklon.domain.usecase.comment.RefreshCommentsUseCase
import ua.vlad.uklon.domain.usecase.post.GetPostsUseCase
import ua.vlad.uklon.domain.usecase.post.RefreshPostUseCase

val domainModule = module {

    single {
        GetPostsUseCase(get())
    }

    single {
        RefreshPostUseCase(get())
    }

    single {
        GetCommentsUseCase(get())
    }

    single {
        RefreshCommentsUseCase(get())
    }

}