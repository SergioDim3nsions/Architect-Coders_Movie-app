package com.dim3nsions.movieapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dim3nsions.movieapp.databinding.MainFragmentBinding
import com.dim3nsions.movieapp.startActivity
import com.dim3nsions.movieapp.ui.detail.ui.detail.MovieDetailActivity
import com.dim3nsions.movieapp.ui.main.adapter.MovieAdapter
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val adapter = MovieAdapter {
        activity?.startActivity<MovieDetailActivity>()
    }
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        initObservers()

        rvMovie.adapter = adapter
    }

    private fun initObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
            binding.progress.visibility = when (it) {
                true -> View.VISIBLE
                false -> View.GONE
            }
        })

        viewModel.nowPlaying.observe(viewLifecycleOwner, Observer { items ->
            adapter.items = items
        })
    }

}