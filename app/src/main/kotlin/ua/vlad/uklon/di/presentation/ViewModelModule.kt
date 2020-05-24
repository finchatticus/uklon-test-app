package ua.vlad.uklon.di.presentation

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ua.vlad.uklon.presentation.view.posts.PostsViewModel

val viewModelModule = module {
    viewModel { PostsViewModel() }
}