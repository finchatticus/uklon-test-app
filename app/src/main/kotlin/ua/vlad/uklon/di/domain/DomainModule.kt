package ua.vlad.uklon.di.domain

import org.koin.dsl.module
import ua.vlad.uklon.domain.usecase.GetPostsUseCase
import ua.vlad.uklon.domain.usecase.RefreshPostUseCase

val domainModule = module {

    single {
        GetPostsUseCase(get())
    }

    single {
        RefreshPostUseCase(get())
    }

}