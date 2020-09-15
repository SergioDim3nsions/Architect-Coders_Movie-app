package com.dim3nsions.movieapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dim3nsions.movieapp.databinding.MainFragmentBinding
import com.dim3nsions.movieapp.startActivity
import com.dim3nsions.movieapp.ui.detail.ui.detail.MovieDetailActivity
import com.dim3nsions.movieapp.ui.main.adapter.MovieAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val adapter = MovieAdapter {
        activity?.startActivity<MovieDetailActivity>(
            MovieDetailActivity.EXTRA_MOVIE to it
        )
    }
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private var layoutManager: GridLayoutManager? = null
    private var tabPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovie.adapter = adapter
        layoutManager = rvMovie.layoutManager as GridLayoutManager
        binding.tlMovies.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                adapter.clear()
                binding.tvSearch.text?.clear()
                viewModel.resetPages()
                tabPosition = tab.position
                getMoviesByTabPosition()
            }
        })

        binding.rvMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                layoutManager?.let {
                    val totalItemCount: Int = it.itemCount
                    val visibleItemCount: Int = it.childCount
                    val firstVisibleItem: Int = it.findFirstVisibleItemPosition()

                    val elementsLeft = totalItemCount - visibleItemCount - firstVisibleItem
                    if (elementsLeft < 2) {
                        getMoviesByTabPosition(true)
                    }
                }

            }
        })
    }

    private fun getMoviesByTabPosition(isScrolled: Boolean = false) {
        when (tabPosition) {
            0 -> viewModel.getNowPlaying()
            1 -> viewModel.getPopular()
            2 -> viewModel.getUpcoming()
            3 -> viewModel.getTopRated()
            4 -> {
                if (!isScrolled) {
                    viewModel.getFavorites()
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initObservers()

        binding.tvSearch.addTextChangedListener {
            adapter.clear()
            viewModel.getSearchResults(it.toString())
        }
    }

    private fun initObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
            binding.progress.visibility = when (it) {
                true -> View.VISIBLE
                false -> View.GONE
            }
        })

        viewModel.movies.observe(viewLifecycleOwner, Observer { items ->
            adapter.addItems(items)
        })
    }

}