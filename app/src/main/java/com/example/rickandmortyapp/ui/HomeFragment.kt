package com.example.rickandmortyapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.databinding.FragmentHomeBinding
import com.example.rickandmortyapp.ui.viewmodel.CharacterViewModel
import com.example.rickandmortyapp.ui.viewmodel.CharacterViewStates
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val characterViewModel: CharacterViewModel by activityViewModels()
    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        displayProgressBar(true)
        characterViewModel.getCharacters()
        setUpRecyclerView()

        characterViewModel.characterViewState.observe(viewLifecycleOwner, {
            when (it) {
                is CharacterViewStates.Success -> {
                    displayProgressBar(false)
                    characterAdapter.characters = it.data
                }
                is CharacterViewStates.Error -> {
                    displayProgressBar(false)
                    Snackbar.make(requireView(), "${it.error}", Snackbar.LENGTH_SHORT).show()
                    Log.d("HomeFragment", "Error: ${it.error}")
                }
                CharacterViewStates.Loading -> {
                    displayProgressBar(true)
                }
            }
        })

        return binding.root
    }

    private fun setUpRecyclerView() {
        characterAdapter = CharacterAdapter();
        binding.rvFragmentHome.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        binding.progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}