package com.askat.rickandmorty.presentation.favorite.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.askat.rickandmorty.databinding.FragmentFavoriteListBinding
import com.askat.rickandmorty.presentation.favorite.adapter.FavoriteCharacterAdapter
import com.askat.rickandmorty.presentation.favorite.viewModel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteListFragment : Fragment() {

    private var _binding: FragmentFavoriteListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FavoriteCharacterAdapter
    private val viewModel: FavoriteViewModel by viewModels()

    private val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val character = adapter.currentList.get(layoutPosition)
            viewModel.deleteCharacter(character)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareAdapter()
        lifecycleScope.launch {
            viewModel.getFavoriteCharacters().collect {
                adapter.submitList(it)
            }
        }
    }
    private fun prepareAdapter() {
        adapter = FavoriteCharacterAdapter()
        binding.characterList.adapter = adapter
        binding.characterList.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(swipeCallback).attachToRecyclerView(binding.characterList)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}