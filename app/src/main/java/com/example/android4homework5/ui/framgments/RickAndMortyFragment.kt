package com.example.android4homework5.ui.framgments

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android4homework5.R
import com.example.android4homework5.base.BaseFragment
import com.example.android4homework5.databinding.FragmentRickAndMortyBinding
import com.example.android4homework5.ui.adapters.RickAndMortyAdapter
import com.example.android4homework5.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RickAndMortyFragment :
    BaseFragment<FragmentRickAndMortyBinding, RickAndMortyViewModel>(R.layout.fragment_rick_and_morty) {

    override val binding by viewBinding(FragmentRickAndMortyBinding::bind)
    override val viewModel: RickAndMortyViewModel by viewModels()
    private val rickAndMortyAdapter = RickAndMortyAdapter()

    override fun initialize() {
        binding.rvRickAndMorty.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rickAndMortyAdapter
        }
    }

    override fun setUpObserves() {
        viewModel.noteLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    Log.e("Error", it.message.toString())
                }
                is Resource.Loading -> {
                    Log.e("Loading", it.message.toString())
                }
                is Resource.Success -> {
                    rickAndMortyAdapter.submitList(it.data)
                    Log.d("Success" , it.data.toString())
                }
            }
        }
    }
}