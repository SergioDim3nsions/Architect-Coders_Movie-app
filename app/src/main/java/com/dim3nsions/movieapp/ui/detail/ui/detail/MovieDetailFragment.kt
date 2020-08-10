package com.dim3nsions.movieapp.ui.detail.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dim3nsions.movieapp.ImageType
import com.dim3nsions.movieapp.databinding.MovieDetailFragmentBinding
import com.dim3nsions.movieapp.loadUrl
import com.dim3nsions.movieapp.network.model.MoviePreview


class MovieDetailFragment : Fragment() {

    companion object {
        fun newInstance(movie: MoviePreview?): MovieDetailFragment {
            val args = Bundle()
            args.putParcelable(MovieDetailActivity.EXTRA_MOVIE, movie)

            val fragment = MovieDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: MovieDetailViewModel
    private var binding: MovieDetailFragmentBinding? = null
    private val _binding: MovieDetailFragmentBinding
        get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("LIFE","onCreateView")
        binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("LIFE","onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("LIFE","onActivityCreated")
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]

        val movie = arguments?.getParcelable<MoviePreview>(MovieDetailActivity.EXTRA_MOVIE)

        movie?.id?.let {
            viewModel.getDetails(it)
        }

        _binding.tvOverview.text = movie?.overview
        _binding.header.tvTitle.text = movie?.title
        _binding.header.ivbackDrop.loadUrl(movie?.backdropPath, ImageType.BACKDROP)
        _binding.header.ivPoster.loadUrl(movie?.posterPath)
    }

}