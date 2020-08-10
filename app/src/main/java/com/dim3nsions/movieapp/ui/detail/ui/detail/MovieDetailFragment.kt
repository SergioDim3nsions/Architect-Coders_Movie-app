package com.dim3nsions.movieapp.ui.detail.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dim3nsions.movieapp.R
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.movie_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]

        val movie = arguments?.getParcelable<MoviePreview>(MovieDetailActivity.EXTRA_MOVIE)
        movie?.id?.let {
            viewModel.getDetails(it)
        }
        Toast.makeText(activity, movie?.id.toString(), Toast.LENGTH_SHORT).show()
    }

}