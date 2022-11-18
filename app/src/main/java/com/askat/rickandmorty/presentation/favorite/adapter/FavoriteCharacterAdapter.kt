package com.askat.rickandmorty.presentation.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.askat.rickandmorty.R
import com.askat.rickandmorty.databinding.CharacterItemFavListBinding
import com.askat.rickandmorty.domain.model.CharactersDomain
import com.askat.rickandmorty.presentation.character.adapter.DiffUtilCallBack
import com.askat.rickandmorty.presentation.character.adapter.FROMCHARACTERLIST
import com.askat.rickandmorty.presentation.character.adapter.FROMFAVORITELIST
import com.askat.rickandmorty.presentation.character.view.CharacterListFragmentDirections
import com.askat.rickandmorty.presentation.favorite.view.FavoriteListFragmentDirections

class FavoriteCharacterAdapter :
    ListAdapter<CharactersDomain, FavoriteCharacterAdapter.CharacterViewHolder>(DiffUtilCallBack()) {


    class CharacterViewHolder(val binding: CharacterItemFavListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                binding.characterModel?.id?.let { id ->
                    navigateToCharacterDetail(id, it, FROMFAVORITELIST)
                }
            }

        }

        fun navigateToCharacterDetail(id: Int, view: View, whichFragment: String) {

            val direction = if (FROMCHARACTERLIST == whichFragment) {
                CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
                    id
                )
            } else {
                FavoriteListFragmentDirections.actionFavoriteListFragmentToCharacterDetailFragment(
                    id
                )
            }

            view.findNavController().navigate(direction)
        }

        companion object {
            fun create(parent: ViewGroup): CharacterViewHolder {
                val binding = CharacterItemFavListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return CharacterViewHolder(binding)
            }
        }

        fun bind(charactersDomain: CharactersDomain) {
            binding.characterModel = charactersDomain
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val charactersDomain = getItem(position)
        holder.bind(charactersDomain)

        holder.itemView.animation = AnimationUtils.loadAnimation(
            holder.itemView.context,
            R.anim.up_anim
        )
    }
}

