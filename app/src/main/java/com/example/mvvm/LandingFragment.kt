package com.example.mvvm

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvm.databinding.FragmentLandingBinding
import kotlinx.coroutines.launch

class LandingFragment : Fragment(R.layout.fragment_landing) {
    private lateinit var binding: FragmentLandingBinding
//    private lateinit var viewModel: LandingViewModel
    private val viewModel:LandingViewModel by viewModels()
    private lateinit var movieAdapter : MovieAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLandingBinding.bind(view)
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(context,3)
            movieAdapter = MovieAdapter(requireContext(), emptyList())
            adapter = movieAdapter
        }
        viewModel.getPopularMovies()
//        viewModel = ViewModelProvider(this)[LandingViewModel::class.java]
//        viewModel.getPopularMovies()
//        viewModel.observeMovieLiveData().observe(viewLifecycleOwner, Observer { asif ->
//            movieAdapter.setMovieList(asif.categories.take(4))
//        })

        lifecycleScope.launch {
            viewModel.observeMovieLiveData().observe(viewLifecycleOwner) {
                movieAdapter.setList(it?.categories?.take(40))
                movieAdapter.notifyDataSetChanged()
            }
        }

    }



}