package com.dim3nsions.movieapp.ui.detail.ui.detail

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dim3nsions.movieapp.ImageType
import com.dim3nsions.movieapp.R
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
    private var _binding: MovieDetailFragmentBinding? = null
    private val binding: MovieDetailFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.header.toolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setHasOptionsMenu(true)
            title = ""
        }

        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.header.toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        }

        viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]

        val movie = arguments?.getParcelable<MoviePreview>(MovieDetailActivity.EXTRA_MOVIE)

        movie?.id?.let {
            viewModel.getDetails(it)
        }

        binding.header.toolbar.title = movie?.title
        binding.tvOverview.text = movie?.overview
        binding.header.ivbackDrop.loadUrl(movie?.backdropPath, ImageType.BACKDROP)
        binding.header.ivPoster.loadUrl(movie?.posterPath)
        binding.header.toolbar.setTitleTextColor(
            ContextCompat.getColor(
                binding.header.toolbar.context,
                R.color.white
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.share_menu, menu)
        //menu.findItem(R.id.action_favorite).setIcon(R.drawable.ic_baseline_favorite_24)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> activity?.onBackPressed()
            R.id.action_favorite -> item.setIcon(R.drawable.ic_baseline_favorite_24)
        }
        return super.onOptionsItemSelected(item)
    }
}