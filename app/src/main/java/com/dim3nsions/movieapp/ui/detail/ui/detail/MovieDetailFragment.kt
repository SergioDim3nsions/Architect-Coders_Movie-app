package com.dim3nsions.movieapp.ui.detail.ui.detail

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dim3nsions.movieapp.ImageType
import com.dim3nsions.movieapp.R
import com.dim3nsions.movieapp.databinding.MovieDetailFragmentBinding
import com.dim3nsions.movieapp.loadUrl
import com.dim3nsions.movieapp.ui.model.PresentationMoviePreview


class MovieDetailFragment : Fragment() {

    companion object {
        fun newInstance(movie: PresentationMoviePreview?): MovieDetailFragment {
            val args = Bundle()
            args.putParcelable(MovieDetailActivity.EXTRA_MOVIE, movie)

            val fragment = MovieDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: MovieDetailViewModel
    private var optionsMenu: Menu? = null
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
        viewModel.movie.value = arguments?.getParcelable(MovieDetailActivity.EXTRA_MOVIE)
        viewModel.movie.observe(viewLifecycleOwner, Observer {
            loadView(it)
        })

        viewModel.isFavorite.observe(viewLifecycleOwner, Observer { isFavorite ->
            loadFavoriteIcon(isFavorite)
        })
    }

    private fun loadView(it: PresentationMoviePreview?) {
        it?.let {
            binding.header.toolbar.title = it.title
            binding.tvOverview.text = it.overview
            binding.header.ivbackDrop.loadUrl(it.backdropPath, ImageType.BACKDROP)
            binding.header.ivPoster.loadUrl(it.posterPath)
            binding.header.toolbar.setTitleTextColor(
                ContextCompat.getColor(
                    binding.header.toolbar.context,
                    R.color.white
                )
            )

            viewModel.getDetails(it.id)
        }
    }

    private fun loadFavoriteIcon(isFavorite: Boolean) {
        val favoriteIconMenu = optionsMenu?.findItem(R.id.action_favorite)
        if (isFavorite) {
            favoriteIconMenu?.setIcon(R.drawable.ic_baseline_favorite_24)
        } else {
            favoriteIconMenu?.setIcon(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.share_menu, menu)
        optionsMenu = menu
        viewModel.checkIfMovieIsFavorite()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> activity?.onBackPressed()
            R.id.action_favorite -> viewModel.updateFavorite()
        }
        return super.onOptionsItemSelected(item)
    }
}