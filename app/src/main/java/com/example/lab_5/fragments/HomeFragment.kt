package com.example.lab_5.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab_5.R
import com.example.lab_5.databinding.FragmentHomeBinding
import androidx.fragment.app.viewModels
import com.example.lab_5.L5.CharacterAdapter
import com.example.lab_5.L5.CharacterViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val TAG = "HomeFragment"

    private val viewModel: CharacterViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding null exception")

    private val adapter = CharacterAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            adapter.submitList(characters)
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }

        viewModel.fetchCharacters(page = 1)

        binding.nextPageButton.setOnClickListener {
            viewModel.loadNextPage()
        }

        binding.prevPageButton.setOnClickListener {
            viewModel.loadPreviousPage()
        }

        viewModel.currentPage.observe(viewLifecycleOwner) { currentPage ->
            binding.prevPageButton.isEnabled = currentPage > 1
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
}