package ua.vlad.uklon.presentation.view.comments

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_comments.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ua.vlad.uklon.BuildConfig
import ua.vlad.uklon.R
import ua.vlad.uklon.presentation.common.BaseFragment
import ua.vlad.uklon.presentation.common.Status.*
import ua.vlad.uklon.presentation.view.comments.rv.CommentsAdapter

private const val ARG_ID_POST = "${BuildConfig.APPLICATION_ID}.ARG_ID_POST"

class CommentsFragment : BaseFragment<CommentsViewModel>(R.layout.fragment_comments) {

    companion object {
        const val DEFAULT_ID_POST = -1;
        fun newInstance(idPost: Int) = CommentsFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_ID_POST, idPost)
            }
        }
    }

    override val viewModel by viewModel<CommentsViewModel> { parametersOf(idPost) }

    private val commentsAdapter = CommentsAdapter()
    private var idPost: Int = DEFAULT_ID_POST

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        idPost = arguments?.getInt(ARG_ID_POST, DEFAULT_ID_POST) ?: DEFAULT_ID_POST
        Log.wtf("VLADOSIK", "idPost: $idPost")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPostsRecyclerView()

        viewModel.fetchComments()
    }

    override fun onOptionsItemSelected(item: MenuItem) =  when (item.itemId) {
        R.id.action_refresh -> {
            viewModel.refreshComments()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun subscribeLiveData() {
        viewModel.commentsLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Loading -> showLoading()
                is Success -> {
                    hideLoading()
                    hideError()
                    commentsAdapter.addComments(it.data)
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
        viewModel.commentsLiveData.removeObservers(viewLifecycleOwner)
    }

    private fun initPostsRecyclerView() {
        comments_rv.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).apply {
            ContextCompat.getDrawable(requireContext(), R.drawable.divider_post_recycler_view)?.run {
                setDrawable(this)
            }
        })
        comments_rv.adapter = commentsAdapter
    }

}