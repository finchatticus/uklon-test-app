package ua.vlad.uklon.di.presentation

import androidx.lifecycle.SavedStateHandle
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ua.vlad.uklon.presentation.view.comments.CommentsViewModel
import ua.vlad.uklon.presentation.view.container.Router
import ua.vlad.uklon.presentation.view.posts.PostsViewModel

val viewModelModule = module {

    viewModel { (handle: SavedStateHandle, router: Router) -> PostsViewModel(handle, get(), get(), router) }

    viewModel { (idPost: Int) -> CommentsViewModel(idPost, get(), get()) }

}