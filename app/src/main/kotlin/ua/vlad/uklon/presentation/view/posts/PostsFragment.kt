package ua.vlad.uklon.presentation.view.posts

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_posts.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ua.vlad.uklon.R
import ua.vlad.uklon.presentation.common.BaseFragment
import ua.vlad.uklon.presentation.common.Status.*
import ua.vlad.uklon.presentation.view.posts.rv.PostsAdapter

class PostsFragment : BaseFragment<PostsViewModel>(R.layout.fragment_posts) {

    companion object {
        fun newInstance(): PostsFragment = PostsFragment()
    }

    override val viewModel by viewModel<PostsViewModel>()

    private val postsAdapter = PostsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPostsRecyclerView()

        viewModel.fetchPosts()
    }

    override fun subscribeLiveData() {
        viewModel.postsLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Loading -> showLoading()
                is Success -> {
                    hideLoading()
                    hideError()
                    postsAdapter.addPosts(it.data)
                }
                is Error -> {
                    hideLoading()
                    showError(it.exception) {
                        viewModel.onErrorActionClicked(it.exception)
                    }
                }
            }
        })
    }

    override fun unSubscribeLiveData() {
        viewModel.postsLiveData.removeObservers(viewLifecycleOwner)
    }

    private fun initPostsRecyclerView() {
        postsAdapter.onPostClicked = {
            viewModel.onPostClicked(it)
        }
        posts_rv.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).apply {
            ContextCompat.getDrawable(requireContext(), R.drawable.divider_post_recycler_view)?.run {
                setDrawable(this)
            }
        })
        posts_rv.adapter = postsAdapter
    }

}